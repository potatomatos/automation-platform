package cn.cxnxs.webspider.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>首页控制器</p>
 *
 * @author mengjinyuan
 * @date 2020-11-11 11:42
 **/
@Controller
public class IndexController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
