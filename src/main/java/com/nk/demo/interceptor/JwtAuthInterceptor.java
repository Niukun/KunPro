package com.nk.demo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-10 11:53
 * 项目名称: KunPro
 * 文件名称: JwtAuthInterceptor
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
@Slf4j
public class JwtAuthInterceptor implements HandlerInterceptor {
    // ========== 核心方法1：preHandle（请求到达Controller前拦截） ==========
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("===== JwtAuthInterceptor preHandle 执行 =====");
        // 1. 从请求头获取Token（和上一篇JWT工具类的配置一致）
        String token = request.getHeader("Authorization");

        // 2. 校验Token（这里复用上一篇的JwtUtil，你可以先写个简单的模拟校验）
        if (token == null || !token.startsWith("Bearer ")) {
            // Token为空或格式错误，返回401
            response.setStatus(401);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":\"401\",\"msg\":\"Token不能为空或格式错误\"}");
            log.warn("请求被拦截：Token为空或格式错误，请求路径：{}", request.getRequestURI());
            return false; // 返回false，拦截请求，不会进入Controller
        }

        // 3. 模拟校验Token有效性（实际项目中调用JwtUtil.validateToken()）
        String realToken = token.replace("Bearer ", "");
        if (!"valid-token-123".equals(realToken)) { // 模拟无效Token
            response.setStatus(401);
            response.getWriter().write("{\"code\":\"401\",\"msg\":\"Token无效或已过期\"}");
            log.warn("请求被拦截：Token无效，请求路径：{}", request.getRequestURI());
            return false;
        }

        // 4. Token有效，放行请求（返回true）
        log.info("Token有效，放行请求：{}", request.getRequestURI());
        return true;
    }

    // ========== 核心方法2：postHandle（Controller处理完请求后） ==========
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, org.springframework.web.servlet.ModelAndView modelAndView) throws Exception {
        log.info("===== JwtAuthInterceptor postHandle 执行 =====");
        // 示例：给响应添加统一的响应头（增强响应）
        response.addHeader("X-Response-Time", String.valueOf(System.currentTimeMillis()));
    }

    // ========== 核心方法3：afterCompletion（响应返回后） ==========
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("===== JwtAuthInterceptor afterCompletion 执行 =====");
        // 示例：记录请求耗时（这里简化，实际可以在preHandle记录开始时间，这里计算差值）
        log.info("请求 {} 处理完成，响应状态码：{}", request.getRequestURI(), response.getStatus());
    }
}
