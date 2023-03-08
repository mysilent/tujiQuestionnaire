package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.system.entity.AnswerSon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户答案子表 Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Mapper
public interface AnswerSonMapper extends BaseMapper<AnswerSon> {

    /**
     * 将用户子表列表中的数据批量插入到数据库
     * @param answerSonList 答案子表的列表
     */
    void insertAnswerSonList(List<AnswerSon> answerSonList);
}
