package com.wang.tujiquestionnaire.background.mapper;

import com.wang.tujiquestionnaire.background.entity.SurveyTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2023-04-26
 */
@Mapper
public interface SurveyTemplateMapper extends BaseMapper<SurveyTemplate> {


    /**
     * 去SurveyTemplate数据库查询state为'1'的数据
     * 或是根据名称与id查询SurveyTemplate数据库state为'1'的数据
     * @param pageSize 分页数
     * @param pageNum   分页大小
     * @param state 状态码
     * @param surveyName 模板名称
     * @param surveyId 模板id
     * @return 带有分页数据的data,以及一共多少条数据total
     */
    @Select("select * from survey_template where state='${state}' and survey_name like concat('%',#{surveyName},'%') and id like concat('%',#{surveyId},'%')limit #{pageNum},#{pageSize}")
    List<SurveyTemplate> templatepage(@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum, @Param("state") String state,@Param("surveyName") String surveyName,@Param("surveyId")String surveyId);

    /**
     * 去SurveyTemplate数据库查询state为某一个值的数据并放入列表
     * @param pageSize 分页大小
     * @param pageNum 分页数
     * @param state state状态码
     * @return 返回一个SurveyTemplate对象的列表
     */
@Select("select * from survey_template where state='${state}' limit #{pageNum},#{pageSize}")
List<SurveyTemplate> templateApplicationpage(@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum, @Param("state") String state);

    /**
     * 去SurveyTemplate数据库查询state为某一个值的数据总条数
     * @param state state状态码
     * @return 返回int
     */
@Select("select count(*) from  survey_template where state='${state}'")
Integer templateTotal(String state);

    /**
     * 根据传来的 state 和 id 去数据库中将问卷模板id为传来的id值相等的state改为传入来的state
     * @param state stete状态码
     * @param id 问卷模板的id
     * @return 返回修改的条数,为1时正确
     */
@Update("update survey_template set state='${state}' where id='${id}'")
    Integer templateYes(String state,String id);

    /**
     * 根据传来的id值删除该问卷模板
     * @param id 问卷模板id
     * @return 返回修改的条数
     */
@Delete("delete from survey_template where id='${id}'")
    Integer templateNo(String id);
}

