package com.wang.tujiquestionnaire.background.service;

import com.wang.tujiquestionnaire.background.entity.SurveyTemplate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.tujiquestionnaire.common.Result;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wang
 * @since 2023-04-26
 */
public interface ISurveyTemplateService extends IService<SurveyTemplate> {


    /**
     * 根据名称与id查询模板数据库中state为‘1’的数据，并分页
     * @param pageSize 分页数
     * @param pageNum   分页大小
     * @param state 状态码
     * @param surveyName 模板名称
     * @param surveyId 模板id
     * @return 带有分页数据的data,以及一共多少条数据total
     */
    Map<String, Object> selectTemplate(@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum, @Param("state") String state, @Param("surveyName") String surveyName, @Param("surveyId") String surveyId);


    /**
     * 查询模板数据库中state为‘0’的待申请的数据，并分页
     * @param pageSize 分页数
     * @param pageNum   分页大小
     * @param state 状态码
     * @return 带有分页数据的data,以及一共多少条数据total
     */
    Map<String, Object> templateApplicationpage(@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum, @Param("state") String state);

    /**
     * 问卷模板申请通过，将state置为 '1'
     * @param id 模板问卷的id
     * @return 返回统一封装类
     */
    Result templateApplicationYes(String id);

    /**
     * 问卷模板申请不通过，将不通过的问卷在问卷模板数据库中删除
     * @param id 模板问卷的id
     * @return 返回同统一封装类
     */
    Result templateApplicationNo(String id);
}
