package com.wang.tujiquestionnaire.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 调查问卷问题主表
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Getter
@Setter
@TableName("question_info")
@ApiModel(value = "Question对象", description = "调查问卷问题主表")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("关联调查问卷主表ID")
    private String surveyId;

    @ApiModelProperty("1 单选 2多选 3填空")
    private String questionType;

    @ApiModelProperty("问题描述")
    private String questionDescription;

    @ApiModelProperty("问题排序")
    private Integer questionSort;

    @ApiModelProperty(" 0 必填 1非必填")
    private String requiredFlag;

    @ApiModelProperty("图片id")
    private Integer questionPicId;



}
