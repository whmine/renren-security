package io.renren.modules.shop.request;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.base.IDRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
        import java.math.BigDecimal;
    import java.io.Serializable;
import java.util.Date;

/**
 * 支付订单
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:44
 */
@Data
@Api("支付订单-修改")
public class ShopPayUpdateRequest extends IDRequest {
    private static final long serialVersionUID = 1L;

                                            /**
             * 用户ID
             */
                    @ApiModelProperty(value = "用户ID")
    private String sysUserId;
                                /**
             * 订单id
             */
                    @ApiModelProperty(value = "订单id")
    private String goodsId;
                                /**
             * 支付订单号
             */
                    @ApiModelProperty(value = "支付订单号")
    private String payCode;
                                /**
             * 订单价格
             */
                    @ApiModelProperty(value = "订单价格")
    private BigDecimal price;
                                /**
             * 支付状态
             */
                    @ApiModelProperty(value = "支付状态")
    private String payStatus;
                                                                                                                                                                                                
}
