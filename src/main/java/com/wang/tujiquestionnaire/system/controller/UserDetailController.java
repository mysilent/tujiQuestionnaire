package com.wang.tujiquestionnaire.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.common.SensitiveWord;
import com.wang.tujiquestionnaire.system.entity.UserDetail;
import com.wang.tujiquestionnaire.system.entity.dto.UserDetailDto;
import com.wang.tujiquestionnaire.system.mapper.UserMapper;
import com.wang.tujiquestionnaire.system.service.impl.UserDetailServiceImpl;
import com.wang.tujiquestionnaire.system.service.impl.UserGoldServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
    @Autowired
    UserGoldServiceImpl userGoldService;
    @Autowired
    UserMapper userMapper;
    @ApiOperation("用户详情")
    @GetMapping("/user")
    public UserDetailDto userSelect(@RequestParam("id") String id){
        UserDetailDto userDetailDto = new UserDetailDto();
        Integer gold =userGoldService.selectGold(id);
        String nickname = userMapper.getNickname(id);
        UserDetail serviceById = userDetailService.getById(id);
        BeanUtils.copyProperties(serviceById,userDetailDto);
        userDetailDto.setGold(gold);
        userDetailDto.setName(nickname);
        return userDetailDto;
    }
    @ApiOperation("用户信息修改")
    @PostMapping("/userDetail")
    @SensitiveWord
    public Result userUpdate(@RequestBody UserDetail userDetail){
        if (userDetail.getEmail().isBlank()){
            userDetail.setEmail(null);
        }
        if (userDetail.getPhone().isBlank()){
            userDetail.setPhone(null);
        }
        if (!userDetail.getName().isBlank()){
            userMapper.updateNickname(userDetail.getUserId(),userDetail.getName());
        }
        QueryWrapper<UserDetail> userDetailQueryWrapper = new QueryWrapper<>();
        userDetailQueryWrapper.eq("user_id",userDetail.getUserId());
        return (userDetailService.update(userDetail,userDetailQueryWrapper))?Result.success():Result.error();
    }
}
