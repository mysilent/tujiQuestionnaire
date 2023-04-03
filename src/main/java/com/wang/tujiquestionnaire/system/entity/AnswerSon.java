package com.wang.tujiquestionnaire.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户答案子表
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@Getter
@Setter
@TableName("answer_son_info")
@ApiModel(value = "AnswerSon对象", description = "用户答案子表")
public class AnswerSon implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("答案主表id")
    private String answerId;

    @ApiModelProperty("选项主表id")
    private String optionId;

    @ApiModelProperty("答案内容")
    private String optionContent;

    @ApiModelProperty("图片id")
    private Integer answerPicId;


}
