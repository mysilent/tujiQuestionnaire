package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.system.entity.UserHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户答卷作答历史记录 Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-04-14
 */
@Mapper
public interface UserHistoryMapper extends BaseMapper<UserHistory> {

}
