package io.renren.modules.shop.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.base.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
@Data
@Api("商品-分页查询")
public class ShopGoodsPageRequest extends PageRequest {

    @ApiModelProperty(value = "类型")
    @NotBlank(message = "类型不能为空")
    private String type;
}
