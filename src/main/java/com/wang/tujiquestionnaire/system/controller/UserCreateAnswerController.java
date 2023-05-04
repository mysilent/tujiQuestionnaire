package com.wang.tujiquestionnaire.system.controller;

import com.wang.tujiquestionnaire.system.entity.UserCreateAnswer;
import com.wang.tujiquestionnaire.system.service.impl.UserCreateAnswerServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户作答问卷信息 前端控制器
 * </p>
 *
 * @author wang
 * @since 2023-03-08
 */
@Api
@RestController
@RequestMapping("/system/userCreateAnswer")
public class UserCreateAnswerController {
    private final
    UserCreateAnswerServiceImpl userCreateAnswerService;

    public UserCreateAnswerController(UserCreateAnswerServiceImpl userCreateAnswerService) {
        this.userCreateAnswerService = userCreateAnswerService;
    }

    @ApiOperation("用户填写历史记录")
    @GetMapping("createAnswerHistory")
    public List<UserCreateAnswer> createAnswerHistory(Long userId){
        List<UserCreateAnswer> userCreateAnswer = userCreateAnswerService.createAnswerHistory(userId);
        return userCreateAnswer;
    }
}
