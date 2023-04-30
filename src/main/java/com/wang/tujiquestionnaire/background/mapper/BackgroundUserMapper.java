package com.wang.tujiquestionnaire.background.mapper;
import org.apache.ibatis.annotations.Param;

import com.wang.tujiquestionnaire.background.entity.BackgroundUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.tujiquestionnaire.system.entity.dto.UserAndUserDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-04-25
 */
@Mapper
public interface BackgroundUserMapper extends BaseMapper<BackgroundUser> {

    /**
     * 查询所有包含该用户名和该姓名的数据并分页
     * @param pageNum 分页数
     * @param pageSize 分页大小
     * @param name 姓名
     * @param username 用户名
     * @return 返回查询到的数据列表
     */
    @Select("select * from background_user where name like concat('%',#{name},'%') and username like concat('%',#{username},'%') limit #{pageNum},#{pageSize}")
    List<BackgroundUser> findPage(Integer pageNum, Integer pageSize, String name, String username);

    /** 查询所有包含该用户名和该姓名的数据数量
     * @param name 姓名
     * @param username 用户名
     * @return 返回查询到的数量
     */
    @Select("select count(*) from background_user where name like concat('%',#{name},'%')and username like concat('%',#{username},'%')")
    Integer selectTotal(String name, String username);

    /**
     * 去数据库根据用户名对密码进行修改
     * @param password 管理员用户名
     * @param username 管理员密码
     * @return 返回修改数量
     */
@Update("update background_user set password='${password}' where username='${username}'")
    Integer changePassword(@Param("password") String password, @Param("username") String username);
}
