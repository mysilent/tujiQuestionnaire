package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.system.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    @Select("select count(*) from user where username=${username} and password=${password}")
    Integer login(@Param("username") String username, @Param("password") String password);

    /**
     * 查找有无相同账户名
     * @param username 账户
     * @return 0/1
     */
    @Select("Select count(*) from user where username=${username}")
    Integer usernameSelectSame(@Param("username") String username);

    /**
     * 将用户注册的账户密码写入数据库
     * @param username 密码
     * @param password 账户
     * @param id 成员id
     * @return 成功、失败
     */
    @Insert("insert into user(id,username,password) values (${id},${username},${password})")
    Integer enroll(@Param("id") Long id,@Param("username") String username , @Param("password") String password);
}
