package com.wang.tujiquestionnaire.until;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;


/**
 * ClassName: JwtUtil
 * Package: IntelliJ IDEA
 * Description: Jwt工具类
 *
 * @Author: wang
 * Create:2023/3/14-17:42
 */


public class JwtUtil {
    private static final String SECRET = "secret"; // 密钥
    private static final long EXPIRATION_TIME = 86400000; // 过期时间，单位毫秒

    public static String generateToken(String username, String userId) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .setSubject(username)
                .claim("userId", userId)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }
}


