package com.wang.tujiquestionnaire.system.mapper;
import org.apache.ibatis.annotations.Param;

import com.wang.tujiquestionnaire.system.entity.Option;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 调查问卷问题选项主表 Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Mapper
public interface OptionMapper extends BaseMapper<Option> {

    /**
     * 将传入的对象批量存入数据库中的option表
     * @param optionLists option对象的list表
     */
    void insertOptionList(List<Option> optionLists);

    /**
     * 根据传入的问卷id查询对应数据返回map数组
     * @param surveyId 问卷id
     * @return 返回查询到的map数据
     */
    List<Option> selectAllBySurveyId(@Param("surveyId") String surveyId);
}
