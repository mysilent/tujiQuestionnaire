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
//    //数据查询
//    @Override
//    public List<UserDetail> find(){
//        return userDetailMapper.findAll();
//    }
//
//    @Override
//    //数据更新
//    public int save(UserDetail user){
//        if (user.getId()==null){
//            return userDetailMapper.insert_user(user);
//        }else {
//            return userDetailMapper.update(user);
//        }
//    }
//    @Override
//    //分页查询
//    public List<UserDetail> findpage(Integer pageNum,Integer pageSize,String username) {
//        return userDetailMapper.findpage(pageNum, pageSize,username);
//
//    }
//    @Override
//    //查询数据总数
//    public Integer selectTotal( String username) {
//        return userDetailMapper.selectTotal(username);
//    }
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

//    @Override
//
//    public boolean delete(Integer id) {
//        int i=userDetailMapper.delete(id);
//        if (i>=1){
//            return true;
//        }else {
//            return false;
//        }
//    }
//    @Override
//
//    public boolean dleteAll(List<Integer> ids) {
//        int i=userDetailMapper.deleteAll(ids);
//        if (i>=1){
//            return true;
//        }else {
//            return false;
//        }
//    }
}
