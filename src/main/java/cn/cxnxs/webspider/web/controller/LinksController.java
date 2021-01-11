package cn.cxnxs.webspider.web.controller;


import cn.cxnxs.webspider.utils.ObjectUtil;
import cn.cxnxs.webspider.web.entity.Links;
import cn.cxnxs.webspider.web.vo.LinksVO;
import cn.cxnxs.webspider.web.vo.ResponseResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 * agent关联 前端控制器
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
@Controller
@RequestMapping("/links")
public class LinksController {

    @ResponseResult
    @RequestMapping
    public List<Links> getLinks(LinksVO linksVO){
        Links links=new Links();
        ObjectUtil.transValues(linksVO,links);
        return links.selectAll();
    }
}

