package com.itheima.Util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Slf4j
public class JwtUtils {

    // 使用 Keys.secretKeyFor 方法来生成一个符合要求的密钥
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);  // 密钥足够强

    private static final long EXPIRATION_TIME = 86400000; // 1 天（单位：毫秒）

    // 生成 JWT 令牌
    public static String generateToken(Map<String, Object> dataMap) {
        return Jwts.builder()
                .setClaims(dataMap)  // 设置 JWT 的内容为 Map 中的所有数据
                .setIssuedAt(new Date())  // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // 设置过期时间
                .signWith(SECRET_KEY)  // 使用生成的密钥进行签名
                .compact();
    }

    // 从 JWT 中获取用户名
    public static String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)  // 解析 JWT
                .getBody();
        return claims.getSubject();  // 获取用户名（subject）
    }

    // 从 JWT 中获取用户角色
    public static String getRole(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return (String) claims.get("role");
    }

    // 验证 JWT 令牌的有效性
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);  // 解析并验证 JWT
            return true;
        } catch (Exception e) {
            return false;  // 如果解析失败，则认为令牌无效
        }
    }

    public static Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.warn("解析 JWT 时出错: {}", e.getMessage());
            throw new RuntimeException("Invalid JWT Token");
        }
    }
}
