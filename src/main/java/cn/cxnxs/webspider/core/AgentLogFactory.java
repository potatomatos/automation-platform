package cn.cxnxs.webspider.core;


import com.sun.javafx.binding.StringFormatter;
import javafx.beans.binding.StringExpression;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>日志</p>
 *
 * @author mengjinyuan
 * @date 2021-02-28 23:45
 **/
public class AgentLogFactory {

    private final ThreadLocal<CopyOnWriteArrayList<AgentLogsVO>> logs=new ThreadLocal<>();

    private Integer agentId;

    private Integer inboundEventId;

    public AgentLogFactory() {
       this.logs.set(new CopyOnWriteArrayList<>());
    }

    public List<AgentLogsVO> getLogs() {
        CopyOnWriteArrayList<AgentLogsVO> logList = logs.get();
        logs.remove();
        return logList;
    }

    public void info(String message,Object ...param){
        StringExpression format = StringFormatter.format(message, param);
        String formatValue = format.getValue();
        AgentLogsVO agentLogsVO=new AgentLogsVO();
        agentLogsVO.setAgentId(agentId);
        agentLogsVO.setMessage(formatValue);
        agentLogsVO.setLevel(1);
        agentLogsVO.setThread(Thread.currentThread().getName());
        agentLogsVO.setInboundEventId(inboundEventId);
        agentLogsVO.setCreatedAt(LocalDateTime.now());
        logs.get().add(agentLogsVO);
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getInboundEventId() {
        return inboundEventId;
    }

    public void setInboundEventId(Integer inboundEventId) {
        this.inboundEventId = inboundEventId;
    }
}
