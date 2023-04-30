package com.wang.tujiquestionnaire.system.mapper;
import java.util.List;

import com.wang.tujiquestionnaire.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.tujiquestionnaire.system.entity.dto.UserDto;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 判断用户密码是否正确
     * @param username 密码
     * @param password 账户
     * @return 0/1
     */
    @Select("select count(*) from user where username='${username}' and password='${password}'")
    Integer login(@Param("username") String username, @Param("password") String password);

    /**
     * 查找有无相同账户名
     * @param username 账户
     * @return 0/1
     */
    @Select("Select count(*) from user where username='${username}'")
    Integer usernameSelectSame(@Param("username") String username);

    /**
     * 将用户注册的账户密码写入数据库
     * @param username 密码
     * @param password 账户
     * @param id 成员id
     * @return 成功、失败
     */
    @Insert("insert into user(id,username,password) values (${id},'${username}','${password}')")
    Integer enroll(@Param("id") String id,@Param("username") String username , @Param("password") String password);

    /**
     * 根据用户名查询用户的一些用户信息
     * @param username 用户名
     * @return UseDto对象
     */
    @Select("select id,nickname from user where username='${username}'")
    UserDto selectUser(String username);


    /**
     * 对用户的密码进行修改
     * @param newPassword  前端传来的新密码
     * @param username  前端传来的要进行修改密码的用户名
     */
    @Update("update user set password='${newPassword}' where username='${username}'")
    void updatePassword(@Param("newPassword") String newPassword, @Param("username") String username);

    /**
     * 对用户的状态进行修改
     * @param id id标识
     * @param state 封禁状态
     * @return 返回修改没修改
     */
@Update("update user set state=${state} where id=${id}")
    Boolean updateState(String id, int state);

    /**
     * 根据用户名查询用户状态
     * @param username 用户名
     * @return 查询到的state
     */
    @Select("select state from user where username='${username}'")
    int selectState(@Param("username") String username);
}
