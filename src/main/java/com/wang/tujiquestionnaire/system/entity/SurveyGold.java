package com.wang.tujiquestionnaire.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
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
@TableName("survey_gold")
@ApiModel(value = "SurveyGold对象", description = "")
public class SurveyGold implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private Integer price;

    private Integer quantity;


}
