package com.nk.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-10 11:50
 * 项目名称: KunPro
 * 文件名称: LogInterceptor
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */

@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    // 记录请求开始时间（preHandle中记录，afterCompletion中计算耗时）
    private ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("===== LogInterceptor preHandle 执行 =====");
        // 1. 记录请求开始时间
        startTime.set(System.currentTimeMillis());
        // 2. 打印请求基本信息
        log.info("请求URL：{}，请求方式：{}，客户端IP：{}",
                request.getRequestURI(),
                request.getMethod(),
                request.getRemoteAddr());
        return true; // 日志拦截器不拦截，直接放行
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("===== LogInterceptor afterCompletion 执行 =====");
        // 计算请求耗时
        long costTime = System.currentTimeMillis() - startTime.get();
        log.info("请求 {} 耗时：{}ms", request.getRequestURI(), costTime);
        // 移除ThreadLocal中的值，防止内存泄漏
        startTime.remove();
    }

}
