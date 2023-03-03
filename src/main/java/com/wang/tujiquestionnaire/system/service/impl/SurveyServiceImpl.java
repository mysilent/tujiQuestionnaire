package com.wang.tujiquestionnaire.system.service.impl;

import com.wang.tujiquestionnaire.common.Result;
import com.wang.tujiquestionnaire.system.entity.dto.QuestionDto;
import com.wang.tujiquestionnaire.system.entity.dto.SurveyCreateDto;
import com.wang.tujiquestionnaire.system.entity.Option;
import com.wang.tujiquestionnaire.system.entity.Question;
import com.wang.tujiquestionnaire.system.entity.Survey;
import com.wang.tujiquestionnaire.system.mapper.OptionMapper;
import com.wang.tujiquestionnaire.system.mapper.QuestionMapper;
import com.wang.tujiquestionnaire.system.mapper.SurveyMapper;
import com.wang.tujiquestionnaire.system.service.ISurveyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wang.tujiquestionnaire.until.HutoolUntil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 调查问卷主表 服务实现类
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Service
public class SurveyServiceImpl extends ServiceImpl<SurveyMapper, Survey> implements ISurveyService {

   @Autowired
   HutoolUntil hutoolUntil;
   @Autowired
   SurveyMapper surveyMapper;
   @Autowired
    QuestionMapper questionMapper;
   @Autowired
    OptionMapper optionMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result createquestionnaire(SurveyCreateDto surveyCreateDto) {
        Survey survey = new Survey();
        Question question = null;
        //1.获取前端传输的各种信息
        //2.对各个信息放入对应的实例化对象中
        BeanUtils.copyProperties(surveyCreateDto,survey);
        List<QuestionDto> questionList= surveyCreateDto.getQuestionList();
        //2.1首先存放问卷信息标注谁发布的并获取其id键值
        //2.1.1创建调查问卷主表id
        Long  surveyId = hutoolUntil.getID();
        survey.setId(surveyId);
       //2.2.2存入调查问卷主表
        surveyMapper.insert(survey);
        //2.2.3存入调查问卷问题主表
        for (QuestionDto q:questionList) {
            Long questionId = hutoolUntil.getID();
            q.setSurveyId(surveyId);
            q.setId(questionId);
            //2.2.3.1将questiondto中属于question的复制进去传入question表
            //TODO 若问卷存入数据库出错  可能是该处问题
            question = new Question();
            BeanUtils.copyProperties(q,question);
            questionMapper.insert(question);
            //2.2判断问题类型进行分类存储
            //2.2.1对问题进行分类，分为填空、多选、单选
            //2.2.3将选项存入选项表中并将问题主键记录下来
            //2.2.4若为填空则停止
            if ("1".equals(q.getQuestionType()) || "2".equals(q.getQuestionType())){
                List<Option> optionList = q.getOptionList();
                for (Option o:optionList) {
                    //2.2.2若为单选、多选将问题表的主键记录下来
                    Long optionId = hutoolUntil.getID();
                    o.setSurveyId(surveyId);
                    o.setQuestionId(questionId);
                    o.setId(optionId);
                    //2.2.3将选项存入数据库
                    optionMapper.insert(o);
                }

            }
        }

        return Result.success();
    }

}
