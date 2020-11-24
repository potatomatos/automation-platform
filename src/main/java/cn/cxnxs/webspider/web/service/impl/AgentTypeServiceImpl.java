package cn.cxnxs.webspider.web.service.impl;

import cn.cxnxs.webspider.web.entity.AgentType;
import cn.cxnxs.webspider.web.mapper.AgentTypeMapper;
import cn.cxnxs.webspider.web.service.IAgentTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * agent定义信息 服务实现类
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-17
 */
@Service
public class AgentTypeServiceImpl extends ServiceImpl<AgentTypeMapper, AgentType> implements IAgentTypeService {

}
