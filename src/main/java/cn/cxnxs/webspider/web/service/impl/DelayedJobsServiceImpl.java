package cn.cxnxs.webspider.web.service.impl;

import cn.cxnxs.webspider.web.entity.DelayedJobs;
import cn.cxnxs.webspider.web.mapper.DelayedJobsMapper;
import cn.cxnxs.webspider.web.service.IDelayedJobsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mengjinyuan
 * @since 2021-02-02
 */
@Service
public class DelayedJobsServiceImpl extends ServiceImpl<DelayedJobsMapper, DelayedJobs> implements IDelayedJobsService {

}
