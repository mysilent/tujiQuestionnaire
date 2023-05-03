package com.wang.tujiquestionnaire.system.service;

import com.wang.tujiquestionnaire.system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wang.tujiquestionnaire.system.entity.dto.ChangePasswordDto;
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
     * @param email 邮箱
     * @return 失败或成功
     *
     */
     Integer enroll(String username,String password,String email);

    /**
     * 登录之后将用户一部分信息存入UserDto对象中，以便后面用户进行各种功能的使用
     * @param username 用户名
     * @return 返回UserDto对象
     */
    UserDto selectUser(String username);

    /**
     * 判断密码是否正确
     * @param changePasswordDto 前端传来的修改密码的实体类
     * @return 是否旧密码正确
     */
    Boolean isValidOldPassword(ChangePasswordDto changePasswordDto);

    /**
     * 更改数据库中某一个用户的密码
     * @param changePasswordDto 前端传来的修改密码的实体类
     */
    void updateUserPassword(ChangePasswordDto changePasswordDto);

    /**
     * 根据用户名去查询用户状态
     * @param username 用户名
     * @return 返回查询到的state是什么
     */
    Integer selectState(String username);
}
