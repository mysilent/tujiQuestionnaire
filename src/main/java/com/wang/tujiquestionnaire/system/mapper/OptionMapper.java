package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.system.entity.Option;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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
}
