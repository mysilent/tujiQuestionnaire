package com.wang.tujiquestionnaire.until;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

/**
 * ClassName: HutoolUntil
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/3/3-8:59
 */
@Component
public class HutoolUntil {
    private static Snowflake snowflake = IdUtil.getSnowflake();
    /**
     * 雪花算法获取唯一id
     */
    public String getID(){
        String id =String.valueOf(snowflake.nextId()) ;
        return id;
    }
}
