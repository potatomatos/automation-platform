package cn.cxnxs.webspider.web.interceptor;

import cn.cxnxs.webspider.web.vo.Result;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>权限拦截器</p>
 *
 * @author mengjinyuan
 * @date 2020-10-13 22:29
 **/
@Slf4j
@Component
public class PermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getParameter("token");
        Result<String> result = null;
        boolean flag = true;
        response.setContentType("application/json");
        response.setCharacterEncoding("utf8");
        if (token != null) {
            String token1 = (String) request.getSession().getAttribute("token");
            if (!token.equals(token1)) {
                log.error("======token非法======");
                result = Result.failure(Result.ResultEnum.ILLEGAL_TOKEN, Result.ResultEnum.ILLEGAL_TOKEN.getInfo(), null);
                flag = false;
            }
        } else {
            log.error("======token非法======");
            result = Result.failure(Result.ResultEnum.ILLEGAL_TOKEN, Result.ResultEnum.ILLEGAL_TOKEN.getInfo(), null);
            flag = false;
        }
        if (request.getHeader("x-requested-with") != null
                && "XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {
            //返回json
            response.setStatus(403);
            response.getWriter().write(JSON.toJSONString(result));
        } else {
            //返回页面
            response.setStatus(403);
        }
        return flag;
    }
}
