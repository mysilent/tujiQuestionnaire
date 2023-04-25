package com.wang.tujiquestionnaire.config;

import com.wang.tujiquestionnaire.until.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.web.servlet.HandlerInterceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ClassName: MyInterceptor
 * Package: IntelliJ IDEA
 * Description:定义 JwtInterceptor 类实现 HandlerInterceptor 接口
 * @Author: wang
 * Create:2023/4/21-15:29
 */
public class JwtInterceptor implements HandlerInterceptor {
    // 定义静态变量 HEADER_AUTHORIZATION 和 BEARER_PREFIX

    private static final String HEADER_AUTHORIZATION = "Authorization";
//    private static final String HEADER_AUTHORIZATION = "token";
    private static final String BEARER_PREFIX = "Bearer ";

    // 实现 preHandle 方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的 Authorization 字段
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        System.out.println(authorizationHeader);
        // 如果 Authorization 字段存在且以 Bearer 前缀开头
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            // 从 Authorization 字段中获取 JWT Token
            String token = authorizationHeader.substring(BEARER_PREFIX.length());
            try {
                // 解析 JWT Token，获取其中的 userId 并将其设置为请求属性
                Claims claims = JwtUtil.parseToken(token);
                request.setAttribute("userId", claims.get("userId"));
                return true;
            } catch (JwtException e) {
                // 如果解析过程中出错，则返回 401 错误
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return false;
            }
        } else {
            // 如果 Authorization 字段不存在或者不是以 Bearer 前缀开头，则返回 401 错误
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token");
            return false;
        }
    }
}
