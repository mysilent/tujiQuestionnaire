package com.wang.tujiquestionnaire.system.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wang.tujiquestionnaire.system.entity.Option;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: OptionDto
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/3/3-14:55
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionDto implements Serializable {
    private static final long serialVersionUID = 1L;

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
    @ApiModelProperty("调查问卷问题选项主表列表")
    private List<Option> optionList;

}
