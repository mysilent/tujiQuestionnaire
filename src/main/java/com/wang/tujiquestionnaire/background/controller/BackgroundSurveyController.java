package com.wang.tujiquestionnaire.background.controller;

import com.wang.tujiquestionnaire.background.service.impl.BackgroundSurveyServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * ClassName: BackgroundSurveyController
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/4/26-15:22
 */
@Api
@RestController
@RequestMapping("/background/survey")
public class BackgroundSurveyController {
    @Resource
    BackgroundSurveyServiceImpl backgroundSurveyService;
    @ApiModelProperty("对所有问卷以及创建问卷的用户进行查询")
    @GetMapping("/selectAll")
    public Map<String,Object>selectSurvey(@RequestParam("pageNum") Integer pageNum,
                                          @RequestParam("pageSize") Integer pageSize,
                                          @RequestParam("nickname") String nickname,
                                          @RequestParam("survey_name") String surveyName,
                                          @RequestParam("id") String id){
        return backgroundSurveyService.pageSurvey(pageNum, pageSize, nickname, surveyName, id);
    }

}
