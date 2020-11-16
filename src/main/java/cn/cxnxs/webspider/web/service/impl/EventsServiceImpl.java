package cn.cxnxs.webspider.web.service.impl;

import cn.cxnxs.webspider.web.entity.Events;
import cn.cxnxs.webspider.web.mapper.EventsMapper;
import cn.cxnxs.webspider.web.service.IEventsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 代理事件 服务实现类
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
@Service
public class EventsServiceImpl extends ServiceImpl<EventsMapper, Events> implements IEventsService {

}
