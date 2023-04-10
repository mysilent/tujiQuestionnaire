package com.wang.tujiquestionnaire.system.controller;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.Question;
import com.wang.tujiquestionnaire.system.entity.Survey;
import com.wang.tujiquestionnaire.system.entity.dto.SurveyCreateDto;
import com.wang.tujiquestionnaire.system.service.impl.SurveyServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Result createQuestionnaire(@RequestBody SurveyCreateDto surveyCreateDto){
        return surveyService.createQuestionnaire(surveyCreateDto);
}
    @ApiOperation("问卷查询")
    @GetMapping("/selectQuestionnaire")
    public SurveyCreateDto selectQuestionnaire(@RequestParam("id")Long id){
        SurveyCreateDto surveyCreateDto = surveyService.selectQuestionnaire(id);
        return surveyCreateDto;
    }
    @ApiOperation("根据用户id查询属于他的问卷")
    @GetMapping("/selectUserSurvey")
    public Result selectUserSurvey(@RequestParam("id") Long id){
        List<Survey> surveys = surveyService.selectUserSurvey(id);
        return Result.success(surveys);
    }
}
