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
import org.springframework.web.bind.annotation.RequestBody;

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
    public Result createQuestionnaire(@RequestBody SurveyCreateDto surveyCreateDto) {
        Survey survey = new Survey();
        Question question = null;
        //1.获取前端传输的各种信息
        //2.对各个信息放入对应的实例化对象中
        BeanUtils.copyProperties(surveyCreateDto, survey);
        List<QuestionDto> questionList = surveyCreateDto.getQuestionDtoList();
        //2.1首先存放问卷信息标注谁发布的并获取其id键值
        //2.1.1创建调查问卷主表id
        String surveyId = hutoolUntil.getID();
        survey.setId(surveyId);
        //2.2.2存入调查问卷主表
        surveyMapper.insert(survey);
        //2.2.3存入调查问卷问题主表
        for (QuestionDto q : questionList) {
            String questionId = hutoolUntil.getID();
            q.setSurveyId(surveyId);
            q.setId(questionId);
            //2.2.3.1将questiondto中属于question的复制进去传入question表
            question = new Question();
            BeanUtils.copyProperties(q, question);
            questionMapper.insert(question);
            //2.2判断问题类型进行分类存储
            //2.2.1对问题进行分类，分为填空、多选、单选
            //2.2.3将选项存入选项表中并将问题主键记录下来
            //2.2.4若为填空则停止
            if (Constant.QUESTION_TYPE_DAN.equals(q.getQuestionType()) || Constant.QUESTION_TYPE_DUO.equals(q.getQuestionType())) {
                List<Option> optionList = q.getOptionList();
                if (optionList != null) {
                    for (Option o : optionList) {
                        //2.2.2若为单选、多选将问题表的主键记录下来
                        String optionId = hutoolUntil.getID();
                        o.setSurveyId(surveyId);
                        o.setQuestionId(questionId);
                        o.setId(optionId);
                        //2.2.3将选项存入数据库
                        optionMapper.insert(o);
                    }
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
        BeanUtils.copyProperties(surveyCreateDto, survey);
        List<QuestionDto> questionList = surveyCreateDto.getQuestionDtoList();
        List<Question> questionLists = new ArrayList<>();
        List<Option> optionLists = new ArrayList<>();
        //2.1首先存放问卷信息标注谁发布的并获取其id键值
        //2.1.1创建调查问卷主表id
        String surveyId = hutoolUntil.getID();
        survey.setId(surveyId);
        //2.2存入调查问卷问题主表
        for (QuestionDto q : questionList) {
            String questionId = hutoolUntil.getID();
            q.setSurveyId(surveyId);
            q.setId(questionId);
            //2.2.1将questiondto中属于question的复制进去传入question表
            question = new Question();
            BeanUtils.copyProperties(q, question);
            questionLists.add(question);
            //2.3判断问题类型进行分类存储
            //2.3.1对问题进行分类，分为填空、多选、单选
            if (Constant.QUESTION_TYPE_DAN.equals(q.getQuestionType()) || Constant.QUESTION_TYPE_DUO.equals(q.getQuestionType())) {
                List<Option> optionList = q.getOptionList();
                for (Option o : optionList) {
                    //2.2.2若为单选、多选将问题表的主键记录下来
                    String optionId = hutoolUntil.getID();
                    o.setSurveyId(surveyId);
                    o.setQuestionId(questionId);
                    o.setId(optionId);
                    //2.3.3将选项存入数据库
                    optionLists.add(o);
                }
            }
        }

        createQuestionnaireSql(survey, questionLists, optionLists);
        return Result.success(surveyId);
    }

    @Transactional(rollbackFor = Exception.class)
    protected void createQuestionnaireSql(Survey survey, List<Question> questionLists, List<Option> optionLists) {
        surveyMapper.insert(survey);
        questionMapper.insertQuestionList(questionLists);
        System.out.println(optionLists.isEmpty());
        if (!optionLists.isEmpty()) {
            optionMapper.insertOptionList(optionLists);
        }
    }

    @Override
    public SurveyCreateDto selectQuestionnaire(String id) {
        //mybatis-plus 条件查询构造器
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        QueryWrapper<Option> optionQueryWrapper = null;
        //surveyCreateDto对象实例化
        SurveyCreateDto surveyCreateDto = new SurveyCreateDto();
        //questionDto对象实例化（先定义名字，在循环中实例化，每次循环都进行实例化用来存储）
        QuestionDto questionDto = null;
        //dtoList时QuestionDto的一个列表实例化
        List<QuestionDto> dtoList = new ArrayList<>();
        //1.对问卷基础信息进行查询，并记录问卷id
        System.out.println("id="+id);
        Survey survey = surveyMapper.selectById(id);
        System.out.println(survey);
        BeanUtils.copyProperties(survey, surveyCreateDto);
        //2.0判断是否为选项，并将属于选项问题的选项加入到里面
        //2.1根据问卷id查找属于该问卷的问题
        queryWrapper.eq("survey_id", id);
        //2.2将每一个问卷的问题查询出来并放入questionList
        List<Question> questionList = questionMapper.selectList(queryWrapper);
        System.out.println();
        for (Question q : questionList) {
            questionDto = new QuestionDto();
            //2.3判断是否为选项
            if (!Constant.QUESTION_TYPE_TIAN.equals(q.getQuestionType())) {
                //2.4根据问卷id以及问题id查询对应选项
                String questionId = q.getId();
                optionQueryWrapper = new QueryWrapper<>();
                optionQueryWrapper.eq("survey_id", id);
                optionQueryWrapper.eq("question_id", questionId);
                List<Option> optionList = optionMapper.selectList(optionQueryWrapper);
                //2.5将选项分别添加到对应的问题列表dtoList中
                questionDto.setOptionList(optionList);
            }
            BeanUtils.copyProperties(q, questionDto);
            dtoList.add(questionDto);
        }
        //3.将问题以及带有选项的加入到问卷对象surveyCreateDto上
        surveyCreateDto.setQuestionDtoList(dtoList);
        return surveyCreateDto;
    }

    @Override
    public List<Survey> selectUserSurvey(Long id) {
        QueryWrapper<Survey> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("creator_id", id);
        return surveyMapper.selectList(queryWrapper);
    }


    @Override
    public Result deleteQuestionnaire(String id) {
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.eq("survey_id", id);
        QueryWrapper<Option> optionQueryWrapper = new QueryWrapper<>();
        optionQueryWrapper.eq("survey_id", id);
        int i = deleteQuestionnaireSql(id, questionQueryWrapper, optionQueryWrapper);
        return i == 1 ? Result.success() : Result.error();
    }

    @Transactional(rollbackFor = Exception.class)
    protected int deleteQuestionnaireSql(String id, QueryWrapper<Question> questionQueryWrapper, QueryWrapper<Option> optionQueryWrapper) {
        int i = surveyMapper.deleteById(id);
        questionMapper.delete(questionQueryWrapper);
        optionMapper.delete(optionQueryWrapper);
        return i == 1 ? 1 : 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result reviseQuestionnaire(SurveyCreateDto surveyCreateDto) {
/*        //1.接受前端对问卷修改之后的数据,进行数据提取
        //1.1获取survey的数据
        Survey survey = new Survey();
        BeanUtils.copyProperties(surveyCreateDto,survey);
        //1.2获取question的数据  (questionList)
        //survey中的questionDto的列表
        List<Question> questionList = new ArrayList<Question>();
        List<Option> optionList = new ArrayList<Option>();
        Question question = new Question();
        List<QuestionDto> questionDtoList = surveyCreateDto.getQuestionDtoList();
        for (QuestionDto questionDto:questionDtoList) {
            BeanUtils.copyProperties(questionDto,question);
            questionList.add(question);
            optionList.addAll(questionDto.getOptionList());
        }
        System.out.println(questionList);
        System.out.println(optionList);
        //1.4进行构造方法的设置
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.eq("survey_id", survey.getId());
        QueryWrapper<Option> optionQueryWrapper = new QueryWrapper<>();
        optionQueryWrapper.eq("survey_id", survey.getId());
        //2.调用update方法对问卷信息主表进行修改
        try {
            reviseQuestionnaireSql(survey, questionQueryWrapper, optionQueryWrapper, questionList, optionList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/

        deleteQuestionnaire(surveyCreateDto.getId());
        return createQuestionnaire1(surveyCreateDto);
    }

/*    @Transactional(rollbackFor = Exception.class)
    protected void reviseQuestionnaireSql(Survey survey,QueryWrapper<Question> questionQueryWrapper, QueryWrapper<Option> optionQueryWrapper, List<Question> questionList, List<Option> optionList) {
        surveyMapper.updateById(survey);
        //3.将对应该问卷id的问题进行删除
        questionMapper.delete(questionQueryWrapper);
        //3.1删除之后就调用sql对问题进行重新储存
        questionMapper.insertQuestionList(questionList);
        //4.将对应问卷id的选项进行删除
        optionMapper.delete(optionQueryWrapper);
        //4.1选项删除之后在对选项进行重新存储
        optionMapper.insertOptionList(optionList);
    }*/


    @Override
    public List<Survey> selectOtherUserSurvey(String id) {
        QueryWrapper<Survey> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("creator_id", id);
        return surveyMapper.selectList(queryWrapper);
    }

}
