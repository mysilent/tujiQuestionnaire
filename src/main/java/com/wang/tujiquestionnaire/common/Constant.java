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
     * state  启用1 封禁0 注销2
     */
    public static final String STATE_ENABLE = "1";
    public static final String STATE_BAN = "0";
    public static final String STATE_LOGOUT = "2";


    /**
     * 登录验证配置不拦截页面网址
     */
    public static final String NO_URL1 = "/system/user/login";
    public static final String NO_URL2 = "/system/user/enroll";
    public static final String NO_URL3 = "/system/survey/selectQuestionnaire";
    public static final String NO_URL4 = "/system/survey/selectOtherUserSurvey";
}
