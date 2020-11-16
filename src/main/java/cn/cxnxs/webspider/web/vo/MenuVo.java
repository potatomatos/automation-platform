package cn.cxnxs.webspider.web.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>菜单实体</p>
 *
 * @author mengjinyuan
 * @date 2020-11-11 15:25
 **/
@Data
public class MenuVo {

    private Integer id;

    /**
     * 父级id
     */
    private Integer pId;

    /**
     * 菜单路径
     */
    private String href;

    /**
     * 菜单名称
     */
    private String title;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 打开方式:_blank，_self
     */
    private String target;

    /**
     * 创建用户
     */
    private Integer userId;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 子菜单
     */
    private List<MenuVo> child;
}
