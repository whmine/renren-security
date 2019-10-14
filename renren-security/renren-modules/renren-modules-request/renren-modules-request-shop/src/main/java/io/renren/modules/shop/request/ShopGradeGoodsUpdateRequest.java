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
 * 积分商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
@Data
@Api("积分商品-修改")
public class ShopGradeGoodsUpdateRequest extends IDRequest {
    private static final long serialVersionUID = 1L;

                                            /**
             * 分类ID
             */
                    @ApiModelProperty(value = "分类ID")
    private String catId;
                                /**
             * 商品编号
             */
                    @ApiModelProperty(value = "商品编号")
    private String goodsSn;
                                /**
             * 商品名称
             */
                    @ApiModelProperty(value = "商品名称")
    private String goodsName;
                                /**
             * 点击数
             */
                    @ApiModelProperty(value = "点击数")
    private Integer clickCount;
                                /**
             * 本店价
             */
                    @ApiModelProperty(value = "本店价")
    private BigDecimal shopPrice;
                                /**
             * 商品详细描述
             */
                    @ApiModelProperty(value = "商品详细描述")
    private String goodsContent;
                                /**
             * 商品原始图
             */
                    @ApiModelProperty(value = "商品原始图")
    private String originalImg;
                                /**
             * 商品视频
             */
                    @ApiModelProperty(value = "商品视频")
    private String originalVideo;
                                /**
             * 是否是否收费
             */
                    @ApiModelProperty(value = "是否是否收费")
    private Integer isCharge;
                                /**
             * 是否上架
             */
                    @ApiModelProperty(value = "是否上架")
    private Integer isOnSale;
                                /**
             * 商品上架时间
             */
                    @ApiModelProperty(value = "商品上架时间")
    private String onTime;
                                /**
             * 商品销量
             */
                    @ApiModelProperty(value = "商品销量")
    private Integer salesSum;
                                /**
             * 被收藏数量
             */
                    @ApiModelProperty(value = "被收藏数量")
    private Integer collectNum;
                                                                                                                                                                                                
}
