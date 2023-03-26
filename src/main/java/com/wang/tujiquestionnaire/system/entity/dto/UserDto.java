package com.wang.tujiquestionnaire.system.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ClassName: UserDto
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/3/14-14:42
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    @ApiModelProperty("用户id")
    private String id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("token")
    private String token;
    @ApiModelProperty("昵称")
    private String nickname;
}
