package com.wang.tujiquestionnaire.until;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
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

@Slf4j
public class JwtUtil {
    /**
     *     Token过期时间30分钟
     */
    public static final long EXPIRE_TIME = 30 * 60 * 1000;
    /**
     * 校验 token
     * @param token
     * @param username
     * @param secret 密码等私密信息
     * @return
     */
    public static boolean verify(String token, String username, String secret) {
        try {
            // 对密码进行加密，因为我们保存到token中的密码也是用这个方式进行加密的，secret 字符串是未加密传过来的
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("userName", username).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            log.info("校验token失败，原因："+e);
            return false;
        }
    }

    /**
     * 生成签名 30分钟过期
     * @param username
     * @param secret
     * @return
     */
    public static String getToken(String username, String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                // 设置负载内容
                .withClaim("userName",username)
                // 设置过期时间
                .withExpiresAt(date)
                // 设置尾部签名信息
                .sign(algorithm);
    }

    /**
     * 获得用户名
     * @param request
     * @return
     */
    public static String getUserNameByToken(HttpServletRequest request)  {
        String token = request.getHeader("token");
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("userName")
                .asString();
    }

}


