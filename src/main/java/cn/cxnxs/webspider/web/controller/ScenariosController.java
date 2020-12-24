package cn.cxnxs.webspider.web.controller;


import cn.cxnxs.webspider.web.service.IAgentService;
import cn.cxnxs.webspider.web.service.IScenariosService;
import cn.cxnxs.webspider.web.vo.PageResult;
import cn.cxnxs.webspider.web.vo.ResponseResult;
import cn.cxnxs.webspider.web.vo.ScenariosVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @ResponseResult
    @RequestMapping("detail/{id}")
    public ScenariosVo detail(@PathVariable("id") Integer id){
        return scenariosService.getDetail(id);
    }

    @ResponseResult
    @RequestMapping("save")
    public Map<String, String> save(ScenariosVo scenariosVo){
        return scenariosService.saveScenarios(scenariosVo);
    }
}

