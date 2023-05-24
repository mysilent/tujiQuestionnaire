package com.wang.tujiquestionnaire.system.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.wang.tujiquestionnaire.common.Constant;
import com.wang.tujiquestionnaire.common.RedisConstant;
import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.dto.ChangePasswordDto;
import com.wang.tujiquestionnaire.system.entity.dto.UserDto;
import com.wang.tujiquestionnaire.system.service.impl.UserServiceImpl;

import com.wang.tujiquestionnaire.until.JwtUtil;
import io.swagger.annotations.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @ApiOperation("用户登录")
    @PostMapping ("/login")
    public Result login(@RequestParam("username") String username, @RequestParam("password")String password) {
        Integer state = userService.selectState(username);
        if(state == null){
            // 查询结果为空，可以抛出自定义异常或者给state赋一个默认值
            // 给state赋默认值的例子：
            state = -1;
        }
        if (userService.login(username, password) == 1 && state==Constant.STATE_ENABLE) {
            UserDto userDto = userService.selectUser(username);
            String token = JwtUtil.generateToken(username,String.valueOf(userDto.getId()));
            userDto.setToken(token);
            Map<String, Object> userMap = BeanUtil.beanToMap(userDto,new HashMap<>(),
                    CopyOptions.create().setIgnoreNullValue(true)
                            .setFieldValueEditor((fieldName,fieldValue)->fieldValue.toString()));
            stringRedisTemplate.opsForHash().putAll(RedisConstant.LOGIN_USER_KEY+token, userMap);
            //7.4 设置过期时间30分钟
            stringRedisTemplate.expire(RedisConstant.LOGIN_USER_KEY+token,RedisConstant.LOGIN_USER_TTL, TimeUnit.MINUTES);
            if (token != null){
                return Result.success(userDto);
            }
        }else if (state== Constant.STATE_BAN){
            return Result.error(10000, "账户已被封禁");
        }
        return Result.error(10000, "账户或密码错误");
    }

    @ApiOperation("用户注册")
    @PostMapping("/enroll")
    public Result enroll(@RequestParam("username") String username, @RequestParam("pass")String password,@RequestParam("email") String email) {
        return switch (userService.enroll(username, password, email)) {
            case 1 -> Result.success();
            case 2 -> Result.error("该用户已存在");
            default -> Result.error();
        };
    }



    @ApiOperation("修改密码")
    @PostMapping("/changePassword")
    public Result changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        // 验证旧密码是否正确
        if (!isValidOldPassword(changePasswordDto)) {
            return Result.error("密码不正确");
        }
        // 更新数据库中的密码信息

        updateUserPassword(changePasswordDto);
        // 返回修改密码成功的信息
        return Result.success("密码修改成功！");
    }

    // 验证旧密码是否正确

    private boolean isValidOldPassword( ChangePasswordDto changePasswordDto) {
        return userService.isValidOldPassword(changePasswordDto);
    }

    // 更新用户密码信息

    private void updateUserPassword(ChangePasswordDto changePasswordDto) {
        userService.updateUserPassword(changePasswordDto);
    }

}
