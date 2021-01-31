package cn.cxnxs.webspider.web.controller;


import cn.cxnxs.webspider.core.Event;
import cn.cxnxs.webspider.exception.AgentNotFoundException;
import cn.cxnxs.webspider.utils.ObjectUtil;
import cn.cxnxs.webspider.web.entity.Agent;
import cn.cxnxs.webspider.web.entity.Events;
import cn.cxnxs.webspider.web.service.IAgentService;
import cn.cxnxs.webspider.web.vo.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 爬虫任务配置信息 前端控制器
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/agents")
public class AgentController {

    @Autowired
    private IAgentService agentService;

    @RequestMapping
    public Result<List<AgentVo>> list(AgentVo agentVo){
        return agentService.pageList(agentVo);
    }

    @ResponseResult
    @RequestMapping("all")
    public List<AgentVo> listAll(){
        List<Agent> agents = agentService.list();
        return ObjectUtil.copyListProperties(agents,AgentVo.class);
    }

    @ResponseResult
    @RequestMapping("find")
    public List<AgentVo> find(AgentTypeVo agentTypeVo){
        return  agentService.findByTypeProperties(agentTypeVo);
    }

    @ResponseResult
    @RequestMapping("save")
    public Map<String, String> saveAgent(AgentVo agentVo){
        return  agentService.saveAgent(agentVo);
    }

    @ResponseResult
    @RequestMapping("{id}")
    public AgentVo getOne(@PathVariable("id") Integer id) throws AgentNotFoundException {
        return  agentService.getAgentById(id);
    }

    /**
     * 获取数据源数据
     * @return 数据源产生的数据列表
     */
    @ResponseResult
    @GetMapping("events/{id}")
    public Result<List<Event>> getEvents(@PathVariable("id") Integer id,Integer page,Integer limit){
        IPage<Events> p=new Page<>();
        p.setCurrent(page);
        p.setSize(limit);
        IPage<Events> events = new Events().selectPage(p, new QueryWrapper<Events>().eq("agent_id", id).orderByDesc("id"));
        PageResult<List<Event>> result = new PageResult<>(p.getTotal());
        result.setData(ObjectUtil.copyListProperties(events.getRecords(),Event.class));
        return result;
    }

    /**
     * 测试运行
     * @param type 代理类型id
     * @param options 配置
     * @param payload 数据源
     * @return 代理数据
     * @throws HttpProcessException
     * @throws ClassNotFoundException
     */
    @ResponseResult
    @RequestMapping("dryRun/{type}")
    public List<Map<String, String>> dryRun(@PathVariable("type") Integer type,
                                             String options,
                                             String payload) throws HttpProcessException, ClassNotFoundException {
        JSONObject optionsJson= JSON.parseObject(options);
        JSONObject payloadJson=JSON.parseObject(payload);
        return agentService.dryRun(type, optionsJson, payloadJson);
    }
}

