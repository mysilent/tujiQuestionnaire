package com.wang.tujiquestionnaire.system.service;

import com.wang.tujiquestionnaire.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.tujiquestionnaire.system.entity.dto.UserDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
public interface IUserService extends IService<User> {
    /**
     * 登录
     * @param username 账户
     * @param password 密码
     * @return 失败或成功
     */
     Integer login(String username, String password);

    /**
     * 注册
     * @param username 账户
     * @param password 密码
     * @return 失败或成功
     *
     */
     Integer enroll(String username,String password);

    /**
     * 登录之后将用户一部分信息存入UserDto对象中，以便后面用户进行各种功能的使用
     * @param username 用户名
     * @return 返回UserDto对象
     */
    UserDto selectUser(String username);
}
