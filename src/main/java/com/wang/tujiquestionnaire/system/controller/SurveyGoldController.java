package com.wang.tujiquestionnaire.system.controller;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.dto.SurveyGoldDto;
import com.wang.tujiquestionnaire.system.service.impl.SurveyGoldServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wang
 * @since 2023-05-02
 */
@Api
@RestController
@RequestMapping("/system/surveyGold")
public class SurveyGoldController {
    @Autowired
    SurveyGoldServiceImpl surveyGoldService;
    @ApiOperation("问卷进入发布状态")
    @PostMapping("/surveyPublish")
    public Result surveyPublish(@RequestBody SurveyGoldDto surveyGoldDto){
        return surveyGoldService.surveyPublish(surveyGoldDto);
    }

}
