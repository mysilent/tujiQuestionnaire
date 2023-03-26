package com.wang.tujiquestionnaire.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wang.tujiquestionnaire.common.Constant;
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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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
    public Result createQuestionnaire(SurveyCreateDto surveyCreateDto) {
        Survey survey = new Survey();
        Question question = null;
        //1.获取前端传输的各种信息
        //2.对各个信息放入对应的实例化对象中
        BeanUtils.copyProperties(surveyCreateDto,survey);
        List<QuestionDto> questionList= surveyCreateDto.getQuestionDtoList();
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
            question = new Question();
            BeanUtils.copyProperties(q,question);
            questionMapper.insert(question);
            //2.2判断问题类型进行分类存储
            //2.2.1对问题进行分类，分为填空、多选、单选
            //2.2.3将选项存入选项表中并将问题主键记录下来
            //2.2.4若为填空则停止
            if (Constant.QUESTION_TYPE_DAN.equals(q.getQuestionType()) || Constant.QUESTION_TYPE_DUO.equals(q.getQuestionType())){
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
    @Override
    //TODO 重写createQuestionnaire方法将sql语句执行剔除出来构成一个新的事务方法
    public Result createQuestionnaire1(SurveyCreateDto surveyCreateDto) {
        Survey survey = new Survey();
        Question question = null;
        //1.获取前端传输的各种信息
        //2.对各个信息放入对应的实例化对象中
        BeanUtils.copyProperties(surveyCreateDto,survey);
        List<QuestionDto> questionList= surveyCreateDto.getQuestionDtoList();
        List<Question> questionLists= new ArrayList<>();
        List<Option> optionLists = new ArrayList<>();
        //2.1首先存放问卷信息标注谁发布的并获取其id键值
        //2.1.1创建调查问卷主表id
        Long  surveyId = hutoolUntil.getID();
        survey.setId(surveyId);
        //2.2存入调查问卷问题主表
        for (QuestionDto q:questionList) {
            Long questionId = hutoolUntil.getID();
            q.setSurveyId(surveyId);
            q.setId(questionId);
            //2.2.1将questiondto中属于question的复制进去传入question表
            question = new Question();
            BeanUtils.copyProperties(q,question);
            questionLists.add(question);
            //2.3判断问题类型进行分类存储
            //2.3.1对问题进行分类，分为填空、多选、单选
            if (Constant.QUESTION_TYPE_DAN.equals(q.getQuestionType()) || Constant.QUESTION_TYPE_DUO.equals(q.getQuestionType())){
                List<Option> optionList = q.getOptionList();
                for (Option o:optionList) {
                    //2.2.2若为单选、多选将问题表的主键记录下来
                    Long optionId = hutoolUntil.getID();
                    o.setSurveyId(surveyId);
                    o.setQuestionId(questionId);
                    o.setId(optionId);
                    //2.3.3将选项存入数据库
                    optionLists.add(o);
                }
            }
        }
        try {
            createQuestionnaireSql(survey,questionLists,optionLists);
            return Result.success();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public void createQuestionnaireSql(Survey survey,List<Question> questionLists,List<Option> optionLists){
        surveyMapper.insert(survey);
        questionMapper.insertQuestionList(questionLists);
        optionMapper.insertOptionList(optionLists);
    }


    @Override
    public SurveyCreateDto selectQuestionnaire(Long id) {
        //mybatis-plus 条件查询构造器
        QueryWrapper<Question> queryWrapper =new QueryWrapper<>();
        QueryWrapper<Option> optionQueryWrapper = null;
        //surveyCreateDto对象实例化
        SurveyCreateDto surveyCreateDto = new SurveyCreateDto();
        //questionDto对象实例化（先定义名字，在循环中实例化，每次循环都进行实例化用来存储）
        QuestionDto questionDto=null;
        //dtoList时QuestionDto的一个列表实例化
        List<QuestionDto> dtoList=new ArrayList<>();
        //1.对问卷基础信息进行查询，并记录问卷id
        Survey survey = surveyMapper.selectById(id);
        BeanUtils.copyProperties(survey,surveyCreateDto);
        //2.0判断是否为选项，并将属于选项问题的选项加入到里面
        //2.1根据问卷id查找属于该问卷的问题
        queryWrapper.eq("survey_id",id);
        //2.2将每一个问卷的问题查询出来并放入questionList
        List<Question> questionList= questionMapper.selectList(queryWrapper);
        for (Question q:questionList) {
            questionDto = new QuestionDto();
            //2.3判断是否为选项
            System.out.println(q.getQuestionType());
            //TODO 取消掉去数据库查询问题类型，改为直接读取前面查询到的数据中的
//            if (questionMapper.selectQuestionType(q.getId())!=3){
            if (!Constant.QUESTION_TYPE_TIAN.equals(q.getQuestionType())){
                //2.4根据问卷id以及问题id查询对应选项
                Long questionId =q.getId();
                optionQueryWrapper = new QueryWrapper<>();
                optionQueryWrapper.eq("survey_id",id);
                optionQueryWrapper.eq("question_id",questionId);
                List<Option> optionList = optionMapper.selectList(optionQueryWrapper);
                //2.5将选项分别添加到对应的问题列表dtoList中
                    questionDto.setOptionList(optionList);
                    BeanUtils.copyProperties(q,questionDto);
                    dtoList.add(questionDto);
            }
        }
        //3.将问题以及带有选项的加入到问卷对象surveyCreateDto上
        surveyCreateDto.setQuestionDtoList(dtoList);
        return surveyCreateDto;
    }

    @Override
    public List<Survey> selectUserSurvey( Long id) {
        QueryWrapper<Survey> queryWrapper =new QueryWrapper<>();
        queryWrapper.eq("creator_id",id);
        List<Survey> surveys = surveyMapper.selectList(queryWrapper);
        return surveys;
    }


}
