package com.wang.tujiquestionnaire.system.service.impl;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.Answer;
import com.wang.tujiquestionnaire.system.entity.UserCreateAnswer;
import com.wang.tujiquestionnaire.system.entity.UserHistory;
import com.wang.tujiquestionnaire.system.entity.dto.AnswerDto;
import com.wang.tujiquestionnaire.system.mapper.AnswerMapper;
import com.wang.tujiquestionnaire.system.mapper.UserCreateAnswerMapper;
import com.wang.tujiquestionnaire.system.mapper.UserHistoryMapper;
import com.wang.tujiquestionnaire.system.service.IAnswerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.tujiquestionnaire.until.HutoolUntil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    private UserCreateAnswerMapper userCreateAnswerMapper;
    @Autowired
    private HutoolUntil hutoolUntil;
    @Autowired
    private UserHistoryMapper userHistoryMapper;

    @Override
    public Result submitAnswer(AnswerDto answerDto) {
        //1.创建所需要的对象
        Answer answer = new Answer();
        UserHistory userHistory = new UserHistory();
        List<Answer> answerList = new ArrayList<>();
        List<UserCreateAnswer>userCreateAnswerList =new ArrayList<>();
        UserCreateAnswer userCreateAnswer = new UserCreateAnswer();
        String[] questionList = answerDto.getQuestionList();
        String[] optionList = answerDto.getAnswers();

        //2.进行数据处理
        //2.1答案表进行用户id以及问卷id的赋值
        answer.setSurveyId(answerDto.getSurveyId());
        answer.setUserId(answerDto.getUserId());
        //2.2用户创建答案表进行用户id以及问卷id的赋值，创建一个唯一的用户创建id作为标记以供用户历史表做标记
        userCreateAnswer.setSurveyId(answerDto.getSurveyId());
        userCreateAnswer.setUserId(answerDto.getUserId());
        userCreateAnswer.setUserCreateId(hutoolUntil.getID());
        //2.3对问卷问题进行遍历往用户答案表以及用户创建答案表进行某些属性的赋值
        for (int i = 1;i<questionList.length;i++) {
            Answer answer1 = new Answer();
            UserCreateAnswer userCreateAnswer1 = new UserCreateAnswer();
            BeanUtils.copyProperties(answer,answer1);
            BeanUtils.copyProperties(userCreateAnswer,userCreateAnswer1);
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
        //2.5用户历史表进行赋值
        userHistory.setUserId(answerDto.getUserId());
        userHistory.setSurveyId(answerDto.getSurveyId());
        userHistory.setUserCreateId(userCreateAnswer.getUserCreateId());
        userHistory.setId(hutoolUntil.getID());
        userHistory.setSurveyName(answerDto.getSurveyName());

        //3调用sql进行存储
        submitAnswerSql(answerList,userCreateAnswerList,userHistory);
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public void submitAnswerSql(List<Answer> answerList,List<UserCreateAnswer> userCreateAnswerList,UserHistory userHistory){
        answerMapper.insertAnswerList(answerList);
        userCreateAnswerMapper.insertUserCreateAnswerList(userCreateAnswerList);
        userHistoryMapper.insert(userHistory);
    }
}
