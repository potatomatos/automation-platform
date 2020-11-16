package cn.cxnxs.webspider.web.controller;

import java.time.LocalDateTime;


import cn.cxnxs.webspider.web.ResponseResult;
import cn.cxnxs.webspider.web.entity.SysMenu;
import cn.cxnxs.webspider.web.service.ISysMenuService;
import cn.cxnxs.webspider.web.vo.MenuVo;
import cn.cxnxs.webspider.web.vo.Result;
import cn.cxnxs.webspider.web.vo.TreeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单信息 前端控制器
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-11
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Resource
    private ISysMenuService sysMenuService;

    /**
     * 获取系统菜单
     *
     * @return none
     */
    @GetMapping("/menu")
    public Map<String, Object> menu() {
        return sysMenuService.menu();
    }

    @ResponseResult
    @GetMapping("list")
    public List<MenuVo> list() {
        List<SysMenu> sysMenus = sysMenuService.list(new QueryWrapper<SysMenu>().orderByAsc("sort"));
        List<MenuVo> menuVoList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            MenuVo menuVo = new MenuVo();
            menuVo.setId(sysMenu.getId());
            menuVo.setPId(sysMenu.getPId());
            menuVo.setHref(sysMenu.getHref());
            menuVo.setTitle(sysMenu.getTitle());
            menuVo.setIcon(sysMenu.getIcon());
            menuVo.setTarget(sysMenu.getTarget());
            menuVo.setUserId(sysMenu.getUserId());
            menuVo.setCreatedAt(sysMenu.getCreatedAt());
            menuVo.setUpdatedAt(sysMenu.getUpdatedAt());
            menuVo.setSort(sysMenu.getSort());
            menuVoList.add(menuVo);
        }
        return menuVoList;
    }

    /**
     * 获取菜单树
     *
     * @return none
     */

    @ResponseResult
    @GetMapping("/menuTree")
    public List<TreeVo> menuTree() {
        return sysMenuService.menuTree();
    }

    @RequestMapping("/save")
    public Result<String> save(MenuVo menuVo) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setPId(menuVo.getPId());
        sysMenu.setHref(menuVo.getHref());
        sysMenu.setTitle(menuVo.getTitle());
        sysMenu.setIcon(menuVo.getIcon());
        sysMenu.setTarget(menuVo.getTarget());
        sysMenu.setUserId(1);
        sysMenu.setSort(menuVo.getSort());
        sysMenu.setCreatedAt(LocalDateTime.now());
        sysMenu.setState(0);
        sysMenuService.save(sysMenu);
        return Result.success("success");
    }

    @RequestMapping("/delete/{id}")
    public Result<String> delete(@PathVariable("id") Integer id) {
        sysMenuService.delete(id);
        return Result.success("success");
    }

    @ResponseResult
    @RequestMapping("/{id}")
    public MenuVo getById(@PathVariable("id") Integer id) {
        SysMenu sysMenu = sysMenuService.getById(id);
        MenuVo menuVo=new MenuVo();
        menuVo.setId(sysMenu.getId());
        menuVo.setPId(sysMenu.getPId());
        menuVo.setHref(sysMenu.getHref());
        menuVo.setTitle(sysMenu.getTitle());
        menuVo.setIcon(sysMenu.getIcon());
        menuVo.setTarget(sysMenu.getTarget());
        menuVo.setSort(sysMenu.getSort());
        menuVo.setState(sysMenu.getState());
        return menuVo;
    }

    @RequestMapping("/update/{id}")
    public Result<String> update(MenuVo menuVo) {
        SysMenu sysMenu=new SysMenu();
        sysMenu.setId(menuVo.getId());
        sysMenu.setPId(menuVo.getPId());
        sysMenu.setHref(menuVo.getHref());
        sysMenu.setTitle(menuVo.getTitle());
        sysMenu.setIcon(menuVo.getIcon());
        sysMenu.setTarget(menuVo.getTarget());
        sysMenu.setSort(menuVo.getSort());
        sysMenu.setUpdatedAt(LocalDateTime.now());
        sysMenu.setState(menuVo.getState());
        sysMenuService.updateById(sysMenu);
        return Result.success("success");
    }

}

