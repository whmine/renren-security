package io.renren.modules.shop.request;

import io.renren.common.base.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 我的商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
@Data
@Api("我的商品-我的列表" )
public class ShopMyGoodsListRequest extends BaseRequest {

    @ApiModelProperty(value = "类型" )
    @NotBlank(message = "类型不能为空" )
    private String type;
}
