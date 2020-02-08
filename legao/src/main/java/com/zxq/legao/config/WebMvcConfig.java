package com.zxq.legao.config;

import com.zxq.legao.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description:
 * <p>
 *     登录拦截
 * </p>
 * @author dengzhenxiang
 * @Date 2019/2/16 17:15
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/toLogin","/",
                        "/css/**", "/fonts/**", "/img/**", "/js/**","/laydate/**","/error/**");
    }
}