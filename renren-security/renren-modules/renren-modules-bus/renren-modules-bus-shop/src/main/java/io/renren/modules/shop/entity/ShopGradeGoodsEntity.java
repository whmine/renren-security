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
 * 积分商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
@Data
@TableName("shop_grade_goods")
public class ShopGradeGoodsEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 分类ID
     */
    private String catId;
    /**
     * 商品编号
     */
    private String goodsSn;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 点击数
     */
    private Integer clickCount;
    /**
     * 本店价
     */
    private BigDecimal shopPrice;
    /**
     * 商品详细描述
     */
    private String goodsContent;
    /**
     * 商品原始图
     */
    private String originalImg;
    /**
     * 商品视频
     */
    private String originalVideo;
    /**
     * 是否是否收费
     */
    private Integer isCharge;
    /**
     * 是否上架
     */
    private Integer isOnSale;
    /**
     * 商品上架时间
     */
    private String onTime;
    /**
     * 商品销量
     */
    private Integer salesSum;
    /**
     * 被收藏数量
     */
    private Integer collectNum;

}
