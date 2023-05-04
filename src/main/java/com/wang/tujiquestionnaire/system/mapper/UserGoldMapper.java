package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.system.entity.UserGold;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-05-02
 */
@Mapper
public interface UserGoldMapper extends BaseMapper<UserGold> {
    /**
     * 根据用户id查询用户的激励值
     *
     * @param id 用户id
     * @return 返回int
     */
    @Select("select gold from user_gold where id='${id}'")
    Integer selectGold(String id);

    /**
     * 用户回答完问卷之后如果有激励值奖赏则调用该方法给用户增加激励值
     * @param gold 激励值
     * @param id   用户id
     * @return 返回修改数
     */
    @Update("UPDATE user_gold SET gold=gold + ${gold} where id='${id}'")
    Integer userGoldAdd(@Param("gold") Integer gold, @Param("id") String id);

    /**
     * 本方法为用户在发布带有激励值的问卷是,将扣除相应的激励值
     * @param gold 激励值
     * @param id 用户id
     * @return 返回修改数
     */
    @Update("UPDATE user_gold SET gold=gold - ${gold} where id='${id}'")
    Integer userGoldSub(@Param("gold") Integer gold, @Param("id") String id);
}
