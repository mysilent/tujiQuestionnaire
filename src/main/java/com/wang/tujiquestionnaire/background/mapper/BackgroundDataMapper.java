package com.wang.tujiquestionnaire.background.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * ClassName: BackgroundDataMapper
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/5/1-22:36
 */
@Mapper
public interface BackgroundDataMapper {

    /**
     * 去数据库里面查询前七天的问卷创建数量各多少
     * @return 一个列表
     */
    @Select("SELECT DATE_FORMAT(date_list.date, '%Y-%m-%d') AS date, COUNT(survey_info.create_date) AS count\n" +
            "FROM (\n" +
            "  SELECT CURDATE() - INTERVAL 6 DAY AS date\n" +
            "  UNION SELECT CURDATE() - INTERVAL 5 DAY\n" +
            "  UNION SELECT CURDATE() - INTERVAL 4 DAY\n" +
            "  UNION SELECT CURDATE() - INTERVAL 3 DAY\n" +
            "  UNION SELECT CURDATE() - INTERVAL 2 DAY\n" +
            "  UNION SELECT CURDATE() - INTERVAL 1 DAY\n" +
            "  UNION SELECT CURDATE()\n" +
            ") AS date_list\n" +
            "LEFT JOIN survey_info\n" +
            "ON DATE(survey_info.create_date) = date_list.date\n" +
            "WHERE date_list.date BETWEEN CURDATE() - INTERVAL 6 DAY AND CURDATE()\n" +
            "GROUP BY date_list.date;")
    public List<Map<String, Object>> surveyCreateDate();

    /**
     * 去数据库里面查询前七天的问卷回答数量各多少
     * @return 一个列表
     */
    @Select("SELECT DATE_FORMAT(date_list.date, '%Y-%m-%d') AS date, COUNT(user_history.create_date) AS count\n" +
            "FROM (\n" +
            "  SELECT CURDATE() - INTERVAL 6 DAY AS date\n" +
            "  UNION SELECT CURDATE() - INTERVAL 5 DAY\n" +
            "  UNION SELECT CURDATE() - INTERVAL 4 DAY\n" +
            "  UNION SELECT CURDATE() - INTERVAL 3 DAY\n" +
            "  UNION SELECT CURDATE() - INTERVAL 2 DAY\n" +
            "  UNION SELECT CURDATE() - INTERVAL 1 DAY\n" +
            "  UNION SELECT CURDATE()\n" +
            ") AS date_list\n" +
            "LEFT JOIN user_history\n" +
            "ON DATE(user_history.create_date) = date_list.date\n" +
            "WHERE date_list.date BETWEEN CURDATE() - INTERVAL 6 DAY AND CURDATE()\n" +
            "GROUP BY date_list.date;")
    List<Map<String, Object>> answerCreateDate();
}
