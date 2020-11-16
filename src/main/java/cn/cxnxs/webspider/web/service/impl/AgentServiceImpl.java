package cn.cxnxs.webspider.web.service.impl;

import cn.cxnxs.webspider.web.entity.Agent;
import cn.cxnxs.webspider.web.mapper.AgentMapper;
import cn.cxnxs.webspider.web.service.IAgentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
