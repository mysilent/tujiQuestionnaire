package com.wang.tujiquestionnaire.system.entity.dto;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: SurveyCreateDto
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author:  wang
 * Create:2023/3/2-11:16
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SurveyCreateDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
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
    private String creatorId;

    @ApiModelProperty("更新人员ID")
    private String updatorId;

    @ApiModelProperty("图片id")
    private Integer surveyPicId;
    @ApiModelProperty("调查问卷问题主表列表")
    private List<QuestionDto> questionDtoList;

    @Override
    public String toString() {
        return "SurveyCreateDto{" +
                "id='" + id + '\'' +
                ", surveyName='" + surveyName + '\'' +
                ", surveyDescription='" + surveyDescription + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status='" + status + '\'' +
                ", surveySort=" + surveySort +
                ", topFlag='" + topFlag + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", creatorId='" + creatorId + '\'' +
                ", updatorId='" + updatorId + '\'' +
                ", surveyPicId=" + surveyPicId +
                ", questionDtoList=" + questionDtoList +
                '}';
    }
}
