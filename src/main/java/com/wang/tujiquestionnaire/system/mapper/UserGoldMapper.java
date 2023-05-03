package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.system.entity.UserGold;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}
