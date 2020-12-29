package cn.cxnxs.webspider.web.service.impl;

import cn.cxnxs.webspider.utils.TreeUtil;
import cn.cxnxs.webspider.web.entity.SysMenu;
import cn.cxnxs.webspider.web.mapper.SysMenuMapper;
import cn.cxnxs.webspider.web.service.ISysMenuService;
import cn.cxnxs.webspider.web.vo.MenuVo;
import cn.cxnxs.webspider.web.vo.TreeVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单信息 服务实现类
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-11
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {


    /**
     * 获取系统菜单
     *
     * @return 菜单数据
     */
    @Override
    public Map<String, Object> menu() {
        Map<String, Object> map = new HashMap<>(16);
        Map<String, Object> home = new HashMap<>(16);
        Map<String, Object> logo = new HashMap<>(16);
        List<SysMenu> menuList = list(new QueryWrapper<SysMenu>().orderByAsc("sort"));
        List<MenuVo> menuInfo = new ArrayList<>();
        for (SysMenu e : menuList) {
            MenuVo menuVO = new MenuVo();
            menuVO.setId(e.getId());
            menuVO.setPId(e.getPId());
            menuVO.setHref(e.getHref());
            menuVO.setTitle(e.getTitle());
            menuVO.setIcon(e.getIcon());
            menuVO.setTarget(e.getTarget());
            menuInfo.add(menuVO);
        }
        map.put("menuInfo", TreeUtil.toTree(menuInfo, 0));
        home.put("title", "首页");
        home.put("href", "/static/page/welcome-1.html");
        //控制器路由,自行定义
        logo.put("href", "");
        logo.put("title", "后台管理系统");
        //静态资源文件路径,可使用默认的logo.png
        logo.put("image", "/static/images/logo.png");
        map.put("homeInfo", home);
        map.put("logoInfo", logo);
        return map;
    }

    /**
     * 获取菜单树
     *
     * @return 菜单树
     */
    @Override
    public List<TreeVo> menuTree() {
        List<SysMenu> menuList = list(new QueryWrapper<SysMenu>().orderByAsc("sort"));
        List<TreeVo> menuTree = new ArrayList<>();
        for (SysMenu menu : menuList) {
            TreeVo treeVo = new TreeVo();
            treeVo.setId(menu.getId());
            treeVo.setPid(menu.getPId());
            treeVo.setTitle(menu.getTitle());
            menuTree.add(treeVo);
        }
        return TreeUtil.toTreeVo(menuTree, 0);
    }

    /**
     * 删除菜单及其所有子菜单
     *
     * @param id 菜单id
     */
    @Override
    public void delete(Integer id) {
        List<SysMenu> menuList = list();
        List<TreeVo> tree = new ArrayList<>();
        for (SysMenu menu : menuList) {
            TreeVo treeVo = new TreeVo();
            treeVo.setId(menu.getId());
            treeVo.setPid(menu.getPId());
            treeVo.setTitle(menu.getTitle());
            tree.add(treeVo);
        }
        List<TreeVo> treeVos = TreeUtil.toTreeVo(tree, id);
        removeById(id);
        this.delChild(treeVos);
    }

    private void delChild(List<TreeVo> treeVos) {
        for (TreeVo treeVo : treeVos) {
            removeById(treeVo.getId());
            if (treeVo.getChildren() != null) {
                delChild(treeVo.getChildren());
            }
        }
    }
}
