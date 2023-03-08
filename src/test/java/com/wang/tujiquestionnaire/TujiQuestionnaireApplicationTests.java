package com.wang.tujiquestionnaire;

import com.wang.tujiquestionnaire.system.entity.AnswerSon;
import com.wang.tujiquestionnaire.system.mapper.AnswerSonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TujiQuestionnaireApplicationTests {
@Autowired
    AnswerSonMapper answerSonMapper;
    @Test
    void contextLoads() {
        AnswerSon answerSon = new AnswerSon();
        answerSon.setAnswerId(123456L);
        answerSon.setId(45678945L);
        answerSon.setOptionId(456787L);
        List<AnswerSon> list = new ArrayList<>();
        list.add(answerSon);
        answerSonMapper.insertAnswerSonList(list);
    }

}
