package com.nk.demo.controller;

import com.nk.demo.constants.JwtConstants;
import com.nk.demo.utils.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-10 13:45
 * 项目名称: KunPro
 * 文件名称: TestController
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
@RestController
public class TestController {
    @GetMapping("/user/info")
    public Map<String, Object> getUserInfo(@RequestHeader(JwtConstants.TOKEN_HEADER) String token) {
        // 解析Token，获取用户信息
        Integer userId = (Integer) JwtUtil.getClaimFromToken(token, "userId");
        String username = (String) JwtUtil.getClaimFromToken(token, "username");
        String role = (String) JwtUtil.getClaimFromToken(token, "role");

        HashMap<Object, Object> data = new HashMap<>();
        data.put("userId", userId);
        data.put("username", username);
        data.put("role", role);
        // 返回用户信息
        Map<String, Object> result = new HashMap<>();
        result.put("code", "200");
        result.put("data", data);
        return result;
    }


}
