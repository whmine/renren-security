package io.renren.modules.shop.request;

import io.renren.common.base.IDRequest;
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
@Api("积分商品-兑换")
public class ShopGradeGoodsChangeRequest extends IDRequest {

    @ApiModelProperty(value = "手机")
    @NotBlank(message = "手机不能为空")
    private String phone;

    @ApiModelProperty(value = "手机")
    @NotBlank(message = "手机不能为空")
    private String address;


}
