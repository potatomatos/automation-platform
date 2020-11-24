package cn.cxnxs.webspider.web.service.impl;

import cn.cxnxs.webspider.web.entity.Agent;
import cn.cxnxs.webspider.web.mapper.AgentMapper;
import cn.cxnxs.webspider.web.service.IAgentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 爬虫任务配置信息 服务实现类
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
@Service
public class AgentServiceImpl extends ServiceImpl<AgentMapper, Agent> implements IAgentService {

    /**
     * 获取agent配置信息
     *
     * @param agentType agent类型
     * @return agent配置json数据
     */
    @Override
    public Map<String, Object> getAgentConfig(String agentType) {
        return null;
    }
}
