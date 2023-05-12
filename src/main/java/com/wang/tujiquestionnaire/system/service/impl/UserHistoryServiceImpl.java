package com.wang.tujiquestionnaire.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.UserHistory;
import com.wang.tujiquestionnaire.system.mapper.UserHistoryMapper;
import com.wang.tujiquestionnaire.system.service.IUserHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户答卷作答历史记录 服务实现类
 * </p>
 *
 * @author wang
 * @since 2023-04-14
 */
@Service
public class UserHistoryServiceImpl extends ServiceImpl<UserHistoryMapper, UserHistory> implements IUserHistoryService {
@Autowired
private UserHistoryMapper userHistoryMapper;
    @Override
    public Result history(String id) {
        QueryWrapper<UserHistory> userHistoryQueryWrapper = new QueryWrapper<>();
        userHistoryQueryWrapper.eq("user_id",id);
        List<UserHistory> userHistories = userHistoryMapper.selectList(userHistoryQueryWrapper);
        return Result.success(userHistories);
    }

    @Override
    public Map<Object,Object> createAnswerData(String userId) {
        List<Map<String, Object>> answerData = userHistoryMapper.createAnswerData(userId);
        Map<Object,Object>maps = new HashMap<>();
        for (Map<String, Object> map : answerData) {
            Object surveyId =  map.get("survey_id");
            Object count =  map.get("count");
            maps.put(surveyId, count);
        }
        return maps;
    }
}
