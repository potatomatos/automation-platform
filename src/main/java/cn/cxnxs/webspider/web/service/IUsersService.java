package cn.cxnxs.webspider.web.service;

import cn.cxnxs.webspider.web.entity.Users;
import cn.cxnxs.webspider.web.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author mengjinyuan
 * @since 2020-11-10
 */
public interface IUsersService extends IService<Users> {

    UserVo login(String username, String password, String captcha) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}
