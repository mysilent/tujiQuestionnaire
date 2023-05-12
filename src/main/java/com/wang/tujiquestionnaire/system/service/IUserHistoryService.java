package com.wang.tujiquestionnaire.system.service;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.UserHistory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户答卷作答历史记录 服务类
 * </p>
 *
 * @author wang
 * @since 2023-04-14
 */
public interface IUserHistoryService extends IService<UserHistory> {

    /**
     * 根据前端传入id查询用户作答历史记录
     *
     * @param id 前端传来的用户id
     * @return 返回封装的统一接口
     */
    Result history(String id);

    /**
     * 根据用户ID获取该用户发布的各个调查问卷的被作答数。
     *
     * @param userId 用户ID
     * @return 返回各个调查问卷的答题数信息的Map，键为surveyId，值为count
     */
    Map<Object, Object> createAnswerData(String userId);
}
