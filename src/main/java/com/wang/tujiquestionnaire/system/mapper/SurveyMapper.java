package com.wang.tujiquestionnaire.system.mapper;
import java.time.LocalDateTime;
import org.apache.ibatis.annotations.Param;

import com.wang.tujiquestionnaire.system.entity.Survey;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 调查问卷主表 Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Mapper
public interface SurveyMapper extends BaseMapper<Survey> {
    Integer updateStatusById(@Param("status") String status, @Param("id") String id);

}
