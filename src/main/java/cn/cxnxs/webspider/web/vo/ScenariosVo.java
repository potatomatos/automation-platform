package cn.cxnxs.webspider.web.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p></p>
 *
 * @author mengjinyuan
 * @date 2020-12-10 00:18
 **/
public class ScenariosVo implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 所属用户
     */
    private Integer userId;

    /**
     * 描述说明
     */
    private String description;

    /**
     * 是否公开
     */
    private Boolean isPublic;

    /**
     * 标签背景颜色
     */
    private String tagBgColor;

    /**
     * 标签前景颜色
     */
    private String tagFgColor;

    /**
     * 图标
     */
    private String icon;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    /**
     * 代理数量
     */
    private Long agentCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public String getTagBgColor() {
        return tagBgColor;
    }

    public void setTagBgColor(String tagBgColor) {
        this.tagBgColor = tagBgColor;
    }

    public String getTagFgColor() {
        return tagFgColor;
    }

    public void setTagFgColor(String tagFgColor) {
        this.tagFgColor = tagFgColor;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getAgentCount() {
        return agentCount;
    }

    public void setAgentCount(Long agentCount) {
        this.agentCount = agentCount;
    }

    @Override
    public String toString() {
        return "ScenariosVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", description='" + description + '\'' +
                ", isPublic=" + isPublic +
                ", tagBgColor='" + tagBgColor + '\'' +
                ", tagFgColor='" + tagFgColor + '\'' +
                ", icon='" + icon + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", agentCount=" + agentCount +
                '}';
    }
}
