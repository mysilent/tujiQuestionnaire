package com.wang.tujiquestionnaire.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.tujiquestionnaire.system.entity.UserCreateAnswer;
import com.wang.tujiquestionnaire.system.mapper.QuestionMapper;
import com.wang.tujiquestionnaire.system.mapper.UserCreateAnswerMapper;
import com.wang.tujiquestionnaire.system.service.IUserCreateAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户作答问卷信息 服务实现类
 * </p>
 *
 * @author wang
 * @since 2023-03-08
 */
@Service
public class UserCreateAnswerServiceImpl extends ServiceImpl<UserCreateAnswerMapper, UserCreateAnswer> implements IUserCreateAnswerService {
@Autowired
UserCreateAnswerMapper userCreateAnswerMapper;
    @Override
    public List<UserCreateAnswer> createAnswerHistory(Long userId) {
        QueryWrapper<UserCreateAnswer> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        return userCreateAnswerMapper.selectList(queryWrapper);
    }

}
