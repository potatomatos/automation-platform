package cn.cxnxs.webspider.web.controller;


import cn.cxnxs.webspider.web.service.IAgentService;
import cn.cxnxs.webspider.web.service.IScenariosService;
import cn.cxnxs.webspider.web.vo.PageResult;
import cn.cxnxs.webspider.web.vo.ScenariosVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 任务场景 前端控制器
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
@RestController
@RequestMapping("/scenarios")
public class ScenariosController {

    @Autowired
    private IScenariosService scenariosService;

    @Autowired
    private IAgentService agentService;


    @RequestMapping("")
    public PageResult<List<ScenariosVo>> list(Long page,Long limit,ScenariosVo scenario){
        Page<ScenariosVo> pagination=new Page<>(page,limit);
        IPage<ScenariosVo> result = scenariosService.getList(pagination,scenario);
        PageResult<List<ScenariosVo>> res=new PageResult<>(pagination.getTotal());
        res.setData(result.getRecords());
        return res;
    }
}

