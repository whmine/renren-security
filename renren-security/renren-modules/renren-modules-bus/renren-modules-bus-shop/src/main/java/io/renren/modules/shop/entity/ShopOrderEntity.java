package io.renren.modules.shop.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

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
@TableName("shop_order")
public class ShopOrderEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 订单表ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 用户ID
     */
    private String sysUserId;
    /**
     * 商品ID
     */
    private String goodsId;
    /**
     * 订单编号
     */
    private String orderSn;
    /**
     * 订单价格
     */
    private BigDecimal orderAmount;
    /**
     * 0 待付款 1 已付款
     */
    private Integer orderStatus;
    /**
     * 支付状态0 未付款 1 已付款
     */
    private Integer payStatus;
    /**
     * 支付CODE
     */
    private String payCode;
    /**
     * 下单时间
     */
    private String addTime;

}
