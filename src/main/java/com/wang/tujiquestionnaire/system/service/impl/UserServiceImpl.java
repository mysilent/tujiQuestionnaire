package com.wang.tujiquestionnaire.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.tujiquestionnaire.system.entity.User;
import com.wang.tujiquestionnaire.system.entity.UserDetail;
import com.wang.tujiquestionnaire.system.entity.UserGold;
import com.wang.tujiquestionnaire.system.entity.dto.ChangePasswordDto;
import com.wang.tujiquestionnaire.system.entity.dto.UserDto;
import com.wang.tujiquestionnaire.system.mapper.UserDetailMapper;
import com.wang.tujiquestionnaire.system.mapper.UserGoldMapper;
import com.wang.tujiquestionnaire.system.mapper.UserMapper;
import com.wang.tujiquestionnaire.system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.tujiquestionnaire.until.HutoolUntil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    final
    UserDetailMapper userDetailMapper;
    final UserGoldMapper userGoldMapper;

    public UserServiceImpl(UserMapper userMapper, HutoolUntil hutoolUntil, UserDetailMapper userDetailMapper,UserGoldMapper userGoldMapper) {
        this.userMapper = userMapper;
        this.hutoolUntil = hutoolUntil;
        this.userDetailMapper = userDetailMapper;
        this.userGoldMapper = userGoldMapper;
    }


    @Override
    public Integer login(String username, String password) {
        return userMapper.login(username,password);
    }
    @Override
    public Integer enroll(String username, String password,String email) {
            if (userMapper.usernameSelectSame(username)==0) {
                String id= hutoolUntil.getID();
                UserDetail userDetail = new UserDetail();
                userDetail.setUserId(id);
                userDetail.setEmail(email);
                //创建一个激励值的实体类对象
                UserGold userGold= new UserGold(id,0);
            return (enrollUserDetail(id, username, password,userDetail,userGold)) ? 1:0;
        }
        return 2;
    }
    @Transactional(rollbackFor = Exception.class)
    public Boolean enrollUserDetail(String id,String username,String password,UserDetail userDetail,UserGold userGold){
        int i = userMapper.enroll(id, username, password);
        int j = userDetailMapper.insert(userDetail);
        int k =  userGoldMapper.insert(userGold);
        return i == 1 && j == 1&&k==1;
    }

    @Override
    public UserDto selectUser(String username) {
        UserDto userDto = userMapper.selectUser(username);
        userDto.setUsername(username);
        return userDto;
    }

    @Override
    public Boolean isValidOldPassword(ChangePasswordDto changePasswordDto) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",changePasswordDto.getUsername())
                    .eq("password",changePasswordDto.getOldPassword());
        Integer i = userMapper.selectCount(queryWrapper).intValue();
        return i==1? true:false;
    }

    @Override
    public void updateUserPassword(ChangePasswordDto changePasswordDto) {
        userMapper.updatePassword(changePasswordDto.getNewPassword(),changePasswordDto.getUsername());
    }

    @Override
    public Integer selectState(String username) {
        return userMapper.selectState(username);
    }


}
