package com.wang.tujiquestionnaire.until;

import lombok.Data;

import java.time.LocalDateTime;
/**
 * ClassName: Constant
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/5/5-11:37
 */
@Data
public class RedisData {
    private LocalDateTime expireTime;
    private Object data;
}
