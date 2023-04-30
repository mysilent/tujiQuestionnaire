package com.wang.tujiquestionnaire.background.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.tujiquestionnaire.background.entity.BackgroundUser;
import com.wang.tujiquestionnaire.background.mapper.BackgroundUserMapper;
import com.wang.tujiquestionnaire.background.service.IBackgroundUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.dto.UserAndUserDetailDto;
import com.wang.tujiquestionnaire.system.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wang
 * @since 2023-04-25
 */
@Service
public class BackgroundUserServiceImpl extends ServiceImpl<BackgroundUserMapper, BackgroundUser> implements IBackgroundUserService {
@Autowired
BackgroundUserMapper backgroundUserMapper;
@Autowired
    UserMapper userMapper;

    @Override
    public Result backgroundLogin(String username, String password) {
        QueryWrapper<BackgroundUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username).eq("password",password);
        Long count = backgroundUserMapper.selectCount(queryWrapper);
        BackgroundUser backgroundUser = backgroundUserMapper.selectOne(queryWrapper);
        return count==1?Result.success(backgroundUser):Result.error();
    }

    @Override
    public Map<String, Object> findpage(Integer pageNum,Integer pageSize,String name,String username) {
        pageNum = (pageNum - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        Integer total = backgroundUserMapper.selectTotal(name,username);
        List<BackgroundUser> data = backgroundUserMapper.findPage(pageNum, pageSize,name,username);
        map.put("data", data);
        map.put("total", total);
        return map;
    }

    @Override
    public Result updateState(String id, int state) {
        return userMapper.updateState(id,state)?Result.success():Result.error();

    }

    @Override
    public Result changePassword(String username, String password) {
        return backgroundUserMapper.changePassword(username,password)==1?Result.success():Result.error();
    }
}
