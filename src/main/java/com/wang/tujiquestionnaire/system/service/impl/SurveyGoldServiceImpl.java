package com.wang.tujiquestionnaire.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.Survey;
import com.wang.tujiquestionnaire.system.entity.SurveyGold;
import com.wang.tujiquestionnaire.system.entity.dto.SurveyGoldDto;
import com.wang.tujiquestionnaire.system.mapper.SurveyGoldMapper;
import com.wang.tujiquestionnaire.system.mapper.SurveyMapper;
import com.wang.tujiquestionnaire.system.mapper.UserGoldMapper;
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
    UserGoldMapper userGoldMapper;
    @Autowired
    SurveyMapper surveyMapper;

    @Override
    public Result surveyPublish(SurveyGoldDto surveyGoldDto) {
        //1.先进行surveyGold的新建数据对象
        SurveyGold surveyGold = new SurveyGold();
        BeanUtils.copyProperties(surveyGoldDto, surveyGold);
        //1.1 去数据库查询该问卷有没有被发布,若能查询到数据则已经发布过,报错；若没数据,则未发布继续执行下面逻辑
        QueryWrapper<SurveyGold> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",surveyGoldDto.getId()).eq("user_id",surveyGoldDto.getUserId());
        Long aLong = surveyGoldMapper.selectCount(queryWrapper);
        if (aLong==1){
            return Result.error(1000,"该问卷已经发布");
        }
        // 下面两个是接受问卷修改值的，若其中一个为零则错误
        int insert;
        Integer status;
        //2.若用户选择激励值发布问卷，则使用该方法对用户进行激励值的扣除
        if (surveyGoldDto.getPrice()!=0){
            //2.1 计算所需的激励值
            Integer gold = surveyGoldDto.getPrice() * surveyGoldDto.getQuantity();
            //2.2判断激励值是否够发布
            if (userGoldMapper.selectGold(surveyGoldDto.getUserId())>=gold){
                userGoldMapper.userGoldSub(gold,surveyGoldDto.getUserId());
                //3.将问卷发布状况写入数据库
                 insert = surveyGoldMapper.insert(surveyGold);
                //4.更改问卷发布状态
                 status = surveyMapper.updateStatusById("0", surveyGoldDto.getId());
            }else {
                return Result.error(1000,"激励值不够呢亲~");
            }
        }else {
            //3.将问卷发布状况写入数据库
             insert = surveyGoldMapper.insert(surveyGold);
            //4.更改问卷发布状态
             status = surveyMapper.updateStatusById("0", surveyGoldDto.getId());
        }
        return insert==1&&status==1?Result.success():Result.error();
    }
}
