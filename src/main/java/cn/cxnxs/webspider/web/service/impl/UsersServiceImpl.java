package cn.cxnxs.webspider.web.service.impl;

import cn.cxnxs.webspider.exception.LoginException;
import cn.cxnxs.webspider.utils.ObjectUtil;
import cn.cxnxs.webspider.utils.PasswordUtil;
import cn.cxnxs.webspider.web.entity.Users;
import cn.cxnxs.webspider.web.mapper.UsersMapper;
import cn.cxnxs.webspider.web.service.IUsersService;
import cn.cxnxs.webspider.web.vo.Result;
import cn.cxnxs.webspider.web.vo.UserVo;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Locale;

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
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UserVo login(String username, String password, String captcha) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String key = request.getRequestedSessionId() + "_captcha";
        if (captcha != null) {
            String captchaSrc= (String) request.getSession().getAttribute(key);
            if (!captcha.toLowerCase(Locale.ENGLISH).equals(captchaSrc)) {
                throw new LoginException("验证码错误");
            }
        } else {
            throw new LoginException("验证码错误");
        }
        String salt = PasswordUtil.Salt;
        String secret = PasswordUtil.encrypt(username, password, salt);
        String token= PasswordUtil.encrypt(System.currentTimeMillis()+"", password, salt);
        Users user=usersMapper.selectLoginUser(username, secret);
        if (user==null){
            throw new LoginException(Result.ResultEnum.LOGIN_VERIFY_ERROR);
        }
        user.setLastLoginIp(user.getCurrentLoginIp());
        user.setCurrentLoginIp(request.getRemoteAddr());
        user.setLastLoginTime(user.getCurrentLoginTime());
        user.setCurrentLoginTime(LocalDateTime.now());
        user.setLoginCount(user.getLoginCount()+1);
        String resetPasswordToken =PasswordUtil.encrypt("reset_password_token"+System.currentTimeMillis(),password,PasswordUtil.Salt);
        String unlockToken =PasswordUtil.encrypt("unlock_token"+System.currentTimeMillis(),password,PasswordUtil.Salt);
        user.setResetPasswordToken(resetPasswordToken);
        user.setUnlockToken(unlockToken);
        request.getSession().setAttribute("token",token);
        UserVo userVo=new UserVo();
        BeanUtils.copyProperties(user,userVo);
        userVo.setToken(token);
        //更新信息
        user.updateById();

        //删除验证码
        request.getSession().removeAttribute(key);
        return userVo;
    }
}
