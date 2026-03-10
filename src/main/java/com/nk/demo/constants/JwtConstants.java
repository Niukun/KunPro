package com.nk.demo.constants;

import java.util.concurrent.TimeUnit;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-10 13:40
 * 项目名称: KunPro
 * 文件名称: JwtConstants
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
public class JwtConstants {
    // 服务端密钥（建议配置在application.yml中，这里简化写死）
    public static final String SECRET_KEY = "my-secret-key-1234567890-abcdefg";
    // Token过期时间：2小时（单位：毫秒）
    public static final long EXPIRATION_TIME = TimeUnit.HOURS.toMillis(2);
    // 请求头中Token的key（前端请求时要把Token放在这个请求头里）
    public static final String TOKEN_HEADER = "Authorization";
    // Token前缀（规范写法，后端解析时要去掉）
    public static final String TOKEN_PREFIX = "Bearer ";
}
