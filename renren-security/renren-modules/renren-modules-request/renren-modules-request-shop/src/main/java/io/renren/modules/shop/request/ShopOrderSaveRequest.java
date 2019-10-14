package io.renren.modules.shop.request;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.renren.common.base.BaseRequest;
        import java.math.BigDecimal;
    import java.io.Serializable;
import java.util.Date;

/**
 * 订单
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
@Data
@Api("订单-保存")
public class ShopOrderSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

                                /**
 * 用户ID
 */
                @ApiModelProperty(value = "用户ID")
private String sysUserId;
                    /**
 * 商品ID
 */
                @ApiModelProperty(value = "商品ID")
private String goodsId;
                    /**
 * 订单编号
 */
                @ApiModelProperty(value = "订单编号")
private String orderSn;
                    /**
 * 订单价格
 */
                @ApiModelProperty(value = "订单价格")
private BigDecimal orderAmount;
                    /**
 * 0 待付款 1 已付款
 */
                @ApiModelProperty(value = "0 待付款 1 已付款")
private Integer orderStatus;
                    /**
 * 支付状态0 未付款 1 已付款
 */
                @ApiModelProperty(value = "支付状态0 未付款 1 已付款")
private Integer payStatus;
                    /**
 * 支付CODE
 */
                @ApiModelProperty(value = "支付CODE")
private String payCode;
                    /**
 * 下单时间
 */
                @ApiModelProperty(value = "下单时间")
private String addTime;
                                                                                                                                                                                                

}
