package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.system.entity.UserCreateAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户作答问卷信息 Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-03-08
 */
@Mapper
public interface UserCreateAnswerMapper extends BaseMapper<UserCreateAnswer> {

    /**
     * 将该列表批量插入user_create_answer数据库
     * @param userCreateAnswerList 存放userCreateAnswer对象的列表
     */
    void insertUserCreateAnswerList(List<UserCreateAnswer> userCreateAnswerList);

}
