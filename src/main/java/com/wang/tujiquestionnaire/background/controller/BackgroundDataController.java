package com.wang.tujiquestionnaire.background.controller;

import com.wang.tujiquestionnaire.background.mapper.BackgroundDataMapper;
import com.wang.tujiquestionnaire.background.service.impl.BackgroundDataImpl;
import com.wang.tujiquestionnaire.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: BackgroundDataController
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/5/1-22:34
 */
@Api
@RestController
@RequestMapping("/background/backgroundData")
public class BackgroundDataController {
    @Autowired
    BackgroundDataImpl backgroundData;
    @Autowired
    BackgroundDataMapper backgroundDataMapper;
    @ApiOperation("本周问卷创建数量查询")
    @GetMapping("/surveyCreate")
    public Result surveyCreate(){
        return Result.success(backgroundData.surveyCreateDate());
    }@ApiOperation("本周问卷回答数量查询")
    @GetMapping("/answerCreate")
    public Result answerCreate(){
        return Result.success(backgroundData.answerCreateDate());
    }
}
