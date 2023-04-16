package com.wang.tujiquestionnaire.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>
 * 用户答卷作答历史记录
 * </p>
 *
 * @author wang
 * @since 2023-04-14
 */
@Getter
@Setter
@ToString
@TableName("user_history")
@ApiModel(value = "UserHistory对象", description = "用户答卷作答历史记录")
public class UserHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    @ApiModelProperty("问卷id")
    private String surveyId;
    @ApiModelProperty("问卷名称")
    private String surveyName;
    @ApiModelProperty("用户id")
    private String userId;
    @ApiModelProperty("用户答卷表的标识")
    private String userCreateId;
    @ApiModelProperty("创建时间")
    private Date createDate;
}
