package cn.cxnxs.webspider.web.interceptor;

import cn.cxnxs.webspider.web.ResponseResult;
import cn.cxnxs.webspider.web.vo.ErrorResult;
import cn.cxnxs.webspider.web.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * <p></p>
 *
 * @author mengjinyuan
 * @date 2020-10-13 23:45
 **/
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {

    public static final String RESPONSE_RESULT_ANN="RESPONSE-RESULT-ANN";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes sra= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert sra != null;
        HttpServletRequest request=sra.getRequest();
        ResponseResult responseResult= (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);
        return responseResult!=null;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body instanceof ErrorResult){
            ErrorResult errorResult= (ErrorResult) body;
            return Result.failure(Result.ResultEnum.SYSTEM_EXCEPTION,errorResult.getMsg(),errorResult.getErrors());
        }else if (body instanceof Result){
            return body;
        }
        return Result.success(body);
    }
}
