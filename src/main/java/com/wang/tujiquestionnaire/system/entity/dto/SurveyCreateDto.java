package com.wang.tujiquestionnaire.system.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.wang.tujiquestionnaire.system.entity.Option;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: SurveyCreateDto
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/3/2-11:16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SurveyCreateDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
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
    private Integer creatorId;

    @ApiModelProperty("更新人员ID")
    private Integer updatorId;

    @ApiModelProperty("图片id")
    private Integer surveyPicId;
    @ApiModelProperty("调查问卷问题主表列表")
    private List<QuestionDto> questionDtoList;
}
