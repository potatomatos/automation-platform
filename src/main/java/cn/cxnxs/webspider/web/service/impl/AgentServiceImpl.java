package cn.cxnxs.webspider.web.service.impl;

import cn.cxnxs.webspider.utils.ObjectUtil;
import cn.cxnxs.webspider.web.entity.Agent;
import cn.cxnxs.webspider.web.entity.Links;
import cn.cxnxs.webspider.web.entity.ScenarioAgentRel;
import cn.cxnxs.webspider.web.entity.Scenarios;
import cn.cxnxs.webspider.web.mapper.AgentMapper;
import cn.cxnxs.webspider.web.service.IAgentService;
import cn.cxnxs.webspider.web.service.IScenarioAgentRelService;
import cn.cxnxs.webspider.web.vo.AgentTypeVo;
import cn.cxnxs.webspider.web.vo.AgentVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 爬虫任务配置信息 服务实现类
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
@Service
public class AgentServiceImpl extends ServiceImpl<AgentMapper, Agent> implements IAgentService {


    /**
     * 获取agent配置信息
     *
     * @param agentType agent类型
     * @return agent配置json数据
     */
    @Override
    public Map<String, Object> getAgentConfig(String agentType) {
        return null;
    }

    @Override
    public List<AgentVo> findByTypeProperties(AgentTypeVo agentTypeVo) {
        AgentMapper agentMapper = getBaseMapper();
        List<Agent> agents = agentMapper.selectByTypeProperties(agentTypeVo);
        return ObjectUtil.copyListProperties(agents,AgentVo.class);
    }

    @Transactional
    @Override
    public Map<String, String> saveAgent(AgentVo agentVo) {
        //保存代理
        Agent agent=new Agent();
        ObjectUtil.transValues(agentVo,agent);
        agent.insertOrUpdate();
        //保存代理和方案关系
        if (agentVo.getScenarioIds()!=null){
            String[] scenarios = agentVo.getScenarioIds().split(",");
            for (String scenarioId : scenarios) {
                ScenarioAgentRel senarioAgentRel=new ScenarioAgentRel();
                senarioAgentRel.setAgentId(agent.getId());
                senarioAgentRel.setScenarioId(Integer.parseInt(scenarioId));
                senarioAgentRel.setCreatedAt(LocalDateTime.now());
                senarioAgentRel.insertOrUpdate();
            }
        }
        //保存代理-代理关系
        if (agentVo.getSources()!=null){
            String[] sources = agentVo.getSources().split(",");
            for (String sourceId: sources) {
                Links links=new Links();
                links.setSourceId(Integer.parseInt(sourceId));
                links.setReceiverId(agent.getId());
                links.setCreatedAt(LocalDateTime.now());
                links.insertOrUpdate();
            }
        }
        if (agentVo.getReceivers()!=null){
            String[] receivers = agentVo.getReceivers().split(",");
            for (String receiverId : receivers) {
                Links links=new Links();
                links.setSourceId(agent.getId());
                links.setReceiverId(Integer.parseInt(receiverId));
                links.setCreatedAt(LocalDateTime.now());
                links.insertOrUpdate();
            }
        }
        return new HashMap<>();
    }

}
