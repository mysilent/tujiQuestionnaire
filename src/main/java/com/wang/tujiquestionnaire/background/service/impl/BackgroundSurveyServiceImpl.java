package com.wang.tujiquestionnaire.background.service.impl;

import com.wang.tujiquestionnaire.background.entity.BackgroundUser;
import com.wang.tujiquestionnaire.background.entity.SurveyAndUser;
import com.wang.tujiquestionnaire.background.mapper.BackgroundSurveyMapper;
import com.wang.tujiquestionnaire.background.service.IBackgroundSurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: BackgroundSurveyServiceImpl
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/4/26-15:28
 */
@Service
public class BackgroundSurveyServiceImpl implements IBackgroundSurveyService {
    @Resource
    BackgroundSurveyMapper backgroundSurveyMapper;

    @Override
    public Map<String, Object> pageSurvey(Integer pageNum, Integer pageSize, String nickname, String surveyName, String id) {
        pageNum = (pageNum - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        Integer total = backgroundSurveyMapper.selectTotal(nickname,surveyName,id);
        List<SurveyAndUser> data = backgroundSurveyMapper.findPage(pageNum, pageSize,nickname,surveyName,id);
        map.put("data", data);
        map.put("total", total);
        return map;
    }
}
