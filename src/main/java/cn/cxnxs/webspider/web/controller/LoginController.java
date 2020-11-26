package cn.cxnxs.webspider.web.controller;

import cn.cxnxs.webspider.exception.LoginException;
import cn.cxnxs.webspider.utils.ImageUtil;
import cn.cxnxs.webspider.utils.StringUtil;
import cn.cxnxs.webspider.web.service.IUsersService;
import cn.cxnxs.webspider.web.vo.Result;
import cn.cxnxs.webspider.web.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

/**
 * <p>登录</p>
 *
 * @author mengjinyuan
 * @date 2020-11-24 17:41
 **/
@RestController
public class LoginController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IUsersService usersService;

    @RequestMapping("/captcha")
    @CrossOrigin(origins = "*")
    public void captcha(HttpServletResponse httpServletResponse) throws IOException {
        String captcha = StringUtil.randomString(4);
        String key = request.getRequestedSessionId() + "_captcha";
        HttpSession session = request.getSession();
        //5分钟有效
        session.setMaxInactiveInterval(5 * 60);
        //验证码保存到session
        session.setAttribute(key, captcha.toLowerCase(Locale.ENGLISH));
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageUtil.buildImageVerify(captcha, 100, 38, bos);
        httpServletResponse.setContentType("image/png");
        OutputStream os = httpServletResponse.getOutputStream();
        os.write(bos.toByteArray());
        os.flush();
        os.close();
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param captcha
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public Result<UserVo> login(String username, String password, String captcha) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        UserVo user;
        try {
            user = usersService.login(username, password, captcha);
            if (user == null) {
                throw new LoginException(Result.ResultEnum.LOGIN_VERIFY_ERROR);
            }
        } catch (LoginException e) {
            return Result.failure(e.getCode(), "failure", e.getMessage(), null);
        }
        return Result.success("登录成功", user);
    }

}
