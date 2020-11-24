package cn.cxnxs.webspider.web.service;

import cn.cxnxs.webspider.web.entity.Agent;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 爬虫任务配置信息 服务类
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
public interface IAgentService extends IService<Agent> {

    /**
     * 获取agent配置信息
     * @param agentType agent类型
     * @return
     */
    Map<String,Object> getAgentConfig(String agentType);
}
