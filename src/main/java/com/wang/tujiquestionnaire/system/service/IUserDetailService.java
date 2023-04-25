package com.wang.tujiquestionnaire.system.service;

import com.wang.tujiquestionnaire.system.entity.UserDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wang
 * @since 2023-04-18
 */
public interface IUserDetailService extends IService<UserDetail> {
//    public List<UserDetail> find();
//
//    public int save(UserDetail user);
//
//    public List<UserDetail> findpage(Integer pageNum,Integer pageSize,String username);
//
//    public Integer selectTotal( String username);
//
    public Map<String,Object> page(Integer pageNum, Integer pageSize, String username,String userId,String email,String phone);
//
//    public boolean delete(Integer id);
//
//    public boolean dleteAll(List<Integer> ids);

}
