package com.wang.tujiquestionnaire.until;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.wang.tujiquestionnaire.system.entity.dto.UserDto;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;
/**
 * ClassName: Constant
 * Package: IntelliJ IDEA
 * Description: 本拦截器作用是：已经登录的用户，访问非登录页面时，延长redis过期时间
 *
 * @Author: wang
 * Create:2023/5/5-12:47
 */

public class RefreshTokenInterceptor implements HandlerInterceptor {
    private StringRedisTemplate stringRedisTemplate;
    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("authorization");
        //token是存到redis中的键，没有键，说明之前没有登录，直接放行
        if (StrUtil.isBlank(token)) {
            return true;
        }
        //2:基于token获取redis中的用户,key是一个常量+token组成的。
        Map<Object, Object> userMap = stringRedisTemplate.opsForHash().entries(RedisConstants.LOGIN_USER_KEY + token);
        //3:判断用户是否存在
        if (userMap.isEmpty()) {
            //4：不存在，直接放行
            return true;
        }
        //只有登录用户，才将redis的有效期改为30分钟
        //5：将查询到的Hash转换成UserDTO对象
        UserDto userDTO = BeanUtil.fillBeanWithMap(userMap, new UserDto(), false);

        //6:存在，保存用户信息到ThreadLocal,UserHolder编写了保存UserHolder的方法
        UserHolder.saveUser(userDTO);
        //7:刷新redis键的有效期，也就是每次通过拦截器，都说明用户与系统有交互，那么自动清除时间重新刷新计算,30分钟后过期
        stringRedisTemplate.expire(RedisConstants.LOGIN_USER_KEY + token, 30, TimeUnit.MINUTES);
        //6：放行
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //移除用户
        UserHolder.removeUser();
    }
}
