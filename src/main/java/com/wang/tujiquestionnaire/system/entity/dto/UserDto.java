package com.wang.tujiquestionnaire.system.entity.dto;

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
    private Long id;
    private String username;
    private String token;
    private String nickname;
}
