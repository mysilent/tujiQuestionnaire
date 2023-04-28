package com.wang.tujiquestionnaire.background.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

/**
 * ClassName: SurveyAndUser
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/4/26-15:59
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SurveyAndUser {

@ApiModelProperty("问卷id")
    private String id;

    @ApiModelProperty("主题")
    private String surveyName;

    @ApiModelProperty("0 发布 1 暂存 2已结束 3已失效")
    private String status;
    @ApiModelProperty("创建时间")
    private String startTime;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;


}
