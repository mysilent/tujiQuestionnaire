package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.system.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 调查问卷问题主表 Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     * 判断问题是什么类型
     * @param id 问卷问题表id
     * @return  问题类型
     */
    @Select("select question_type from question_info where id=${id}")
    Integer selectQuestionType(Long id);

    /**
     * 将传入的数据批量插入数据库的question表中
     * @param questionLists question对象的list列表
     */
    void insertQuestionList(List<Question> questionLists);
}
