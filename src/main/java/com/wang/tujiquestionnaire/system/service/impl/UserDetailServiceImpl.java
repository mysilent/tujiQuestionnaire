package com.wang.tujiquestionnaire.system.service.impl;

import com.wang.tujiquestionnaire.system.entity.UserDetail;
import com.wang.tujiquestionnaire.system.entity.dto.UserAndUserDetailDto;
import com.wang.tujiquestionnaire.system.mapper.UserDetailMapper;
import com.wang.tujiquestionnaire.system.service.IUserDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2023-04-18
 */
@Service
public class UserDetailServiceImpl extends ServiceImpl<UserDetailMapper, UserDetail> implements IUserDetailService {
@Autowired
UserDetailMapper userDetailMapper;
    @Override
    //前端分页查询
    public Map<String,Object> page(Integer pageNum, Integer pageSize, String name,String userId,String email,String phone) {
        pageNum = (pageNum - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        Integer total = userDetailMapper.selectTotal(name,userId,email,phone);
        List<UserAndUserDetailDto> data = userDetailMapper.findPage1(pageNum, pageSize,name,userId,email,phone);
        map.put("data", data);
        map.put("total", total);
        return map;
    }

}
