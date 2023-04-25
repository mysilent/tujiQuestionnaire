package com.wang.tujiquestionnaire.system.service;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.Answer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.tujiquestionnaire.system.entity.dto.AnswerDto;

/**
 * <p>
 * 用户答案表 服务类
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
public interface IAnswerService extends IService<Answer> {

    /**
     * 提交填写好的问卷至问题表
     * @param answerDto AnswerDto 对象
     * @return 返回定义的统一返回类
     */
//    Result submitAnswer(AnswerDto answerDto);
    /**
     * 提交填写好的问卷至问题表
     * @param answerDto AnswerDto 对象
     * @return 返回定义的统一返回类
     */
    Result submitAnswer(AnswerDto answerDto);

    /**
     * 根据前端传来的两个id去数据库查询该问卷用户的答案
     * @param userId 前端传来的用户id
     * @param surveyId 前端传来的问卷id
     * @return 返回封装的统一返回类
     */
    Result historyAnswer(String userId, String surveyId);
}
