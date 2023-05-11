package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.system.entity.UserDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.tujiquestionnaire.system.entity.dto.UserAndUserDetailDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-04-18
 */
@Mapper
public interface UserDetailMapper extends BaseMapper<UserDetail> {


//    @Select("select * from user_detail")
//    List<UserDetail> findAll();
//    @Insert("insert into user_detail(username,password,nickname,email,phone,address) value (#{username},#{password},#{nickname},#{email},#{phone},#{address})")
//    int insert_user(UserDetail user);
//    int update(UserDetail user);
    /**
     * 通过前端传来的条件进行分页查询
     * @param pageNum 分页数量
     * @param pageSize 分页大小
     * @param name 用户昵称
     * @param userId 用户id
     * @param email 邮箱
     * @param phone 电话
     * @return 返回查询到的数据
     */
    @Select("select * from user_detail where name like concat('%',#{name},'%') and user_id like concat('%',#{userId},'%')and email like concat('%',#{email},'%')and phone like concat('%',#{phone},'%') limit #{pageNum},#{pageSize}")
    List<UserDetail> findPage(Integer pageNum,Integer pageSize,String name,String userId,String email,String phone);

    /**
     * 通过前端传过来的条件条件进行多表查询
     * @param pageNum 分页数量
     * @param pageSize 分页大小
     * @param name 用户昵称
     * @param userId 用户id
     * @param email 邮箱
     * @param phone 电话
     * @return 返回两张表的所有数据
     */
@Select("SELECT * FROM user_detail ud JOIN USER u ON u.id=ud.user_id WHERE u.username like concat('%',#{name},'%') and ud.user_id like concat('%',#{userId},'%')and ud.email like concat('%',#{email},'%')and ud.phone like concat('%',#{phone},'%') limit #{pageNum},#{pageSize}")
    List<UserAndUserDetailDto> findPage1(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize, @Param("name") String name, @Param("userId") String userId, @Param("email") String email, @Param("phone") String phone);

    /**
     * 通过前端传来的条件进行查询数量，为上面的分页查询提供总数进行分页
     * @param name 用户昵称
     * @param userId 用户id
     * @param email 邮箱
     * @param phone 电话
     * @return 一共有多收数据
     */
    @Select("select count(*) from user_detail where name like concat('%',#{name},'%')and user_id like concat('%',#{userId},'%')and email like concat('%',#{email},'%')and phone like concat('%',#{phone},'%')")
    Integer selectTotal(String name,String userId,String email,String phone);

//    @Delete("delete from user_detail where user_id=#{user_id} ")
//    int delete(Integer user_id);
//
//    int deleteAll(List<Integer> ids);
//    @Select("select count(*) from user_detail where name=#{name} and password=#{password}")
//    int login(String name,String password);
}
