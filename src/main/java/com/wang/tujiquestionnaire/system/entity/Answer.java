package com.wang.tujiquestionnaire.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 用户答案表
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@TableName("answer_info")
@ApiModel(value = "Answer对象", description = "用户答案表")
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @ApiModelProperty("成员id")
    private String userId;
    @ApiModelProperty("问卷主表ID")
    private String surveyId;
    @ApiModelProperty("问题主表ID")
    private String questionId;
    @ApiModelProperty("答案内容")
    private String optionContent;
    @ApiModelProperty("创建时间")
    @Builder.Default
    private LocalDateTime createDate=LocalDateTime.now();


}
