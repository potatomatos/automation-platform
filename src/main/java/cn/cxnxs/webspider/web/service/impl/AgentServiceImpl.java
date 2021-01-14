package cn.cxnxs.webspider.web.service.impl;

import cn.cxnxs.webspider.utils.ObjectUtil;
import cn.cxnxs.webspider.web.entity.*;
import cn.cxnxs.webspider.web.mapper.AgentMapper;
import cn.cxnxs.webspider.web.service.IAgentService;
import cn.cxnxs.webspider.web.service.ILinksService;
import cn.cxnxs.webspider.web.service.IScenarioAgentRelService;
import cn.cxnxs.webspider.web.vo.AgentTypeVo;
import cn.cxnxs.webspider.web.vo.AgentVo;
import cn.cxnxs.webspider.web.vo.ScenariosVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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


    @Autowired
    private IScenarioAgentRelService scenarioAgentRelService;
    @Autowired
    private ILinksService linksService;

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
        scenarioAgentRelService.remove(new QueryWrapper<ScenarioAgentRel>().eq("agent_id",agent.getId()));
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
        linksService.remove(new QueryWrapper<Links>().eq("source_id",agent.getId()));
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
        linksService.remove(new QueryWrapper<Links>().eq("receiver_id",agent.getId()));
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

        return new HashMap<>();
    }

    @Override
    public AgentVo getAgentById(@NotNull Integer id) {
        //获取代理信息
        Agent agent=getById(id);
        AgentVo agentVo=new AgentVo();
        ObjectUtil.transValues(agent,agentVo);
        //获取数据源
        List<Links> sourcesLinks=new Links().selectList(new QueryWrapper<Links>().eq("receiver_id",id));
        if (sourcesLinks.size()!=0){
            List<Integer> sourcesIds=new ArrayList<>();
            for (Links links : sourcesLinks) {
                sourcesIds.add(links.getSourceId());
            }
            List<Agent> sources=new Agent().selectList(new QueryWrapper<Agent>().in("id",sourcesIds));
            agentVo.setSourceAgents(ObjectUtil.copyListProperties(sources,AgentVo.class));
        }
        //获取接收者
        List<Links> receiversLinks=new Links().selectList(new QueryWrapper<Links>().eq("source_id",id));
        if (receiversLinks.size()!=0){
            List<Integer> receiversIds=new ArrayList<>();
            for (Links links : receiversLinks) {
                receiversIds.add(links.getReceiverId());
            }
            List<Agent> receivers=new Agent().selectList(new QueryWrapper<Agent>().in("id",receiversIds));
            agentVo.setReceiverAgents(ObjectUtil.copyListProperties(receivers,AgentVo.class));
        }
        //获取方案
        List<ScenarioAgentRel> scenarioAgentRels = new ScenarioAgentRel().selectList(new QueryWrapper<ScenarioAgentRel>().eq("agent_id", id));
        if (scenarioAgentRels.size()!=0){
            List<Integer> scenarioIds=new ArrayList<>();
            for (ScenarioAgentRel scenarioAgentRel : scenarioAgentRels) {
                scenarioIds.add(scenarioAgentRel.getScenarioId());
            }
            List<Scenarios> scenarios=new Scenarios().selectList(new QueryWrapper<Scenarios>().in("id",scenarioIds));
            agentVo.setScenarios(ObjectUtil.copyListProperties(scenarios, ScenariosVo.class));
        }
        if (agentVo.getType()!=null){
            AgentType agentType = new AgentType().selectById(agentVo.getType());
            AgentTypeVo agentTypeVo=new AgentTypeVo();
            ObjectUtil.transValues(agentType,agentTypeVo);
            agentVo.setAgentType(agentTypeVo);
        }
        return agentVo;
    }

}
