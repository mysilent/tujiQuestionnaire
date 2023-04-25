package com.wang.tujiquestionnaire.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.UserDetail;
import com.wang.tujiquestionnaire.system.service.impl.UserDetailServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wang
 * @since 2023-04-18
 */
@Api
@RestController
@RequestMapping("/system/userDetail")
public class UserDetailController {
    @Autowired
    UserDetailServiceImpl userDetailService;
    @ApiOperation("用户详情")
    @GetMapping("/user")
    public UserDetail userSelect(@RequestParam("id") String id){
        return userDetailService.getById(id);
    }
    @ApiOperation("用户信息修改")
    @PostMapping("/userDetail")
    public Result userUpdate(@RequestBody UserDetail userDetail){
        if (userDetail.getEmail().isEmpty()){
            userDetail.setEmail(null);
        }
        if (userDetail.getPhone().isEmpty()){
            userDetail.setPhone(null);
        }
        if (userDetail.getName().isEmpty()){
            userDetail.setName(null);
        }
        QueryWrapper<UserDetail> userDetailQueryWrapper = new QueryWrapper<>();
        userDetailQueryWrapper.eq("user_id",userDetail.getUserId());
        return (userDetailService.update(userDetail,userDetailQueryWrapper))?Result.success():Result.error();
    }


}
