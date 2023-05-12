package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.system.entity.UserHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;
import java.util.List;

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
    /**
     * 根据前端传来的用户id去查询他的所有问卷的被作答次数
     * @param id 用户id
     * @return 返回查到的数据，1个列表里的map包含问卷id和该问卷被填写数
     */

    @Select("select count(*) as count,si.id as survey_id\n" +
            "     from survey_info si join user_history uh on si.id=uh.survey_id\n" +
            "    where si.creator_id=#{id,jdbcType=VARCHAR}\n" +
            "     group by si.id")
     List<Map<String, Object>> createAnswerData(@Param("id") String id);
}
