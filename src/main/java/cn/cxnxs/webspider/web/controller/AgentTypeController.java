package cn.cxnxs.webspider.web.controller;


import cn.cxnxs.webspider.utils.ObjectUtil;
import cn.cxnxs.webspider.web.entity.AgentType;
import cn.cxnxs.webspider.web.service.IAgentTypeService;
import cn.cxnxs.webspider.web.vo.AgentTypeVo;
import cn.cxnxs.webspider.web.vo.ResponseResult;
import cn.cxnxs.webspider.web.vo.Result;
import com.alibaba.fastjson.JSONArray;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * agent定义信息 前端控制器
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-17
 */
@RestController
@RequestMapping("/agentType")
public class AgentTypeController {

    @Autowired
    private IAgentTypeService agentTypeService;

    @ResponseResult
    @RequestMapping
    public List<AgentTypeVo> getAgentTypes() {
        List<AgentType> agentTypes = agentTypeService.list();
        return ObjectUtil.copyListProperties(agentTypes,AgentTypeVo.class);
    }

    @RequestMapping("save")
    public Result<String> save(AgentType agentType) {
        agentType.setCreatedAt(LocalDateTime.now());
        agentType.setUpdatedAt(LocalDateTime.now());
        agentTypeService.save(agentType);
        return Result.success("保存成功");
    }

    @SneakyThrows
    @ResponseResult
    @RequestMapping("detail/{id}")
    public AgentTypeVo detail(@PathVariable("id") Integer id) {
        AgentType agentType = agentTypeService.getById(id);
        AgentTypeVo agentTypeVo=new AgentTypeVo();
        ObjectUtil.transValues(agentType,agentTypeVo);
        return agentTypeVo;
    }


    @RequestMapping("delete/{id}")
    public Result<String> delete(@PathVariable("id") Integer id) {
        agentTypeService.removeById(id);
        return Result.success("删除成功");
    }

    @RequestMapping("modify")
    public Result<String> modify(AgentType agentType) {
        agentType.setUpdatedAt(LocalDateTime.now());
        agentTypeService.updateById(agentType);
        return Result.success("更新成功");
    }

    @ResponseResult
    @RequestMapping("schedules")
    public JSONArray schedules() {
        return AgentTypeVo.ScheduleEnum.toJson();
    }
}

