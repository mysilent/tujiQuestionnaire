package com.wang.tujiquestionnaire.system.service;

import com.wang.tujiquestionnaire.system.entity.UserGold;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wang
 * @since 2023-05-02
 */
public interface IUserGoldService extends IService<UserGold> {
    /**
     * 查询用户的激励值
     * @param id 用户id
     * @return 激励值数量
     */
    public Integer selectGold(String id);

}
