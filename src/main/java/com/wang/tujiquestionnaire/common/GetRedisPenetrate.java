package com.wang.tujiquestionnaire.common;

import java.lang.annotation.*;

/**
 * ClassName: GetRedisPenetrate
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/5/13-19:03
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GetRedisPenetrate {
    String[] value() default "";
}
