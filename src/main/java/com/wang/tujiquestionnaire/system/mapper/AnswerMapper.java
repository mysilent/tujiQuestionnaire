package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.system.entity.Answer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户答案表 Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Mapper
public interface AnswerMapper extends BaseMapper<Answer> {

    void insertAnswerList(List<Answer> answerList);
}
