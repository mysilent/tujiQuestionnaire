package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.system.entity.UserGold;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-05-02
 */
@Mapper
public interface UserGoldMapper extends BaseMapper<UserGold> {
@Select("select gold from user_gold where id='${id}'")
     Integer selectGold(String id);

@Update("UPDATE user_gold SET gold=gold + ${gold} where id='${id}'")
Integer userGoldAdd(@Param("gold") Integer gold, @Param("id") String id);
}
