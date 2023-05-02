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
    Map<String, String> surveyCreateDate();
    Map<String, String> answerCreateDate();
}
