package com.wang.tujiquestionnaire.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.Survey;
import com.wang.tujiquestionnaire.system.entity.SurveyGold;
import com.wang.tujiquestionnaire.system.entity.dto.SurveyGoldDto;
import com.wang.tujiquestionnaire.system.mapper.SurveyGoldMapper;
import com.wang.tujiquestionnaire.system.mapper.SurveyMapper;
import com.wang.tujiquestionnaire.system.service.ISurveyGoldService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author wang
 * @since 2023-05-02
 */
@Service
public class SurveyGoldServiceImpl extends ServiceImpl<SurveyGoldMapper, SurveyGold> implements ISurveyGoldService {

    @Autowired
    SurveyGoldMapper surveyGoldMapper;
    @Autowired
    SurveyMapper surveyMapper;

    @Override
    public Result surveyPublish(SurveyGoldDto surveyGoldDto) {
        //1.先进行surveygold的新建数据
        SurveyGold surveyGold = new SurveyGold();
        BeanUtils.copyProperties(surveyGoldDto, surveyGold);
        QueryWrapper<SurveyGold> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",surveyGoldDto.getId()).eq("user_id",surveyGoldDto.getUserId());
        Long aLong = surveyGoldMapper.selectCount(queryWrapper);
        if (aLong==1){
            return Result.error(1000,"该问卷已经发布");
        }
        int insert = surveyGoldMapper.insert(surveyGold);
        //2.更改问卷发布状态
        Integer status = surveyMapper.updateStatusById("0", surveyGoldDto.getId());
        return insert==1&&status==1?Result.success():Result.error();
    }
}
