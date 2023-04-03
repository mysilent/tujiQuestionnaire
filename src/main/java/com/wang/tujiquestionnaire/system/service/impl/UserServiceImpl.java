package com.wang.tujiquestionnaire.system.service.impl;

import com.wang.tujiquestionnaire.system.entity.User;
import com.wang.tujiquestionnaire.system.entity.dto.UserDto;
import com.wang.tujiquestionnaire.system.mapper.UserMapper;
import com.wang.tujiquestionnaire.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.tujiquestionnaire.until.HutoolUntil;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final
    HutoolUntil hutoolUntil;

    public UserServiceImpl(UserMapper userMapper, HutoolUntil hutoolUntil) {
        this.userMapper = userMapper;
        this.hutoolUntil = hutoolUntil;
    }


    @Override
    public Integer login(String username, String password) {
        return userMapper.login(username,password);
    }
    @Override
    public Integer enroll(String username, String password) {
            if (userMapper.usernameSelectSame(username)==0) {
                String id= hutoolUntil.getID();
            return (userMapper.enroll(id,username, password)==1) ? 1:0;
        }
        return 2;
    }

    @Override
    public UserDto selectUser(String username) {
        UserDto userDto = userMapper.selectUser(username);
        userDto.setUsername(username);
        return userDto;
    }

}
