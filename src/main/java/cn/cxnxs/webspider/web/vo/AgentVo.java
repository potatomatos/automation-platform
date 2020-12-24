package cn.cxnxs.webspider.web.vo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 爬虫任务配置信息
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
public class AgentVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 所属用户
     */
    private Integer userId;

    /**
     * 服务配置
     */
    private String options;

    /**
     * 服务类型
     */
    private Integer type;

    /**
     * 服务名称
     */
    private String name;

    private Integer value;

    /**
     * 服务运行周期计划
     */
    private String schedule;

    /**
     * 数据总数
     */
    private Integer dataCount;

    /**
     * 上次检测时间
     */
    private LocalDateTime lastCheckAt;

    /**
     * 上次接收到数据时间
     */
    private LocalDateTime lastReceiveAt;

    /**
     * 上次检查数据源数据id
     */
    private Integer lastCheckedDataId;

    /**
     * 上次发送请求时间
     */
    private LocalDateTime lastWebRequestAt;

    /**
     * 结果数据保存时长
     */
    private Integer keepDataTime;

    /**
     * 上次产生数据时间
     */
    private LocalDateTime lastDataIme;

    /**
     * 上次产生错误时间
     */
    private LocalDateTime lastErrorLogTime;

    /**
     * 数据是否立即传播到下一个服务
     */
    private Boolean propagateImmediately;

    /**
     * 禁用状态
     */
    private Boolean disabled;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;

    private String keepDataTimeStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Integer getDataCount() {
        return dataCount;
    }

    public void setDataCount(Integer dataCount) {
        this.dataCount = dataCount;
    }

    public LocalDateTime getLastCheckAt() {
        return lastCheckAt;
    }

    public void setLastCheckAt(LocalDateTime lastCheckAt) {
        this.lastCheckAt = lastCheckAt;
    }

    public LocalDateTime getLastReceiveAt() {
        return lastReceiveAt;
    }

    public void setLastReceiveAt(LocalDateTime lastReceiveAt) {
        this.lastReceiveAt = lastReceiveAt;
    }

    public Integer getLastCheckedDataId() {
        return lastCheckedDataId;
    }

    public void setLastCheckedDataId(Integer lastCheckedDataId) {
        this.lastCheckedDataId = lastCheckedDataId;
    }

    public LocalDateTime getLastWebRequestAt() {
        return lastWebRequestAt;
    }

    public void setLastWebRequestAt(LocalDateTime lastWebRequestAt) {
        this.lastWebRequestAt = lastWebRequestAt;
    }

    public Integer getKeepDataTime() {
        return keepDataTime;
    }

    public void setKeepDataTime(Integer keepDataTime) {
        this.keepDataTime = keepDataTime;
    }

    public LocalDateTime getLastDataIme() {
        return lastDataIme;
    }

    public void setLastDataIme(LocalDateTime lastDataIme) {
        this.lastDataIme = lastDataIme;
    }

    public LocalDateTime getLastErrorLogTime() {
        return lastErrorLogTime;
    }

    public void setLastErrorLogTime(LocalDateTime lastErrorLogTime) {
        this.lastErrorLogTime = lastErrorLogTime;
    }

    public Boolean getPropagateImmediately() {
        return propagateImmediately;
    }

    public void setPropagateImmediately(Boolean propagateImmediately) {
        this.propagateImmediately = propagateImmediately;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
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

    public String getKeepDataTimeStr() {
        this.keepDataTimeStr = AgentTypeVo.KeepEventsTime.getDesc(this.keepDataTime);
        return this.keepDataTimeStr;
    }

    public void setKeepDataTimeStr(String keepDataTimeStr) {
        this.keepDataTimeStr = keepDataTimeStr;
    }

    public Integer getValue() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AgentVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", options='" + options + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", schedule='" + schedule + '\'' +
                ", dataCount=" + dataCount +
                ", lastCheckAt=" + lastCheckAt +
                ", lastReceiveAt=" + lastReceiveAt +
                ", lastCheckedDataId=" + lastCheckedDataId +
                ", lastWebRequestAt=" + lastWebRequestAt +
                ", keepDataTime=" + keepDataTime +
                ", lastDataIme=" + lastDataIme +
                ", lastErrorLogTime=" + lastErrorLogTime +
                ", propagateImmediately=" + propagateImmediately +
                ", disabled=" + disabled +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
