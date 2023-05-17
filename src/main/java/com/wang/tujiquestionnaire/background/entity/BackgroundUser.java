package com.wang.tujiquestionnaire.background.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author wang
 * @since 2023-04-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("background_user")
@ApiModel(value = "BackgroundUser对象", description = "")
public class BackgroundUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("账户")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("权限等级")
    private Integer permissions;


}
