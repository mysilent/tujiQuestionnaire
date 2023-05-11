package com.wang.tujiquestionnaire.background.mapper;

import com.wang.tujiquestionnaire.background.entity.BackgroundUser;
import com.wang.tujiquestionnaire.background.entity.SurveyAndUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: BackgroundSurveyMapper
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/4/26-15:33
 */
@Mapper
public interface BackgroundSurveyMapper {

    /**
     * @param nickname
     * @param surveyName
     * @param id
     * @return
     */
    @Select("select count(*) from survey_info s join user u on s.creator_id=u.id where u.nickname like concat('%',#{nickname},'%')and s.survey_name like concat('%',#{surveyName},'%')and s.id like concat('%',#{id},'%')")
    Integer selectTotal(String nickname, String surveyName, String id);

    /**
     * 根据传来的信息进行分页查询
     * @param pageNum 分页数
     * @param pageSize 分页大小
     * @param nickname 昵称
     * @param surveyName 问卷名
     * @param id 创建问卷用户id
     * @return 列表
     */
    @Select("select s.id,s.survey_name,s.status,u.username,u.nickname,s.start_time from survey_info s join user u on s.creator_id=u.id where u.nickname like concat('%',#{nickname},'%') and s.survey_name like concat('%',#{surveyName},'%')and s.id like concat('%',#{id},'%') limit #{pageNum},#{pageSize}")
    List<SurveyAndUser> findPage(Integer pageNum, Integer pageSize, String nickname, String surveyName, String id);
}
