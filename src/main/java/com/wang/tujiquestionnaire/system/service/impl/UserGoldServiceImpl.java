package com.wang.tujiquestionnaire.system.service.impl;

import com.wang.tujiquestionnaire.system.entity.UserGold;
import com.wang.tujiquestionnaire.system.mapper.UserGoldMapper;
import com.wang.tujiquestionnaire.system.service.IUserGoldService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wang
 * @since 2023-05-02
 */
@Service
public class UserGoldServiceImpl extends ServiceImpl<UserGoldMapper, UserGold> implements IUserGoldService {
@Autowired
UserGoldMapper userGoldMapper;
    @Override
    public Integer selectGold(String id) {
        return userGoldMapper.selectGold(id);
    }
}
