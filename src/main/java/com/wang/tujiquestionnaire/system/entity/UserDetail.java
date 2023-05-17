package com.wang.tujiquestionnaire.system.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author wang
 * @since 2023-04-18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("user_detail")
@ApiModel(value = "UserDetail对象", description = "用户详情表")
public class UserDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("生日")
    private LocalDate birthday;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("身份证号")
    private byte[] idNumber;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("注册日期")
    private LocalDateTime registerTime;

    @ApiModelProperty("最后登录日期")
    private LocalDateTime lastLoginTime;


}
