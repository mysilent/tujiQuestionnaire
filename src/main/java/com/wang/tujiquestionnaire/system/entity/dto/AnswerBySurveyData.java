package com.wang.tujiquestionnaire.system.entity.dto;

import com.wang.tujiquestionnaire.system.entity.Option;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * ClassName: AnswerBySurveyData
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/5/10-14:49
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@ApiOperation("每个问卷的各问题选项的数据传输对象")
public class AnswerBySurveyData {
    private String surveyName;
     private String surveyDescription;

     private List<Map<String, Object>>oneOptionData;
     private Map<String,Object> anyOptionData;
     private List<Map<String,Object>> inputOptionData;
    /**
     * 问题题列表
     */
     private List<Option> optionList;
    /**
     * 每个问题有多少人回答
     */
    private List<AnswerDataDto> answerDataDtoList;
}
