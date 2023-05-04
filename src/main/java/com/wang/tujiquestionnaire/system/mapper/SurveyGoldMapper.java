package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.system.entity.SurveyGold;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-05-02
 */
@Mapper
public interface SurveyGoldMapper extends BaseMapper<SurveyGold> {

    /**
     * 使得发布激励份数减少一份
     * @param id 被回答的问卷id
     * @return 返回修改数量
     */
    @Update("UPDATE survey_gold SET quantity=quantity - 1 where id='${id}'")
public Integer quantityDeleteOne(String id);
}
