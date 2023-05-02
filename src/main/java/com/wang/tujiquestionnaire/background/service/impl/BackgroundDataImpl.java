package com.wang.tujiquestionnaire.background.service.impl;

import com.wang.tujiquestionnaire.background.mapper.BackgroundDataMapper;
import com.wang.tujiquestionnaire.background.service.IBackgroundDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * ClassName: BackgroundDataImpl
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/5/1-22:36
 */
@Service
public class BackgroundDataImpl implements IBackgroundDataService {
    @Autowired
    BackgroundDataMapper backgroundDataMapper;
    @Override
    public Map<String, String> surveyCreateDate() {
        List<Map<String, Object>> maps = backgroundDataMapper.surveyCreateDate();
        Map<String, String> dataMap = new HashMap<>();
        for (Map<String, Object> map : maps) {
            dataMap.put(map.get("date").toString(), map.get("count").toString());
        }
        return dataMap;
    }
    @Override
    public Map<String, String> answerCreateDate() {
        List<Map<String, Object>> maps = backgroundDataMapper.answerCreateDate();
        Map<String, String> dataMap = new HashMap<>();
        for (Map<String, Object> map : maps) {
            dataMap.put(map.get("date").toString(), map.get("count").toString());
        }
        return dataMap;
    }
}
