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
@TableName("survey_gold")
@ApiModel(value = "SurveyGold对象", description = "问卷发布信息表")
public class SurveyGold implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private Integer price;

    private Integer quantity;


}
