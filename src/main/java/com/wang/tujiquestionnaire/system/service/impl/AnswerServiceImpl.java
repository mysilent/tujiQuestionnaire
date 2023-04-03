package com.wang.tujiquestionnaire.system.service.impl;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.Answer;
import com.wang.tujiquestionnaire.system.entity.AnswerSon;
import com.wang.tujiquestionnaire.system.entity.UserCreateAnswer;
import com.wang.tujiquestionnaire.system.entity.dto.AnswerDto;
import com.wang.tujiquestionnaire.system.mapper.AnswerMapper;
import com.wang.tujiquestionnaire.system.mapper.AnswerSonMapper;
import com.wang.tujiquestionnaire.system.mapper.UserCreateAnswerMapper;
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
    private AnswerSonMapper answerSonMapper;
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private UserCreateAnswerMapper userCreateAnswerMapper;
    @Autowired
    private HutoolUntil hutoolUntil;
    @Override
    public Result submitAnswer(AnswerDto answerDto) {
        Answer answer = new Answer();
        List<AnswerSon> answerSonLists = new ArrayList<>();
        UserCreateAnswer userCreateAnswer = new UserCreateAnswer();
        System.out.println(1);
        answerDto.setId(hutoolUntil.getID());
        userCreateAnswer.setId(hutoolUntil.getID());
        userCreateAnswer.setUserId(answerDto.getUserId());
        userCreateAnswer.setSurveyId(answerDto.getSurveyId());
        userCreateAnswer.setAnswerId(answerDto.getId());
        BeanUtils.copyProperties(answerDto,answer);
        List<AnswerSon> answerSonList = answerDto.getAnswerSonList();
        for (AnswerSon answerSon: answerSonList) {
            String id = hutoolUntil.getID();
            answerSon.setId(id);
            answerSon.setAnswerId(answerDto.getId());
            answerSonLists.add(answerSon);
        }
        try {
            submitAnswerSql(answer,answerSonLists,userCreateAnswer);
            return Result.success();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void submitAnswerSql(Answer answer,List<AnswerSon> answerSonLists ,UserCreateAnswer userCreateAnswer){
            answerMapper.insert(answer);
            answerSonMapper.insertAnswerSonList(answerSonLists);
            userCreateAnswerMapper.insert(userCreateAnswer);
    }

}
