package com.wang.tujiquestionnaire.background.service.impl;

import com.wang.tujiquestionnaire.background.entity.SurveyTemplate;
import com.wang.tujiquestionnaire.background.mapper.SurveyTemplateMapper;
import com.wang.tujiquestionnaire.background.service.ISurveyTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.tujiquestionnaire.common.Constant;
import com.wang.tujiquestionnaire.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wang
 * @since 2023-04-26
 */
@Service
public class SurveyTemplateServiceImpl extends ServiceImpl<SurveyTemplateMapper, SurveyTemplate> implements ISurveyTemplateService {
    @Autowired
    SurveyTemplateMapper surveyTemplateMapper;

    /**
     * @param pageSize
     * @param pageNum
     * @param state
     * @param surveyName
     * @param surveyId
     * @return
     */
    @Override
    public Map<String, Object> selectTemplate(Integer pageSize, Integer pageNum, String state,String surveyName, String surveyId) {

        pageNum = (pageNum - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        Integer total = surveyTemplateMapper.templateTotal(state);
        List<SurveyTemplate> data = surveyTemplateMapper.templatepage(pageSize,pageNum,state,surveyName,surveyId);
        map.put("total", total);
        map.put("data", data);
        return map;
    }


    @Override
    public Map<String, Object> templateApplicationpage(Integer pageSize, Integer pageNum, String state) {
        pageNum = (pageNum - 1) * pageSize;
        Map<String, Object> map = new HashMap<>();
        List<SurveyTemplate> templatePage = surveyTemplateMapper.templateApplicationpage(pageSize, pageNum, state);
        Integer total = surveyTemplateMapper.templateTotal(state);
        map.put("total", total);
        map.put("data", templatePage);
        return map;
    }

    @Override
    public Result templateApplicationYes(String id) {
        return surveyTemplateMapper.templateYes(Constant.TEMPLATE_YES, id) == 1 ? Result.success() : Result.error();
    }

    @Override
    public Result templateApplicationNo(String id) {
        return surveyTemplateMapper.templateNo(id) == 1 ? Result.success() : Result.error();
    }
}
