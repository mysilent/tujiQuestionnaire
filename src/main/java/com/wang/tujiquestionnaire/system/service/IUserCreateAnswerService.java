package com.wang.tujiquestionnaire.system.service;

import com.wang.tujiquestionnaire.system.entity.UserCreateAnswer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户作答问卷信息 服务类
 * </p>
 *
 * @author wang
 * @since 2023-03-08
 */
public interface IUserCreateAnswerService extends IService<UserCreateAnswer> {

    /**
     * 查找该用户的填写记录
     * @param userId 用户id
     * @return UserCreateAnswer  list列表对象
     */
    List<UserCreateAnswer> createAnswerHistory(Long userId);
}
