package com.wang.tujiquestionnaire.background.service;

import java.util.List;
import java.util.Map;

/**
 * ClassName: IBackgroundDataService
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/5/1-22:35
 */
public interface IBackgroundDataService {
    /**
     * 将查询到的前七天问卷创建总数的数据封装起来返回
     * @return 返回map对象
     */
    Map<String, String> surveyCreateDate();

    /**
     * 将查询到的前七天问卷作答总数的数据封装起来返回
     * @return 返回map对象
     */
    Map<String, String> answerCreateDate();
}
