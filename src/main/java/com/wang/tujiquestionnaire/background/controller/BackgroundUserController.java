package com.wang.tujiquestionnaire.background.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.tujiquestionnaire.background.entity.BackgroundUser;
import com.wang.tujiquestionnaire.background.service.impl.BackgroundUserServiceImpl;
import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.service.impl.UserDetailServiceImpl;
import com.wang.tujiquestionnaire.system.service.impl.UserServiceImpl;
import com.wang.tujiquestionnaire.until.HutoolUntil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * ClassName: BackgroundUserController
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/4/25-14:41
 */
@Api
@RestController
@RequestMapping("/background/user")
public class BackgroundUserController {

    @Resource
    private UserDetailServiceImpl userDetailService;
    @Resource
    private BackgroundUserServiceImpl backgroundUserService;
    @Resource
    private HutoolUntil hutoolUntil;

    @ApiModelProperty("进行用户的查询并分页")
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("username") String name,
                                        @RequestParam("userId") String userId,
                                        @RequestParam("email") String email,
                                        @RequestParam("phone") String phone) {
        return userDetailService.page(pageNum, pageSize, name, userId, email, phone);
    }

    @ApiModelProperty("对用户进行封禁或解封")
    @PostMapping("/updateState")
    public Result updateState(@RequestParam("id") String id,@RequestParam("state")int state){
        return backgroundUserService.updateState(id,state);
    }


    @ApiModelProperty("进行管理员的查询并分页")
    @GetMapping("/adminPage")
    public Map<String, Object> adminFinPage(@RequestParam("pageNum") Integer pageNum,
                                            @RequestParam("pageSize") Integer pageSize,
                                            @RequestParam("name") String name,
                                            @RequestParam("username") String username) {
        return backgroundUserService.findpage(pageNum, pageSize, name, username);
    }

    @ApiModelProperty("新增管理员")
    @PostMapping("/save")
    public Result adminSave(@RequestBody BackgroundUser backgroundUser) {
        backgroundUser.setId(hutoolUntil.getID());
        boolean save = backgroundUserService.save(backgroundUser);
        return save ? Result.success() : Result.error();
    }

    @ApiModelProperty("修改管理员信息")
    @PostMapping("/update")
    public Result adminUpdate(@RequestBody BackgroundUser backgroundUser) {
        boolean update = backgroundUserService.updateById(backgroundUser);
        return update ? Result.success() : Result.error();
    }

    @ApiModelProperty("管理员信息删除")
    @DeleteMapping("/delete")
    public Result adminDelete(@RequestParam("id") String id) {
        boolean remove = backgroundUserService.removeById(id);
        return remove ? Result.success() : Result.error();
    }

    @ApiModelProperty("管理员登录")
    @GetMapping("/backgroundLogin")
    public Result backgroundLogin(@RequestParam("username") String username, @RequestParam("password")String password){
        return backgroundUserService.backgroundLogin(username,password);
    }

    @ApiModelProperty("管理员修改密码")
    @PostMapping("backgroundChangePassword")
    public Result backgroundChangePassword(@RequestParam("username") String username,@RequestParam("newPassword")String password){
        return backgroundUserService.changePassword(username,password);
    }
}
