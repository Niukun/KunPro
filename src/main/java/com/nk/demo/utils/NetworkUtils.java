package com.nk.demo.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;

public class NetworkUtils {
    public static String getClientIp(HttpServletRequest request) {
        // 1. 尝试从代理头获取
        String[] headers = {
                "X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_X_FORWARDED_FOR",
                "HTTP_CLIENT_IP",
                "X-Real-IP"
        };

        for (String header : headers) {
            String ip = request.getHeader(header);
            if (isValidIp(ip)) {
                return extractClientIp(ip);
            }
        }

        // 2. 直接获取远程地址
        String remoteAddr = request.getRemoteAddr();

        // 3. 处理IPv6本地地址
        if ("0:0:0:0:0:0:0:1".equals(remoteAddr) || "::1".equals(remoteAddr)) {
            return "127.0.0.1";
        }

        return remoteAddr;
    }

    private static boolean isValidIp(String ip) {
        return ip != null &&
                !ip.isEmpty() &&
                !"unknown".equalsIgnoreCase(ip);
    }

    private static String extractClientIp(String ipHeader) {
        // 处理多个IP的情况（如：X-Forwarded-For: client, proxy1, proxy2）
        if (ipHeader.contains(",")) {
            return Arrays.stream(ipHeader.split(","))
                    .map(String::trim)
                    .filter(NetworkUtils::isValidIp)
                    .findFirst()
                    .orElse(ipHeader);
        }
        return ipHeader;
    }

    // 可选：判断是否为内网IP
    public static boolean isPrivateIp(String ip) {
        if (ip == null) return false;
        return ip.matches("(^10\\.)|(^192\\.168\\.)|(^172\\.(1[6-9]|2[0-9]|3[0-1])\\..*)") ||
                ip.startsWith("127.") ||
                ip.startsWith("fc00:") || // IPv6私有地址
                ip.startsWith("fd00:") ||
                ip.startsWith("fe80:");   // IPv6链路本地地址
    }
}
