package com.wang.tujiquestionnaire.system.service;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.SurveyGold;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.tujiquestionnaire.system.entity.dto.SurveyGoldDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wang
 * @since 2023-05-02
 */
public interface ISurveyGoldService extends IService<SurveyGold> {


    /** 对问卷进行发布
     * @param surveyGoldDto 实体类
     * @return 返回统一封装类
     */
    Result surveyPublish(SurveyGoldDto surveyGoldDto);

    /**
     * 回收问卷
     * @param id 问卷id
     * @return 返回统一封装类
     */
    Result surveyStop(String id);
}
