package com.wang.tujiquestionnaire.system.entity;

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
 * @since 2023-05-02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_gold")
@ApiModel(value = "UserGold对象", description = "用户激励值表")
public class UserGold implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("关联用户id")
    private String id;

    @ApiModelProperty("激励值")
    private Integer gold;


}
