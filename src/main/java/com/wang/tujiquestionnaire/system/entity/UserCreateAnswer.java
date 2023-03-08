package com.wang.tujiquestionnaire.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户作答问卷信息
 * </p>
 *
 * @author wang
 * @since 2023-03-08
 */
@Getter
@Setter
@TableName("user_create_answer")
@ApiModel(value = "UserCreateAnswer对象", description = "用户作答问卷信息")
public class UserCreateAnswer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户名")
    private String userId;

    @ApiModelProperty("问卷主表id")
    private Long surveyId;

    @ApiModelProperty("答案主表id")
    private Long answerId;

    @ApiModelProperty("创建时间")
    private LocalDateTime createDate;


}
