package com.wang.tujiquestionnaire.background.service;

import com.wang.tujiquestionnaire.background.entity.BackgroundUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.tujiquestionnaire.common.Result;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wang
 * @since 2023-04-25
 */
public interface IBackgroundUserService extends IService<BackgroundUser> {

    /**
     * 进行登陆验证
     * @param username 管理员用户名
     * @param password 管理员密码
     * @return 返回是否登陆成功
     */
    Result backgroundLogin(String username, String password);

    /**
     * 根据前端出传来的信息进行分页查询
     * @param pageNum 页数
     * @param pageSize 分页大小
     * @param name 名字
     * @param username 账户
     * @return 返回放置查询数据以及分页数据
     */
    Map<String, Object> findpage(Integer pageNum,Integer pageSize,String name,String username);

    /**
     * 根据前端传来的值对用户状态进行修改
     * @param id id标识
     * @param state 用户状态
     * @return 返回统一封装类
     */
    Result updateState(String id, int state);


    Result changePassword(String username, String password);
}
