package com.wang.tujiquestionnaire.system.controller;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.dto.AnswerDto;
import com.wang.tujiquestionnaire.system.service.impl.AnswerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 用户答案表 前端控制器
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Api
@ApiOperation("用户答案主表")
@RestController
@RequestMapping("/system/answer")
public class AnswerController {
    private final AnswerServiceImpl answerService;

    public AnswerController(AnswerServiceImpl answerService) {
        this.answerService = answerService;
    }
    @ApiOperation("将填写问卷提交到答案表")
    @PostMapping("submitAnswer")
    public Result submitAnswer(AnswerDto answerDto){
        return answerService.submitAnswer(answerDto);
    }


}
