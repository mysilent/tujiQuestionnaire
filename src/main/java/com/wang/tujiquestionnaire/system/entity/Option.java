package com.wang.tujiquestionnaire.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 调查问卷问题选项主表
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Getter
@Setter
@TableName("option_info")
@ApiModel(value = "Option对象", description = "调查问卷问题选项主表")
public class Option implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("调查问卷ID")
    private Long surveyId;

    @ApiModelProperty("问题ID")
    private Long questionId;

    @ApiModelProperty("选项名称")
    private String optionName;

    @ApiModelProperty("选项排序")
    private Integer optionSort;

    @ApiModelProperty("图片id")
    private Integer optionPicId;


}
