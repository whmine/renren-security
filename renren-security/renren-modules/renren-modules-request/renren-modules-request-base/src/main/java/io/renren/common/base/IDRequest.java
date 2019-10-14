package io.renren.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class IDRequest extends BaseRequest {

    @ApiModelProperty(value = "ID")
    @NotBlank(message = "ID不能为空")
    private String id;

}
