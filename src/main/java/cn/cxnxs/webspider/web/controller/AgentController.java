package cn.cxnxs.webspider.web.controller;


import cn.cxnxs.webspider.web.vo.AgentTypeVo;
import cn.cxnxs.webspider.web.vo.AgentVo;
import cn.cxnxs.webspider.web.vo.ResponseResult;
import com.alibaba.fastjson.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

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


    public Map<String,Object> getAgentConfig(){

        return null;
    }
}

