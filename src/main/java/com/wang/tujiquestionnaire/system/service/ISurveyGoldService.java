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


    Result surveyPublish(SurveyGoldDto surveyGoldDto);
}
