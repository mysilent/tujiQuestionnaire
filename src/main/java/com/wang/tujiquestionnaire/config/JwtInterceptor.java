package com.wang.tujiquestionnaire.config;


import com.wang.tujiquestionnaire.common.RedisConstant;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * ClassName: MyInterceptor
 * Package: IntelliJ IDEA
 * Description:定义 JwtInterceptor 类实现 HandlerInterceptor 接口
 * @Author: wang
 * Create:2023/4/21-15:29
 */
public class JwtInterceptor implements HandlerInterceptor {
    private final StringRedisTemplate stringRedisTemplate;


    public JwtInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    // 定义静态变量 HEADER_AUTHORIZATION 和 BEARER_PREFIX
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    // 实现 preHandle 方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的 Authorization 字段
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        // 如果 Authorization 字段存在且以 Bearer 前缀开头
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            // 从 Authorization 字段中获取 JWT Token
            String token = authorizationHeader.substring(BEARER_PREFIX.length());
            Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(RedisConstant.LOGIN_USER_KEY +token);
            if (userMap.isEmpty()){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token");
                return false;
            }
            return true;
        } else {
            // 如果 Authorization 字段不存在或者不是以 Bearer 前缀开头，则返回 401 错误
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing token");
            return false;
        }
    }
}
