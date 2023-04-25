package com.wang.tujiquestionnaire.background.service;

import com.wang.tujiquestionnaire.background.entity.BackgroundUser;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wang
 * @since 2023-04-25
 */
public interface IBackgroundUserService extends IService<BackgroundUser> {

    /**
     * 根据前端出传来的信息进行分页查询
     * @param pageNum 页数
     * @param pageSize 分页大小
     * @param name 名字
     * @param username 账户
     * @return 返回放置查询数据以及分页数据
     */
    Map<String, Object> findpage(Integer pageNum,Integer pageSize,String name,String username);
}
