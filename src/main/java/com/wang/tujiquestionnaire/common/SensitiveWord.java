package com.wang.tujiquestionnaire.common;

import java.lang.annotation.*;

/**
 * ClassName: SensitiveWord
 * Package: IntelliJ IDEA
 * Description: 自定义注解，注解在方法上会启用切面方法MyControllerAspect
 *
 * @Author: wang
 * Create:2023/4/28-16:27
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SensitiveWord {
    String[] value() default "";
}