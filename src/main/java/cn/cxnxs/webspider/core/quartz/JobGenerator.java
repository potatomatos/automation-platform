package cn.cxnxs.webspider.core.quartz;

import cn.cxnxs.webspider.core.IAgent;
import cn.cxnxs.webspider.utils.SpringContextUtil;
import cn.cxnxs.webspider.web.entity.AgentType;
import cn.cxnxs.webspider.web.vo.AgentVo;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <p>任务生成器</p>
 *
 * @author mengjinyuan
 * @date 2021-02-04 11:35
 **/
@Component
public class JobGenerator {

    private static final Logger logger = LoggerFactory.getLogger(JobGenerator.class);

    public IAgent buildAgent(AgentVo agent) throws ClassNotFoundException {
        Integer typeId = agent.getType();
        AgentType agentType = new AgentType().selectById(typeId);
        logger.info("创建代理实例:{}",agentType.getHandler());
        Class<IAgent> agentClass = (Class<IAgent>) Class.forName(agentType.getHandler());
        IAgent agentInstance = SpringContextUtil.getBean(agentClass);
        agentInstance.option(JSONObject.parseObject(agent.getOptions()));
        return agentInstance.option(JSONObject.parseObject(agent.getOptions()));
    }
}
