package com.wang.tujiquestionnaire.system.controller;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.common.SensitiveWord;
import com.wang.tujiquestionnaire.system.entity.dto.AnswerDto;
import com.wang.tujiquestionnaire.system.service.impl.AnswerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("提交答案表")
    @PostMapping("/submitAnswer")
    @SensitiveWord
    public Result submitAnswer(@RequestBody AnswerDto answerDto){
        return answerService.submitAnswer(answerDto);
    }
    @ApiOperation("用户浏览自己的答卷记录时对问题答案的查询")
    @GetMapping("/historyAnswer")
    public Result historyAnswer(@RequestParam("userId") String userId,@RequestParam("surveyId") String surveyId){
        return answerService.historyAnswer(userId,surveyId);
    }

}
