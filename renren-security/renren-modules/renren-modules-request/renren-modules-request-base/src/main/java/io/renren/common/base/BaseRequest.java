package io.renren.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseRequest implements Serializable {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", hidden = true)
    private String userId;
}
