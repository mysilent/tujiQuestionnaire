package com.wang.tujiquestionnaire.system.entity.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ClassName: UserDetailDto
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/5/2-21:20
 */
@Getter
@Setter
@ToString
@TableName("user_detail")
@ApiModel(value = "UserDetail数据传输对象", description = "")
public class UserDetailDto {


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

        @ApiModelProperty("激励值")
        private Integer gold;


}
