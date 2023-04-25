package com.wang.tujiquestionnaire.until;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
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





//@Slf4j
//public class JwtUtil {
//    /**
//     *     Token过期时间30分钟
//     */
//    public static final long EXPIRE_TIME = 30 * 60 * 1000;
//
//    private static final String SECRET_KEY = "secret"; // 密钥
//    private static final long EXPIRATION_TIME = 3600_000; // 过期时间，单位毫秒
//    /**
//     * 校验 token
//     * @param token
//     * @param username
//     * @param secret 密码等私密信息
//     * @return
//     */
//    public static boolean verify(String token, String username, String secret) {
//        try {
//            // 对密码进行加密，因为我们保存到token中的密码也是用这个方式进行加密的，secret 字符串是未加密传过来的
//            Algorithm algorithm = Algorithm.HMAC256(secret);
//            JWTVerifier verifier = JWT.require(algorithm).withClaim("userName", username).build();
//            DecodedJWT jwt = verifier.verify(token);
//            return true;
//        }catch (Exception e){
//            log.info("校验token失败，原因："+e);
//            return false;
//        }
//    }
//
//    /**
//     * 生成签名 30分钟过期
//     * @param username
//     * @param userId
//     * @return
//     */
//    public static String generateToken(String username, String userId) {
//        Date now = new Date();
//        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);
//
//        return Jwts.builder()
//                .setSubject(username)
//                .claim("userId", userId)
//                .setIssuedAt(now)
//                .setExpiration(expiration)
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//    }
//
//    /**
//     * 获得用户名
//     * @param request
//     * @return
//     */
//    public static String getUserNameByToken(HttpServletRequest request)  {
//        String token = request.getHeader("token");
//        DecodedJWT jwt = JWT.decode(token);
//        return jwt.getClaim("userName")
//                .asString();
//    }
//
//
//    // 验证token
//
//
//
//
//}


