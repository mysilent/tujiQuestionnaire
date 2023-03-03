package com.wang.tujiquestionnaire.system.service.impl;

import com.wang.tujiquestionnaire.system.entity.User;
import com.wang.tujiquestionnaire.system.mapper.UserMapper;
import com.wang.tujiquestionnaire.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Integer login(String username, String password) {
        return userMapper.login(username,password);
    }
    @Override
    public Integer enroll(String username, String password) {
        if (userMapper.usernameSelectSame(username)==0) {
            return (userMapper.enroll(username, password)==1) ? 1:0;
        }
        return 2;
    }

}
