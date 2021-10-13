package cn.cxnxs.webspider.core;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>日志类</p>
 *
 * @author mengjinyuan
 * @date 2021-02-16 23:37
 **/
@Data
public class AgentLogsVO {

    private Integer agentId;

    private String message;

    private Integer level;

    private String thread;

    private Integer inboundEventId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
