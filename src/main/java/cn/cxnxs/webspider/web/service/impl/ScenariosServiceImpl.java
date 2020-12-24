package cn.cxnxs.webspider.web.service.impl;

import cn.cxnxs.webspider.utils.ObjectUtil;
import cn.cxnxs.webspider.web.entity.Agent;
import cn.cxnxs.webspider.web.entity.ScenarioAgentRel;
import cn.cxnxs.webspider.web.entity.Scenarios;
import cn.cxnxs.webspider.web.mapper.ScenariosMapper;
import cn.cxnxs.webspider.web.service.IAgentService;
import cn.cxnxs.webspider.web.service.IScenarioAgentRelService;
import cn.cxnxs.webspider.web.service.IScenariosService;
import cn.cxnxs.webspider.web.vo.AgentVo;
import cn.cxnxs.webspider.web.vo.ScenariosVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 任务场景 服务实现类
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
@Service
public class ScenariosServiceImpl extends ServiceImpl<ScenariosMapper, Scenarios> implements IScenariosService {

    @Autowired
    private ScenariosMapper scenariosMapper;

    @Autowired
    private IScenarioAgentRelService scenarioAgentRelService;

    @Autowired
    private IAgentService agentService;

    @Override
    public IPage<ScenariosVo> getList(Page<ScenariosVo> page, ScenariosVo scenariosVo) {
        return scenariosMapper.selectScenariosList(page,scenariosVo);
    }

    /**
     * 获取详情
     *
     * @param id none
     * @return
     */
    @Override
    public ScenariosVo getDetail(Integer id) {
        Scenarios scenarios = super.getById(id);
        ScenariosVo scenariosVo=new ScenariosVo();
        if (scenarios!=null){
            BeanUtils.copyProperties(scenarios,scenariosVo);
            List<ScenarioAgentRel> scenarioAgentRels = scenarioAgentRelService
                    .list(new QueryWrapper<ScenarioAgentRel>().eq("scenario_id", id));
            if (scenarioAgentRels.size()!=0){
                List<Integer> ids=new ArrayList<>();
                scenarioAgentRels.forEach(scenarioAgentRel->{
                    ids.add(scenarioAgentRel.getAgentId());
                });
                List<Agent> agents = agentService.list(new QueryWrapper<Agent>().in("id", ids));
                scenariosVo.setAgents(ObjectUtil.copyListProperties(agents, AgentVo.class));
            }
        }
        return scenariosVo;
    }

    /**
     * 保存数据
     *
     * @param scenariosVo
     * @return
     */
    @Transactional
    @ResponseBody
    @Override
    public Map<String,String> saveScenarios(ScenariosVo scenariosVo) {
        Scenarios scenarios=new Scenarios();
        BeanUtils.copyProperties(scenariosVo,scenarios);
        super.saveOrUpdate(scenarios);
        //保存关系
        if (scenariosVo.getAgentIds()!=null){
            if (scenariosVo.getId()!=null){
                scenarioAgentRelService.remove(new QueryWrapper<ScenarioAgentRel>().eq("scenario_id",scenariosVo.getId()));
            }
            List<ScenarioAgentRel> scenarioAgentRels=new ArrayList<>();
            for (Integer agentId: scenariosVo.getAgentIds()){
                ScenarioAgentRel scenarioAgentRel=new ScenarioAgentRel();
                scenarioAgentRel.setAgentId(agentId);
                scenarioAgentRel.setScenarioId(scenariosVo.getId());
                scenarioAgentRel.setCreatedAt(LocalDateTime.now());
                scenarioAgentRels.add(scenarioAgentRel);
            }
            scenarioAgentRelService.saveBatch(scenarioAgentRels);
        }
        return new HashMap<>();
    }
}
