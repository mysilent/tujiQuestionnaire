package com.wang.tujiquestionnaire.common;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import com.wang.tujiquestionnaire.until.RedisData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * ClassName: MyControllerRedisAspect
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/5/13-18:55
 */
@Aspect
@Component
public class MyControllerRedisAspect {
    private final StringRedisTemplate stringRedisTemplate;


    public MyControllerRedisAspect(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }
    // 创建10个线程的线程池
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);


    /**
     * 解决redis缓存穿透的
     * @param point  调用环绕方法的方法传来值
     */
    @Around(value = "@annotation(com.wang.tujiquestionnaire.common.GetRedisPenetrate)")
    public Object processAuthority(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕通知启动");
        //获取传入方法的参数
        Object[] arg = point.getArgs();
        //获取调用的方法名
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String className = method.getName();
        // 获取调用方法的返回类型
        Class<?> returnType = ((MethodSignature) point.getSignature()).getReturnType();
//        创建redis需要的key
        String key = className + arg[0];

        Long time=RedisConstant.SURVEY_TTL;

        TimeUnit unit = TimeUnit.MINUTES;
        //开始redis查询
        String json = stringRedisTemplate.opsForValue().get(key);
        if (StrUtil.isNotBlank(json)) {
            // 3.存在，直接返回
            return JSONUtil.toBean(json, returnType);
        }
        // 判断命中的是否是空值（""),即防止穿透保存的""
        if (json != null) {
            // 返回一个错误信息
            return null;
        }
        // 4.不存在，调用接口方法
        //调用接口方法
        Object result = point.proceed(arg);
        // 5.不存在，返回错误
        if (result == null) {
            // 将空值写入redis
            stringRedisTemplate.opsForValue().set(key, "", time, unit);
            // 返回错误信息
            return null;
        }
        // 6.存在，写入redis
        this.set(key, JSONUtil.toJsonStr(result), time, unit);
        System.out.println("切面调用完成");
        return result;

    }


    /**
     * 存入redis缓存
     *
     * @param key
     * @param value
     * @param time  过期时间数
     * @param unit  过期时间单位
     */
    public void set(String key, Object value, Long time, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, unit);
    }

    /**
     * 存入redis缓存，设置逻辑过期时间
     *
     * @param key
     * @param value
     * @param time  过期时间数
     * @param unit  过期时间单位
     */
    public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit) {
        // 设置逻辑过期
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        //        // 写入Redis
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

}
