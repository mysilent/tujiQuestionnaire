package com.wang.tujiquestionnaire.system.entity.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * ClassName: userAndUserDetailDto
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/4/25-21:34
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserAndUserDetailDto {
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
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("昵称")
    private String nickname;

    @ApiModelProperty("创建日期")
    private LocalDateTime createDate;

    @ApiModelProperty("更新时间")
    private LocalDateTime updateDate;

    @ApiModelProperty("启用1 封禁0 注销2")
    private Integer state;
}
