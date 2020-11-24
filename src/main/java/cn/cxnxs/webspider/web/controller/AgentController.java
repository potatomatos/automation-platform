package cn.cxnxs.webspider.web.controller;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * <p>
 * 爬虫任务配置信息 前端控制器
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
@Controller
@RequestMapping("/agents")
public class AgentController {


    public Map<String,Object> getAgentConfig(){

        return null;
    }
}

