package io.renren.modules.shop.request;

import io.renren.common.base.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
/**
 * 商品-购买初始化
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
@Data
@Api("商品-购买初始化")
public class ShopGoodsConfigInfoRequest extends BaseRequest {
    @ApiModelProperty(value = "url")
    @NotBlank(message = "url不能为空")
    private String url;
}
