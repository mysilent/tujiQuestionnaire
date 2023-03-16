package com.wang.tujiquestionnaire.system.controller;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.dto.UserDto;
import com.wang.tujiquestionnaire.system.service.impl.UserServiceImpl;
import com.wang.tujiquestionnaire.until.JwtUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Api
@RestController
@RequestMapping("/system/user")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ApiOperation("用户登录")
    @PostMapping ("/login")
    public Result login(@RequestParam("username") String username, @RequestParam("password")String password) {
//        System.out.println(userDto.getUsername()+userDto.getPassword());
        Integer i = userService.login(username, password);
        if (i == 1) {
            UserDto userDto = userService.selectUser(username);
            String token = JwtUtil.getToken(username,String.valueOf(userDto.getId()));
            userDto.setToken(token);
            if (token != null){
                return Result.success(userDto);
            }
        }
        return Result.error(10000, "账户或密码错误");
    }

    @ApiOperation("用户注册")
    @PostMapping("/enroll")
    public Result enroll(@RequestParam("username") String username, @RequestParam("pass")String password) {
        switch (userService.enroll(username, password)) {
            case 1:
                return Result.success();
            case 2:
                return Result.error("该用户已存在");
            default:
                return Result.error();
        }
    }

//    @ApiOperation("用户填写问卷历史记录")
}
