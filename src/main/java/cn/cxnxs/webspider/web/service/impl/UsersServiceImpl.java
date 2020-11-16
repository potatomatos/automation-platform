package cn.cxnxs.webspider.web.service.impl;

import cn.cxnxs.webspider.web.entity.Users;
import cn.cxnxs.webspider.web.mapper.UsersMapper;
import cn.cxnxs.webspider.web.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
