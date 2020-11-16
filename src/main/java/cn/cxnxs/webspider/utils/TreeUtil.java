package cn.cxnxs.webspider.utils;

import cn.cxnxs.webspider.web.vo.MenuVo;
import cn.cxnxs.webspider.web.vo.TreeVo;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>树形菜单工具</p>
 *
 * @author mengjinyuan
 * @date 2020-11-11 15:33
 **/
public class TreeUtil {

    public static List<MenuVo> toTree(List<MenuVo> treeList, Integer pid) {
        List<MenuVo> retList = new ArrayList<>();
        for (MenuVo parent : treeList) {
            if (pid.equals(parent.getPId())) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }

    private static MenuVo findChildren(MenuVo parent, List<MenuVo> treeList) {
        for (MenuVo child : treeList) {
            if (parent.getId().equals(child.getPId())) {
                if (parent.getChild() == null) {
                    parent.setChild(new ArrayList<>());
                }
                parent.getChild().add(findChildren(child, treeList));
            }
        }
        return parent;
    }

    public static List<TreeVo> toTreeVo(List<TreeVo> treeList, Integer pid) {
        List<TreeVo> retList = new ArrayList<>();
        for (TreeVo parent : treeList) {
            if (pid.equals(parent.getPid())) {
                retList.add(findChildren(parent, treeList));
            }
        }
        return retList;
    }

    private static TreeVo findChildren(TreeVo parent, List<TreeVo> treeList) {
        for (TreeVo child : treeList) {
            if (parent.getId().equals(child.getPid())) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(findChildren(child, treeList));
            }
        }
        return parent;
    }
}
