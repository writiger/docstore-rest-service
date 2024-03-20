package com.ds.user.config;

import com.ds.user.interceptors.AdminInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author writiger
 * @description
 * @create_at 2024-03-17 16:22
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public AdminInterceptor getAdminInterceptor(){
        return new AdminInterceptor();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAdminInterceptor()).addPathPatterns("/admin/**");
    }
}
