package com.wang.tujiquestionnaire.system.service;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.dto.SurveyCreateDto;
import com.wang.tujiquestionnaire.system.entity.Survey;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 调查问卷主表 服务类
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
public interface ISurveyService extends IService<Survey> {

    /**
     * 将前端传入的问卷信息分别存入问卷表、问题表、选项表
     * @param surveyCreateDto  surveyCreateDto实体类
     * @return 返回定义的统一返回类
     */
    Result createQuestionnaire(SurveyCreateDto surveyCreateDto);

    Result createQuestionnaire1(SurveyCreateDto surveyCreateDto);

    /**
     * 根据前端传过来的问卷id返回问卷所有信息
     * @param id 传过来的问卷id
     * @return 返回存储问卷所有信息的一个实体类
     */
    SurveyCreateDto selectQuestionnaire(Long id);

    /**
     * 根据前端传过来的用户id返回该用户所创建的问卷
     * @param creatorId 前端传过来的用户id
     * @return  返回一个存储问卷id、名字、等信息的对象
     */
    List<Survey> selectUserSurvey(Long creatorId);
}
