package cn.cxnxs.webspider.web.controller;


import cn.cxnxs.webspider.utils.ObjectUtil;
import cn.cxnxs.webspider.web.entity.Agent;
import cn.cxnxs.webspider.web.service.IAgentService;
import cn.cxnxs.webspider.web.vo.AgentTypeVo;
import cn.cxnxs.webspider.web.vo.AgentVo;
import cn.cxnxs.webspider.web.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @ResponseResult
    @RequestMapping("all")
    List<AgentVo> listAll(){
        List<Agent> agents = agentService.list();
        return ObjectUtil.copyListProperties(agents,AgentVo.class);
    }

    @ResponseResult
    @RequestMapping("find")
    List<AgentVo> find(AgentTypeVo agentTypeVo){
        return  agentService.findByTypeProperties(agentTypeVo);
    }
}

