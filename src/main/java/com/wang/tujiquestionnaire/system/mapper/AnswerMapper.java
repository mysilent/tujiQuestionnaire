package com.wang.tujiquestionnaire.system.mapper;

import com.wang.tujiquestionnaire.background.entity.SurveyAndUser;
import com.wang.tujiquestionnaire.system.entity.dto.AnswerDataDto;
import org.apache.ibatis.annotations.Param;

import com.wang.tujiquestionnaire.system.entity.Answer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户答案表 Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Mapper
public interface AnswerMapper extends BaseMapper<Answer> {

    /**
     * 将传来的作答答案批量存入数据库
     *
     * @param answerList 作答答案列表
     */
    void insertAnswerList(List<Answer> answerList);

    /**
     * 根据问卷id去查询该问卷的所有问题
     *
     * @param surveyId 问卷id
     * @return 返回一个AnswerDataDto对象的列表
     */
    List<AnswerDataDto> selectDataBySurveyId(@Param("surveyId") String surveyId);

    /**
     * 去查询该问卷，题型为单选题的数据
     *
     * @param question_type 问题类型
     * @param survey_id 问卷id
     * @return 返回一个map的列表
     */
    @Select("SELECT o.question_id,q.question_description,o.option_name,o.option_sort ,  COUNT(a.option_content) as count \n" +
            "FROM option_info o\n" +
            "LEFT JOIN answer_info a ON o.question_id = a.question_id and o.option_sort=a.option_content JOIN question_info q ON o.question_id=q.id\n" +
            "WHERE o.survey_id='${survey_id}' AND q.question_type=#{question_type}\n" +
            "GROUP BY o.option_sort,o.question_id ")
    List<Map<String, Object>> selectOneDataBySurveyId(String survey_id,String question_type);

    /**
     * 去查询该问卷，题型为多选题的数据
     *
     * @param survey_id 问卷id
     * @param question_type 问题类型
     * @return 返回一个map的列表
     */
    @Select("SELECT q.id as question_id,GROUP_CONCAT(TRIM(BOTH '[]' FROM TRIM(BOTH ',' FROM SUBSTRING_INDEX(SUBSTRING_INDEX(a.option_content, ']', 1), '[', -1))) SEPARATOR ',') as count \n" +
            "FROM answer_info a\n" +
            "JOIN question_info q on a.question_id=q.id\n" +
            "WHERE a.survey_id='${survey_id}' and q.question_type=#{question_type}\n" +
            "GROUP BY q.id")
    List<Map<String, String>> selectAnyDataBySurvey(String survey_id,String question_type);

    /**
     * 去查询该问卷，题型为填空题的数据
     *
     * @param survey_id 问卷id
     * @param question_type 问题类型
     * @return 返回一个map的列表
     */
    @Select("select \tq.id, q.question_description, q.question_sort,a.option_content FROM question_info q JOIN answer_info a ON q.id=a.question_id\n" +
            "WHERE q.question_type=#{question_type} AND q.survey_id='${survey_id}'")
    List<Map<String, Object>> selectInputDataBySurveyId(String survey_id,String question_type);

    /**
     * 查询问题对应的用户回答的答案
     *
     * @param surveyId   问卷id
     * @param questionId 问题id
     * @param content    搜索内容
     * @param pageNum    分页数
     * @param pageSize   分页大小
     * @return 列表
     */
    @Select("select * from answer_info where survey_id=#{surveyId} AND question_id=#{questionId} AND option_content like concat('%',#{content},'%') limit #{pageNum},#{pageSize}")
    List<Answer> inputAnswer(@Param("surveyId") String surveyId, @Param("questionId") String questionId, @Param("content") String content, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    /**
     * 根据传来的信息去数据库进行查询数据的总量
     *
     * @param surveyId   问卷id
     * @param questionId 问题id
     * @param content    搜索内容
     * @return 查询到的数量
     */
    @Select("select count(*) from answer_info where survey_id=#{surveyId} AND question_id=#{questionId} AND option_content like concat('%',#{content},'%')")
    Integer inputAnswerTotal(@Param("surveyId") String surveyId, @Param("questionId") String questionId, @Param("content") String content);
}
