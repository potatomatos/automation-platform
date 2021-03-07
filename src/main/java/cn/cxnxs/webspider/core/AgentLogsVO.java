package cn.cxnxs.webspider.core;

import java.time.LocalDateTime;

/**
 * <p>日志类</p>
 *
 * @author mengjinyuan
 * @date 2021-02-16 23:37
 **/
public class AgentLogsVO {

    private Integer agentId;

    private String message;

    private Integer level;

    private Integer inboundEventId;

    private Integer outboundEventId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getInboundEventId() {
        return inboundEventId;
    }

    public void setInboundEventId(Integer inboundEventId) {
        this.inboundEventId = inboundEventId;
    }

    public Integer getOutboundEventId() {
        return outboundEventId;
    }

    public void setOutboundEventId(Integer outboundEventId) {
        this.outboundEventId = outboundEventId;
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

    @Override
    public String toString() {
        return "AgentLogsVO{" +
                "agentId=" + agentId +
                ", message='" + message + '\'' +
                ", level=" + level +
                ", inboundEventId=" + inboundEventId +
                ", outboundEventId=" + outboundEventId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
