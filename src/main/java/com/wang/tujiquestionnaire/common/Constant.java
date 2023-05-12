package com.wang.tujiquestionnaire.common;

/**
 * ClassName: Constant
 * Package: IntelliJ IDEA
 * Description: 问卷内各类型常量定义
 *
 * @Author: wang
 * Create:2023/3/6-11:37
 */
public class Constant {
    /**
     * question_type 单选
     */
    public static final String QUESTION_TYPE_DAN = "1";
    /**
     * question_type 多选
     */
    public static final String QUESTION_TYPE_DUO = "2";
    /**
     * question_type 填空
     */
    public static final String QUESTION_TYPE_TIAN = "3";


    /**
     * 用户状态 state  启用1 封禁0 注销2
     */
    public static final int STATE_ENABLE = 1;
    public static final int STATE_BAN = 0;
    public static final int STATE_LOGOUT = 2;


    /**
     * 登录验证配置不拦截页面网址
     */
    public static final String NO_URL1 = "/system/user/login";
    public static final String NO_URL2 = "/system/user/enroll";
    public static final String NO_URL3 = "/system/survey/selectQuestionnaire";
    public static final String NO_URL4 = "/system/survey/selectOtherUserSurvey";

    /**
    *模板状态 申请-0 通过-1
     */
    public  static  final String TEMPLATE_YES="1";
    public  static  final String TEMPLATE_NO="0";

    /**
     * 问卷状态 暂存-1 发布-0 结束-2 失效-3
     */
    public static final String SURVEY_STATUS_PUBLISH="0";
    public static final String SURVEY_STATUS_STAGING="1";
    public static final String SURVEY_STATUS_END="2";
    public static final String SURVEY_STATUS_LAPSE="3";
}
