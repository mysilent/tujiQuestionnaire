package com.wang.tujiquestionnaire.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.tujiquestionnaire.common.Constant;
import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.*;
import com.wang.tujiquestionnaire.system.entity.dto.AnswerBySurveyData;
import com.wang.tujiquestionnaire.system.entity.dto.AnswerDataDto;
import com.wang.tujiquestionnaire.system.entity.dto.AnswerDto;
import com.wang.tujiquestionnaire.system.mapper.*;
import com.wang.tujiquestionnaire.system.service.IAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.tujiquestionnaire.until.HutoolUntil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Block;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 * 用户答案表 服务实现类
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements IAnswerService {
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private OptionMapper optionMapper;
    @Autowired
    private UserCreateAnswerMapper userCreateAnswerMapper;
    @Autowired
    private HutoolUntil hutoolUntil;
    @Autowired
    private UserHistoryMapper userHistoryMapper;
    @Autowired
    private UserGoldMapper userGoldMapper;
    @Autowired
    private SurveyGoldMapper surveyGoldMapper;
    @Autowired
    private SurveyMapper surveyMapper;

    @Override
    public Result submitAnswer(AnswerDto answerDto) {
        //1. 判断用户提交过该问卷吗
        QueryWrapper<Answer> answerQueryWrapper = new QueryWrapper<>();
        answerQueryWrapper.eq("survey_id", answerDto.getSurveyId()).eq("user_id", answerDto.getUserId());
        Long answerCount = answerMapper.selectCount(answerQueryWrapper);
        if (answerCount != 0) {
            return Result.error(1000, "您已经作答过该问卷~");
        }
        //2. 新增激励值功能
        //2.1查询该问卷的激励制度若是有则进行激励逻辑，若没有则跳过
        QueryWrapper<SurveyGold> surveyGoldQueryWrapper = new QueryWrapper<>();
        surveyGoldQueryWrapper.eq("id", answerDto.getSurveyId());
        SurveyGold surveyGold = surveyGoldMapper.selectOne(surveyGoldQueryWrapper);
        //2.2查询到问卷有激励值奖励则调用下面逻辑，没有则跳过
        if (surveyGold.getPrice() != 0) {
            if (surveyGold.getQuantity() <= 0) {
                return Result.error(1000, "该问卷已经被提前作答完啦~");
            } else {
                submitAnswerGoldSql(answerDto, surveyGold);
            }
        }
        //3.创建所需要的对象
        Answer answer = new Answer();
        UserHistory userHistory = new UserHistory();
        List<Answer> answerList = new ArrayList<>();
        List<UserCreateAnswer> userCreateAnswerList = new ArrayList<>();
        UserCreateAnswer userCreateAnswer = new UserCreateAnswer();
        String[] questionList = answerDto.getQuestionList();
        String[] optionList = answerDto.getAnswers();

        //4.进行数据处理
        //4.1答案表进行用户id以及问卷id的赋值
        answer.setSurveyId(answerDto.getSurveyId());
        answer.setUserId(answerDto.getUserId());
        //4.2用户创建答案表进行用户id以及问卷id的赋值，创建一个唯一的用户创建id作为标记以供用户历史表做标记
        userCreateAnswer.setSurveyId(answerDto.getSurveyId());
        userCreateAnswer.setUserId(answerDto.getUserId());
        userCreateAnswer.setUserCreateId(hutoolUntil.getID());
        //4.3对问卷问题进行遍历往用户答案表以及用户创建答案表进行某些属性的赋值
        for (int i = 1; i < questionList.length; i++) {
            Answer answer1 = new Answer();
            UserCreateAnswer userCreateAnswer1 = new UserCreateAnswer();
            BeanUtils.copyProperties(answer, answer1);
            BeanUtils.copyProperties(userCreateAnswer, userCreateAnswer1);
            String s = questionList[i];
            String o = optionList[i];
            answer1.setId(hutoolUntil.getID());
            answer1.setQuestionId(s);
            answer1.setOptionContent(o);
            userCreateAnswer1.setId(hutoolUntil.getID());
            userCreateAnswer1.setAnswerId(answer1.getId());
            answerList.add(answer1);
            userCreateAnswerList.add(userCreateAnswer1);
        }
        //4.5用户历史表进行赋值
        userHistory.setUserId(answerDto.getUserId());
        userHistory.setSurveyId(answerDto.getSurveyId());
        userHistory.setUserCreateId(userCreateAnswer.getUserCreateId());
        userHistory.setId(hutoolUntil.getID());
        userHistory.setSurveyName(answerDto.getSurveyName());

        //6调用sql进行存储
        submitAnswerSql(answerList, userCreateAnswerList, userHistory);
        return Result.success(surveyGold.getPrice());
    }

    //该方法将上面逻辑一部分sql调用封装起来增加事务
    @Transactional(rollbackFor = Exception.class)
    public void submitAnswerSql(List<Answer> answerList, List<UserCreateAnswer> userCreateAnswerList, UserHistory userHistory) {
        answerMapper.insertAnswerList(answerList);
        userCreateAnswerMapper.insertUserCreateAnswerList(userCreateAnswerList);
        userHistoryMapper.insert(userHistory);
    }

    @Transactional(rollbackFor = Exception.class)
    public Boolean submitAnswerGoldSql(AnswerDto answerDto, SurveyGold surveyGold) {
        Integer deleteOne = surveyGoldMapper.quantityDeleteOne(answerDto.getSurveyId());
        Integer goldAdd = userGoldMapper.userGoldAdd(surveyGold.getPrice(), answerDto.getUserId());
        QueryWrapper<SurveyGold> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",surveyGold.getId());
        SurveyGold surveyGold1 = surveyGoldMapper.selectOne(queryWrapper);
        if (surveyGold1.getQuantity()==0){
            surveyMapper.updateStatusById(Constant.SURVEY_STATUS_END,surveyGold1.getId());
        }
        return deleteOne == 1 && goldAdd == 1;
    }

    @Override
    public Result historyAnswer(String userId, String surveyId) {
        QueryWrapper<Answer> answerQueryWrapper = new QueryWrapper<>();
        answerQueryWrapper.eq("survey_id", surveyId);
        answerQueryWrapper.eq("user_id", userId);
        List<Answer> userHistoryList = answerMapper.selectList(answerQueryWrapper);
        return Result.success(userHistoryList);
    }

    @Override
    public Result selectDataBySurveyId(String id) {
        Survey survey = surveyMapper.selectById(id);
        List<AnswerDataDto> dataBySurveyId = answerMapper.selectDataBySurveyId(id);
        List<Option> optionList = optionMapper.selectAllBySurveyId(id);
        List<Map<String, String>> any = answerMapper.selectAnyDataBySurvey(id,Constant.QUESTION_TYPE_DUO);
        List<Map<String, Object>> one = answerMapper.selectOneDataBySurveyId(id,Constant.QUESTION_TYPE_DAN);
        List<Map<String, Object>> input = answerMapper.selectInputDataBySurveyId(id,Constant.QUESTION_TYPE_TIAN);
        Map<String,Object> surveyAnyData = new HashMap<>(0);
        //2.多选选项数量处理
        for ( Map<String,String> map:any) {
            String count=map.get("count");
            String[] countList  = count.split(",");
            Map<String, Integer> countMap = new HashMap<>(0);
            for (String value : countList) {
                countMap.put(value, countMap.getOrDefault(value, 0) + 1);
            }
            Map<String, Integer> surveyAny = new HashMap<>(countMap);
            surveyAnyData.put(map.get("question_id"),surveyAny);
        }
        AnswerBySurveyData answer = new AnswerBySurveyData();
        answer.setOneOptionData(one);
        answer.setAnyOptionData(surveyAnyData);
        answer.setOptionList(optionList);
        answer.setInputOptionData(input);
        answer.setAnswerDataDtoList(dataBySurveyId);
        answer.setSurveyName(survey.getSurveyName());
        answer.setSurveyDescription(survey.getSurveyDescription());
        return Result.success(answer);

    }

    @Override
    public Result inputAnswer(String surveyId, String questionId, String content, Integer pageNum, Integer pageSize) {
        pageNum = (pageNum - 1) * pageSize;
        List<Answer> answerList = answerMapper.inputAnswer(surveyId, questionId, content, pageNum, pageSize);
        Integer integer = answerMapper.inputAnswerTotal(surveyId, questionId, content);
        Map<String,Object> map = new HashMap<>(0);
        map.put("data",answerList);
        map.put("total",integer);
        return Result.success(map);
    }
}
