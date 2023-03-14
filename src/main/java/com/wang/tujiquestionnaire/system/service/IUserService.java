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

    UserDto selectUser(String username);
}
