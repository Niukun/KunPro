package com.nk.demo.config;

import com.nk.demo.interceptor.JwtAuthInterceptor;
import com.nk.demo.interceptor.JwtInterceptor;
import com.nk.demo.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-10 11:49
 * 项目名称: KunPro
 * 文件名称: WebMvcConfig
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 1. 注册日志拦截器（先注册，preHandle先执行）
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**"); // 拦截所有请求

        // 2. 注册JWT认证拦截器（后注册，preHandle后执行）
        registry.addInterceptor(new JwtAuthInterceptor())
                .addPathPatterns("/api/**") // 只拦截/api开头的请求
                .excludePathPatterns("/api/login"); // 排除登录接口（登录不需要Token）

        registry.addInterceptor(new JwtInterceptor())
                // 拦截所有请求，排除登录接口（登录接口不需要认证）
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }
}
