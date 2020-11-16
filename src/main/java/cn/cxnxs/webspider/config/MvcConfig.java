package cn.cxnxs.webspider.config;

import cn.cxnxs.webspider.web.interceptor.ResponseResultInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * <p></p>
 *
 * @author mengjinyuan
 * @date 2020-10-14 10:26
 **/
@Configuration
@Slf4j
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private ResponseResultInterceptor responseResultInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        log.info("------设置统一接口拦截器------");
        registry.addInterceptor(responseResultInterceptor)
                .addPathPatterns("/**").excludePathPatterns("/static/**");
    }
}
