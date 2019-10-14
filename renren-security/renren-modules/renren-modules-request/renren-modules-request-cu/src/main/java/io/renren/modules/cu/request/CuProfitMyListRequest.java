package io.renren.modules.cu.request;

import io.renren.common.base.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 积分商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
@Data
@Api("积分商品-当前月份积分列表")
public class CuProfitMyListRequest extends BaseRequest {
    @ApiModelProperty(value = "时间")
    @NotBlank(message = "时间不能为空")
    String date;
}
