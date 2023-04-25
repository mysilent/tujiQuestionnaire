package com.wang.tujiquestionnaire.background.mapper;

import com.wang.tujiquestionnaire.background.entity.BackgroundUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wang.tujiquestionnaire.system.entity.dto.UserAndUserDetailDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Select("select * from background_user where name like concat('%',#{name},'%') and username like concat('%',#{username},'%') limit #{pageNum},#{pageSize}")
    List<UserAndUserDetailDto> findPage(Integer pageNum, Integer pageSize, String name, String username);

    @Select("select count(*) from background_user where name like concat('%',#{name},'%')and username like concat('%',#{username},'%')")
    Integer selectTotal(String name, String username);
}
