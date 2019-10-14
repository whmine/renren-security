package io.renren.common.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PageRequest extends BaseRequest {

    @ApiModelProperty(value = "page")
    @NotNull(message = "page不能为空")
    private int page;

    @ApiModelProperty(value = "limit")
    @NotNull(message = "limit不能为空")
    private int limit;
}
