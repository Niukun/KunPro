package com.nk.demo.interceptor;

import com.nk.demo.constants.JwtConstants;
import com.nk.demo.utils.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;



/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-10 13:43
 * 项目名称: KunPro
 * 文件名称: JwtInterceptor
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 从请求头中获取Token
        String token = request.getHeader(JwtConstants.TOKEN_HEADER);

        // 2. 验证Token是否存在且有效
        if (token == null || !JwtUtil.validateToken(token)) {
            // 验证失败，返回401未授权
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"code\":\"401\",\"msg\":\"未授权或Token无效\"}");
            return false;
        }

        // 3. Token有效，放行请求
        return true;
    }
}
