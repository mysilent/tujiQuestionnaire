package com.wang.tujiquestionnaire.system.service;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.UserHistory;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户答卷作答历史记录 服务类
 * </p>
 *
 * @author wang
 * @since 2023-04-14
 */
public interface IUserHistoryService extends IService<UserHistory> {

    /**
     * 根据前端传入id查询用户作答历史记录
     * @param id 前端传来的用户id
     * @return 返回封装的统一接口
     */
    Result history(String id);
}
