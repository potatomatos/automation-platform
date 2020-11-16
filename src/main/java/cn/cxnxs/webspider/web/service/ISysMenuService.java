package cn.cxnxs.webspider.web.service;

import cn.cxnxs.webspider.web.entity.SysMenu;
import cn.cxnxs.webspider.web.vo.TreeVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单信息 服务类
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-11
 */
public interface ISysMenuService extends IService<SysMenu> {

    Map<String, Object> menu();

    List<TreeVo> menuTree();

    void delete(Integer id);
}
