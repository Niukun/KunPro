package com.nk.demo.controller;

import com.nk.demo.constants.JwtConstants;
import com.nk.demo.utils.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-10 12:03
 * 项目名称: KunPro
 * 文件名称: LoginController
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
@RestController
public class LoginController {
    /**
     * 登录接口（模拟验证用户名密码，实际项目中要查数据库）
     *
     * @param username 用户名
     * @param password 密码
     * @return Token
     */
    @PostMapping("/login")
    public Map<String, String> login(
            @RequestParam String username,
            @RequestParam String password) {
        // 1. 模拟验证用户名密码（实际项目中替换为查数据库）
        if (!"admin".equals(username) || !"123456".equals(password)) {
            Map<String, String> result = new HashMap<>();
            result.put("code", "400");
            result.put("msg", "用户名或密码错误");
            return result;
        }

        // 2. 验证通过，构造自定义载荷（放用户核心信息，不要放敏感信息）
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", 1);       // 用户ID
        claims.put("username", "admin");// 用户名
        claims.put("role", "ADMIN");   // 用户角色

        // 3. 生成Token
        String token = JwtUtil.generateToken(claims);

        // 4. 返回Token给客户端
        Map<String, String> result = new HashMap<>();
        result.put("code", "200");
        result.put("msg", "登录成功");
        result.put("token", JwtConstants.TOKEN_PREFIX + token); // 加上前缀
        return result;
    }


}
