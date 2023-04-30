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
import java.util.Optional;

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
            //2.2.1将questionDto中属于question的复制进去传入question表
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
        return Result.success(null,surveyId);
    }

    @Transactional(rollbackFor = Exception.class)
    protected void createQuestionnaireSql(Survey survey, List<Question> questionLists, List<Option> optionLists) {
        surveyMapper.insert(survey);
        questionMapper.insertQuestionList(questionLists);
        if (!optionLists.isEmpty()) {
            optionMapper.insertOptionList(optionLists);
        }
    }

//    @Override
//    public SurveyCreateDto selectQuestionnaire(String id) {
//        //mybatis-plus 条件查询构造器
//        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
//        QueryWrapper<Option> optionQueryWrapper = null;
//        //surveyCreateDto对象实例化
//        SurveyCreateDto surveyCreateDto = new SurveyCreateDto();
//        //questionDto对象实例化（先定义名字，在循环中实例化，每次循环都进行实例化用来存储）
//        QuestionDto questionDto = null;
//        //dtoList时QuestionDto的一个列表实例化
//        List<QuestionDto> dtoList = new ArrayList<>();
//        //1.对问卷基础信息进行查询，并记录问卷id
//        Survey survey = surveyMapper.selectById(id);
//        if (survey!=null){
//            BeanUtils.copyProperties(survey, surveyCreateDto);
//            //2.0判断是否为选项，并将属于选项问题的选项加入到里面
//            //2.1根据问卷id查找属于该问卷的问题
//            queryWrapper.eq("survey_id", id);
//            //2.2将每一个问卷的问题查询出来并放入questionList
//            List<Question> questionList = questionMapper.selectList(queryWrapper);
//            System.out.println();
//            for (Question q : questionList) {
//                questionDto = new QuestionDto();
//                //2.3判断是否为选项
//                if (!Constant.QUESTION_TYPE_TIAN.equals(q.getQuestionType())) {
//                    //2.4根据问卷id以及问题id查询对应选项
//                    String questionId = q.getId();
//                    optionQueryWrapper = new QueryWrapper<>();
//                    optionQueryWrapper.eq("survey_id", id);
//                    optionQueryWrapper.eq("question_id", questionId);
//                    List<Option> optionList = optionMapper.selectList(optionQueryWrapper);
//                    //2.5将选项分别添加到对应的问题列表dtoList中
//                    questionDto.setOptionList(optionList);
//                }
//                BeanUtils.copyProperties(q, questionDto);
//                dtoList.add(questionDto);
//            }
//            //3.将问题以及带有选项的加入到问卷对象surveyCreateDto上
//            surveyCreateDto.setQuestionDtoList(dtoList);
//            return surveyCreateDto;
//        }
//        else {
//            return null;
//        }
//
//    }

    @Override
    public  SurveyCreateDto  selectQuestionnaire(String  id)  {
        System.out.println(id);
        //      建立一个空的  Optional<Survey>  对象，初始化为  null  可以有效避免  NullPointerException  的风险。
        Optional<Survey>  optionalSurvey  =  Optional.ofNullable(surveyMapper.selectById(id));
        if  (optionalSurvey.isPresent())  {
            //  如果  optionalSurvey  内容存在，则将内容赋值给  survey  变量，get()  函数用于获取  optionalSurvey  中的对象。
            Survey  survey  =  optionalSurvey.get();
            //  定义一个新的  SurveyCreateDto  对象
            SurveyCreateDto  surveyCreateDto  =  new  SurveyCreateDto();
            //  BeanUtils.copyProperties()  函数用于将一个对象中的值复制到另一个对象中。
            BeanUtils.copyProperties(survey,  surveyCreateDto);
            //  定义一个新的  ArrayList<QuestionDto>  对象
            List<QuestionDto>  dtoList  =  new  ArrayList<>();
            //  定义一个  QueryWrapper<Question>  对象，并设置查询条件为  survey_id=id
            QueryWrapper<Question>  queryWrapper  =  new  QueryWrapper<>();
            queryWrapper.eq("survey_id",  id);
            //  查询数据库，获取所有符合条件的  Question  对象，并将其保存到  questionList  中。
            List<Question>  questionList  =  questionMapper.selectList(queryWrapper);

            QueryWrapper<Option>  optionQueryWrapper  =  null;
            //遍历  questionList  中的每个  Question  对象，对每个对象执行一次循环。
            for  (Question  q  :  questionList)  {
                //  定义一个新的  QuestionDto  对象
                QuestionDto  questionDto  =  new  QuestionDto();
                //  如果问题类型不为  Constant.QUESTION_TYPE_TIAN，那么还需要获取问题的选项列表。
                if  (!Constant.QUESTION_TYPE_TIAN.equals(q.getQuestionType()))  {
                    String  questionId  =  q.getId();
                    optionQueryWrapper  =  new  QueryWrapper<>();
                    optionQueryWrapper.eq("survey_id",  id);
                    optionQueryWrapper.eq("question_id",  questionId);
                    //  查询所有符合条件的  Option  对象，并将其以列表形式保存
                    List<Option>  optionList  =  optionMapper.selectList(optionQueryWrapper);
                    //  设置  QuestionDto  对象的选项列表
                    questionDto.setOptionList(optionList);
                }
                //  将  Question  对象中的所有属性都复制到  QuestionDto  对象中
                BeanUtils.copyProperties(q,  questionDto);
                //  将  QuestionDto  对象添加到  dtoList  列表中
                dtoList.add(questionDto);
            }
            //  将  dtoList  列表设置到  surveyCreateDto  对象的  QuestionDtoList  属性中
            surveyCreateDto.setQuestionDtoList(dtoList);
            return  surveyCreateDto;
        }  else  {
            return  null;
        }
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

    @Transactional(rollbackFor  =  Exception.class)
    protected    int  deleteQuestionnaireSql(String  id,QueryWrapper<Question>  questionQueryWrapper,QueryWrapper<Option>  optionQueryWrapper)  {
        int  i  =  surveyMapper.deleteById(id);
        int  j  =  questionMapper.delete(questionQueryWrapper);
        int  k  =  optionMapper.delete(optionQueryWrapper);
        return  i  ==  1  &&  j  >=  0  &&  k  >=  0  ?  1  :  0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result reviseQuestionnaire(SurveyCreateDto surveyCreateDto) {
        deleteQuestionnaire(surveyCreateDto.getId());
        return createQuestionnaire1(surveyCreateDto);
    }


    @Override
    public List<Survey> selectOtherUserSurvey(String id) {
        QueryWrapper<Survey> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("creator_id", id);
        return surveyMapper.selectList(queryWrapper);
    }

}
