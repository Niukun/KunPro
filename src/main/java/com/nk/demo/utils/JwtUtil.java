package com.nk.demo.utils;



import com.nk.demo.constants.JwtConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * 创建人：  @author niuk
 * 创建时间: 2026-03-10 11:58
 * 项目名称: KunPro
 * 文件名称: JwtUtil
 * 公司名称: 安徽产业互联数据智能创新中心有限公司
 */
@Slf4j
public class JwtUtil {
    // 生成密钥（JJWT要求密钥长度至少256位，这里用工具类生成符合要求的Key）
    private static Key getSecretKey() {
        return Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());
    }

    /**
     * 生成JWT Token
     * @param claims 自定义载荷（比如userId、username、role）
     * @return Token字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        // 当前时间 + 过期时间 = Token过期时间
        Date expirationDate = new Date(System.currentTimeMillis() + JwtConstants.EXPIRATION_TIME);

        return Jwts.builder()
                // 放入自定义载荷
                .setClaims(claims)
                // 设置签发时间
                .setIssuedAt(new Date())
                // 设置过期时间
                .setExpiration(expirationDate)
                // 使用HS256算法签名
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                // 构建并压缩成字符串
                .compact();
    }

    /**
     * 解析Token，获取载荷中的所有信息
     * @param token Token字符串
     * @return 载荷信息
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parserBuilder()
                    // 设置签名密钥
                    .setSigningKey(getSecretKey())
                    .build()
                    // 解析Token（去掉前缀）
                    .parseClaimsJws(token.replace(JwtConstants.TOKEN_PREFIX, ""))
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.error("Token已过期", e);
            throw new RuntimeException("Token已过期");
        } catch (UnsupportedJwtException e) {
            log.error("Token格式不支持", e);
            throw new RuntimeException("Token格式错误");
        } catch (MalformedJwtException e) {
            log.error("Token格式非法", e);
            throw new RuntimeException("Token非法");
        } catch (SignatureException e) {
            log.error("Token签名验证失败（可能被篡改）", e);
            throw new RuntimeException("Token签名验证失败");
        } catch (IllegalArgumentException e) {
            log.error("Token为空或格式错误", e);
            throw new RuntimeException("Token不能为空");
        }
    }

    /**
     * 从Token中获取指定的自定义字段
     * @param token Token字符串
     * @param key 字段名（比如userId）
     * @return 字段值
     */
    public static Object getClaimFromToken(String token, String key) {
        Claims claims = parseToken(token);
        return claims.get(key);
    }

    /**
     * 验证Token是否有效（没过期+签名合法）
     * @param token Token字符串
     * @return true=有效，false=无效
     */
    public static boolean validateToken(String token) {
        try {
            parseToken(token); // 解析成功即代表有效
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
