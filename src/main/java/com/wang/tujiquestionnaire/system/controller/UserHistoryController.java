package com.wang.tujiquestionnaire.system.controller;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.service.impl.UserHistoryServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户答卷作答历史记录 前端控制器
 * </p>
 *
 * @author wang
 * @since 2023-04-14
 */
@RestController
@RequestMapping("/system/userHistory")
public class UserHistoryController {
    @Autowired
    private UserHistoryServiceImpl userHistoryService;

    @ApiOperation("查询用户作答历史记录")
    @GetMapping("/history")
    public Result history(@RequestParam("id") String id){
        return userHistoryService.history(id);

    }

}
