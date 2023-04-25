package com.wang.tujiquestionnaire.background.controller;

import com.wang.tujiquestionnaire.background.service.impl.BackgroundUserServiceImpl;
import com.wang.tujiquestionnaire.system.service.impl.UserDetailServiceImpl;
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
//    @GetMapping("/find")
//    public List<UserDetail> find(){
//        return userDetailService.find();
//    }
//    @PostMapping("/save")
//    public int insert(@RequestBody UserDetail user){
//        return userDetailService.save(user);
//    }
//    @GetMapping("/delete/{id}")
//    public boolean delete(@PathVariable int id){
//        return userDetailService.delete(id);
//    }
//    @DeleteMapping("deleteAll")
//    public boolean deleteAll(@RequestBody List<Integer> ids){
//        System.out.println(ids);
//        return userDetailService.dleteAll(ids);
//    }

@ApiModelProperty("进行管理员的查询并分页")
@GetMapping("/adminPage")
public Map<String,Object> adminFinPage(@RequestParam("pageNum") Integer pageNum,
                                       @RequestParam("pageSize") Integer pageSize,
                                       @RequestParam("name") String name,
                                       @RequestParam("username") String username){
    return backgroundUserService.findpage(pageNum, pageSize, name,username);
}

@ApiModelProperty("进行用户的查询并分页")
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("username") String name,
                                        @RequestParam("userId") String userId,
                                        @RequestParam("email") String email,
                                        @RequestParam("phone") String phone) {
        return userDetailService.page(pageNum, pageSize, name,userId,email,phone);
    }
//    @PostMapping("/login")
//    public Boolean login(@RequestBody UserDto userDto){
//        System.out.println(userDto);
//        return userDetailService.login(userDto);
//    }
}
