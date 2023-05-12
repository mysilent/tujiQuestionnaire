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

    /**
     * 根据传来的问卷id进行数据的查询并处理返回前端
     * @param id 问卷id
     * @return 返回处理好的数据封装进统一封装类
     */
    Result selectDataBySurveyId(String id);

    /**
     *根据前端传来的值进行查询返回符合条件的
     *@param surveyId 问卷ID
     *@param questionId 问题ID
     *@param content 包含答案的内容
     *@param pageNum 页码，从1开始，若值为0或null，则查询所有符合条件的答案
     *@param pageSize 每页数据量，只有在pageNum不为0或null时才有用
     *@return 返回符合条件的答案列表及总数的Map，其中"data"字段对应答案列表，"total"字段对应答案总数
     */
    Result inputAnswer(String surveyId, String questionId, String content, Integer pageNum, Integer pageSize);
}
