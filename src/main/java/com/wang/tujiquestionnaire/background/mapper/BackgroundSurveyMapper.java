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

    @Select("select count(*) from survey_info s join user u on s.creator_id=u.id where u.nickname like concat('%',#{nickname},'%')and s.survey_name like concat('%',#{surveyName},'%')and s.id like concat('%',#{id},'%')")
    Integer selectTotal(String nickname, String surveyName, String id);

    @Select("select s.id,s.survey_name,s.status,u.username,u.nickname,s.start_time from survey_info s join user u on s.creator_id=u.id where u.nickname like concat('%',#{nickname},'%') and s.survey_name like concat('%',#{surveyName},'%')and s.id like concat('%',#{id},'%') limit #{pageNum},#{pageSize}")
    List<SurveyAndUser> findPage(Integer pageNum, Integer pageSize, String nickname, String surveyName, String id);
}
