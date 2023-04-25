package com.wang.tujiquestionnaire.system.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * ClassName: ChangePasswordDto
 * Package: IntelliJ IDEA
 * Description:
 *
 * @Author: wang
 * Create:2023/4/18-20:42
 */
@Getter
@Setter
@ToString
public class ChangePasswordDto {

    private String newPassword;
    private  String oldPassword;
    private String username;
}
