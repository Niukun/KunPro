package com.nk.demo.controller;

import com.nk.demo.utils.NetworkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;

@RestController
@RequestMapping(value = "/network")
//@CrossOrigin(origins = "*")
@Slf4j
public class IpController {


    @GetMapping("/ip")
    public String getClientIp(HttpServletRequest request) {
        String realIP = request.getHeader("X-Real-IP");
        log.info("X-Real-IP: {}", realIP);
        String clientIp = NetworkUtils.getClientIp(request);
        log.info("客户端IP: {}", clientIp);
        log.info("请求时间：" + DateFormat.getInstance().format(System.currentTimeMillis()));
        return realIP != null? realIP : clientIp;
    }

    @GetMapping("/ip-detail")
    public IpDetail getClientIpDetail(HttpServletRequest request) {
        String clientIp = NetworkUtils.getClientIp(request);
        boolean isPrivate = NetworkUtils.isPrivateIp(clientIp);

        return new IpDetail(
                clientIp,
                request.getRemoteHost(),
                request.getHeader("User-Agent"),
                isPrivate,
                isPrivate ? "内网地址" : "公网地址"
        );
    }

    // 返回详细信息的DTO
    static class IpDetail {
        private final String ipAddress;
        private final String hostName;
        private final String userAgent;
        private final boolean isPrivate;
        private final String ipType;

        public IpDetail(String ipAddress, String hostName, String userAgent,
                        boolean isPrivate, String ipType) {
            this.ipAddress = ipAddress;
            this.hostName = hostName;
            this.userAgent = userAgent;
            this.isPrivate = isPrivate;
            this.ipType = ipType;
        }

        // Getters
        public String getIpAddress() {
            return ipAddress;
        }

        public String getHostName() {
            return hostName;
        }

        public String getUserAgent() {
            return userAgent;
        }

        public boolean isPrivate() {
            return isPrivate;
        }

        public String getIpType() {
            return ipType;
        }
    }


}
