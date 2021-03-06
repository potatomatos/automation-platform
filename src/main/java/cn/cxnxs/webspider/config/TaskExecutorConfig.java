package cn.cxnxs.webspider.config;

import cn.cxnxs.webspider.core.quartz.DelayedJob;
import cn.cxnxs.webspider.core.quartz.TaskDetail;
import cn.cxnxs.webspider.core.quartz.TaskScheduler;
import cn.cxnxs.webspider.web.entity.Agent;
import cn.cxnxs.webspider.web.entity.AgentType;
import cn.cxnxs.webspider.web.service.IAgentService;
import cn.cxnxs.webspider.web.vo.AgentTypeVo;
import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>定时任务配置</p>
 *
 * @author mengjinyuan
 * @date 2021-02-03 10:15
 **/
@Configuration
public class TaskExecutorConfig {

    /**
     * 配置核心线程数
     */
    private static final int CORE_POOL_SIZE = 20;
    /**
     * 配置最大线程数
     */
    private static final int MAX_POOL_SIZE = 100;
    /**
     * 配置队列大小
     */
    private static final int QUEUE_CAPACITY = 1000;

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    private IAgentService agentService;

    /**
     * 定义线程池
     * 使用方法：
     * private ThreadPoolTaskExecutor threadPoolTaskExecutor;
     *
     * @return none
     */
    @Bean("threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(CORE_POOL_SIZE);
        executor.setMaxPoolSize(MAX_POOL_SIZE);
        executor.setKeepAliveSeconds(60);
        executor.setQueueCapacity(QUEUE_CAPACITY);
        //拒绝策略,静默拒绝
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.initialize();
        return executor;
    }

    /**
     * 初始化定时任务
     */
    @PostConstruct
    public void initScheduler() {
        //从数据库获取代理信息
        List<Agent> agents = agentService.list();
        for (Agent agentItem : agents) {
            //获取类型
            AgentType agentType = new AgentType().selectById(agentItem.getType());
            if (agentType.getCanBeScheduled()) {
                //如果代理为定时执行，则创建定时任务
                String cron = AgentTypeVo.ScheduleEnum.getCron(Integer.parseInt(agentItem.getSchedule()));
                TaskDetail taskDetail = new TaskDetail();
                taskDetail.setJobName(agentItem.getName());
                taskDetail.setJobGroupName(agentType.getAgentTypeName());
                taskDetail.setTriggerName(agentItem.getName());
                taskDetail.setTriggerGroupName(agentType.getAgentTypeName());
                taskDetail.setJobClass(DelayedJob.class);
                taskDetail.setCron(cron);
                JobDataMap jobDataMap = new JobDataMap();
                jobDataMap.put("id", agentItem.getId());
                taskDetail.setJobDataMap(jobDataMap);
                taskScheduler.addJob(taskDetail);
            }
        }

    }

}
