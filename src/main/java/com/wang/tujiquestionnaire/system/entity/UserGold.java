package com.wang.tujiquestionnaire.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author wang
 * @since 2023-05-02
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("user_gold")
@ApiModel(value = "UserGold对象", description = "")
public class UserGold implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @ApiModelProperty("激励值")
    private Integer gold;


}
