package com.wang.tujiquestionnaire.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.wang.tujiquestionnaire.config.MySensitiveWordReplace;
import com.wang.tujiquestionnaire.system.entity.dto.SurveyCreateDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
/**
 * ClassName: MyControllerAspect
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/4/28-14:30
 */
@Aspect
@Component
public class MyControllerAspect {

//    @Pointcut("execution(public * com.wang.tujiquestionnaire.system.controller.SurveyController.*(..)) && @annotation(org.springframework.web.bind.annotation.PostMapping)")
//    public void survey() {}

    @Around(value = "@annotation(com.wang.tujiquestionnaire.common.SensitiveWord)")
    public Object processAuthority(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕通知启动");
        SensitiveWordBs wordBs = SensitiveWordBs.newInstance()
                .ignoreCase(true) //忽略大小写
                .ignoreWidth(true)//忽略半角圆角
                .ignoreNumStyle(true)//忽略数字的写法
                .ignoreChineseStyle(true)//忽略中文的书写格式
                .ignoreEnglishStyle(true)//忽略英文的书写格式
                .ignoreRepeat(true)//忽略重复词
                .enableNumCheck(false)//是否启用数字检测。默认连续 8 位数字认为是敏感词
                .enableEmailCheck(true)//是有启用邮箱检测
                .enableUrlCheck(true)//是否启用链接检测
                .init();

        Object[] arg = point.getArgs();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonStr = objectMapper.writeValueAsString(arg[0]);
        String replace = wordBs.replace(jsonStr,new MySensitiveWordReplace());
//        arg[0] =objectMapper.readValue(replace, SurveyCreateDto.class);

        Class<?> argClazz = arg[0].getClass();
        arg[0] =objectMapper.readValue(replace,argClazz);
//        调用接口方法
        Object result = point.proceed(arg);
        System.out.println("切面调用完成");
//        返回方法结果
        return result;
    }


}
