package com.wang.tujiquestionnaire.background.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 
 * </p>
 *
 * @author wang
 * @since 2023-04-26
 */
@Getter
@Setter
@ToString
@TableName("survey_template")
@ApiModel(value = "SurveyTemplate对象", description = "")
public class SurveyTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty("问卷模板名称")
    private String surveyName;

    @ApiModelProperty("问卷模板描述")
    private String surveyDescription;

    @ApiModelProperty("问卷模板内容")
    private String content;

    @ApiModelProperty("创建人id")
    private String creatorId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createDate=LocalDateTime.now();

    @ApiModelProperty("修改日期")
    private LocalDateTime updateDate;

    @ApiModelProperty("状态(1使用，2不使用)")
    private String state;


}
