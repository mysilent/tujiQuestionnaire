package com.wang.tujiquestionnaire.background.service;

import java.util.Map;

/**
 * ClassName: IBackgroundSurveyService
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/4/26-15:28
 */
public interface IBackgroundSurveyService {
    /**
     * 根据前端换来的信息进行问卷以及其创建人的信息并分页
     * @param pageNum 分页数
     * @param pageSize 分页大小
     * @param nickname 用户名
     * @param surveyName 问卷名
     * @param id 问卷id
     * @return 返回查询到的问卷以及其用户信息
     */
    Map<String, Object> pageSurvey(Integer pageNum, Integer pageSize, String nickname, String surveyName, String id);
}
