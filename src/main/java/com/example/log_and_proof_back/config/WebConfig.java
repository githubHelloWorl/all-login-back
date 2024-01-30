package com.example.log_and_proof_back.config;

import com.example.log_and_proof_back.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //配置类
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        //interceptorRegistry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**").excludePathPatterns("/login");
        // 都可以通过
        interceptorRegistry.addInterceptor(loginCheckInterceptor).excludePathPatterns("/**");
    }
}
