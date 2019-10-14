package io.renren.modules.cu.request;

import io.renren.common.base.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 客户签到
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:41:36
 */
@Data
@Api("客户签到-签到" )
public class CuSignSignRequest extends BaseRequest {

    @ApiModelProperty(value = "日期" )
    @NotBlank(message = "日期不能为空" )
    private String date;
}
