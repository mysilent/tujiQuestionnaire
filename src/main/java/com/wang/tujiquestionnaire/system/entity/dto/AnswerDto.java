package com.wang.tujiquestionnaire.system.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wang.tujiquestionnaire.system.entity.AnswerSon;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: AnswerDto
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/3/8-9:17
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private Long id;

    @ApiModelProperty("成员id")
    private String userId;

    @ApiModelProperty("问卷主表ID")
    private Long surveyId;

    @ApiModelProperty("问题主表ID")
    private Long questionId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty("答案子表列表")
    private List<AnswerSon> answerSonList;
}
