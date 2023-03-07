package com.wang.tujiquestionnaire.system.controller;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.dto.SurveyCreateDto;
import com.wang.tujiquestionnaire.system.service.impl.SurveyServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 调查问卷主表 前端控制器
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Api
@RestController
@RequestMapping("/system/survey")
public class SurveyController {
    final SurveyServiceImpl surveyService;

    public SurveyController(SurveyServiceImpl surveyService) {
        this.surveyService = surveyService;
    }

    @ApiOperation("问卷添加")
    @PostMapping("/createQuestionnaire")
    public Result createQuestionnaire(SurveyCreateDto surveyCreateDto){
        return surveyService.createQuestionnaire(surveyCreateDto);
}
    @ApiOperation("问卷查询")
    @GetMapping("/selectQuestionnaire")
    public SurveyCreateDto selectQuestionnaire(Long id){
        SurveyCreateDto surveyCreateDto = surveyService.selectQuestionnaire(id);
        return surveyCreateDto;
    }

}
