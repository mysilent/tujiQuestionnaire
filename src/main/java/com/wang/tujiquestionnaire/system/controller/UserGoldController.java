package com.wang.tujiquestionnaire.system.controller;

import com.wang.tujiquestionnaire.system.service.impl.UserGoldServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wang
 * @since 2023-05-02
 */
@RestController
@RequestMapping("/system/userGold")
public class UserGoldController {
    @Autowired
    UserGoldServiceImpl userGoldService;

    @ApiOperation("查询用户激励值")
    @GetMapping("/userGold")
    public Integer userGold(@RequestParam("id") String id){
        return userGoldService.selectGold(id);
    }

}
