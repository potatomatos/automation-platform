package cn.cxnxs.webspider.core.quartz;

import cn.cxnxs.webspider.core.Event;
import cn.cxnxs.webspider.core.IAgent;
import cn.cxnxs.webspider.utils.ObjectUtil;
import cn.cxnxs.webspider.utils.SpringContextUtil;
import cn.cxnxs.webspider.web.entity.Agent;
import cn.cxnxs.webspider.web.entity.AgentType;
import cn.cxnxs.webspider.web.entity.Events;
import cn.cxnxs.webspider.web.service.IAgentService;
import cn.cxnxs.webspider.web.service.IEventsService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * <p>定时任务类</p>
 *
 * @author mengjinyuan
 * @date 2021-02-01 22:53
 **/
public class DelayedJob extends QuartzJobBean {

    @Autowired
    private IAgentService agentService;

    @Autowired
    private IEventsService eventsService;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private static final Logger logger = LoggerFactory.getLogger(DelayedJob.class);

    /**
     * 代理信息id
     */
    private Integer id;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            logger.info("------定时任务开始执行------");
            Agent agent = agentService.getById(id);
            List<Events> events;
            int pageNo = 1;
            int pageSize = 1000;
            IAgent agentInstance = buildAgent(agent);
            IPage<Events> eventsPage = eventsService.page(new Page<>(pageNo, pageSize),
                    new QueryWrapper<Events>().eq("agent_id", id));
            events = eventsPage.getRecords();
            Future<List<Map<String, String>>> future = null;
            if (events.size() == 0) {
                TaskRunnable taskRunnable = new TaskRunnable();
                taskRunnable.setAgent(agentInstance);
                future = threadPoolTaskExecutor.submit(taskRunnable);
            } else {
                while (events.size() > 0) {
                    //将事件添加到代理
                    for (Events event : events) {
                        Event ev = new Event();
                        ObjectUtil.transValues(event, Event.class);
                        TaskRunnable taskRunnable = new TaskRunnable();
                        taskRunnable.setAgent(agentInstance);
                        taskRunnable.setEvent(ev);
                        future = threadPoolTaskExecutor.submit(taskRunnable);
                    }
                    pageNo++;
                    eventsPage = eventsService.page(new Page<>(pageNo, pageSize),
                            new QueryWrapper<Events>().eq("agent_id", id));
                    events = eventsPage.getRecords();
                }
            }
            logger.info("执行结果：{}", future.get());
        } catch (ClassNotFoundException e) {
            logger.error("定时任务执行失败,找不到指定的执行类", e);
        } catch (InterruptedException | ExecutionException e) {
            logger.error("线程运行发生异常", e);
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public IAgent buildAgent(Agent agent) throws ClassNotFoundException {
        Integer typeId = agent.getType();
        AgentType agentType = new AgentType().selectById(typeId);
        Class<IAgent> agentClass = (Class<IAgent>) Class.forName(agentType.getHandler());
        IAgent agentInstance = SpringContextUtil.getBean(agentClass);
        agentInstance.option(JSONObject.parseObject(agent.getOptions()));
        return agentInstance.option(JSONObject.parseObject(agent.getOptions()));
    }
}
