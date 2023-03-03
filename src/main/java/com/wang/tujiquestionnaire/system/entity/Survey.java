package com.wang.tujiquestionnaire.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 调查问卷主表
 * </p>
 *
 * @author wang
 * @since 2023-02-28
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@TableName("survey_info")
@ApiModel(value = "Survey对象", description = "调查问卷主表")
public class Survey implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("主题")
    private String surveyName;

    @ApiModelProperty("描述")
    private String surveyDescription;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("0 发布 1 暂存 2已结束 3已失效")
    private String status;

    @ApiModelProperty("排序")
    private Integer surveySort;

    @ApiModelProperty("0 置顶 1不置顶")
    private String topFlag;

    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateDate;

    @ApiModelProperty("创建人员ID")
    private Long creatorId;

    @ApiModelProperty("更新人员ID")
    private Long updatorId;

    @ApiModelProperty("图片id")
    private Integer surveyPicId;


}
