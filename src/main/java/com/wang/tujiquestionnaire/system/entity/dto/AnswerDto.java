package com.wang.tujiquestionnaire.system.entity.dto;

import lombok.*;

/**
 * ClassName: AnswerDto
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/4/13-20:06
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private String userId;
    private String surveyName;
   private String surveyId;
   private String[] questionList;
   private String[] answers;
}
