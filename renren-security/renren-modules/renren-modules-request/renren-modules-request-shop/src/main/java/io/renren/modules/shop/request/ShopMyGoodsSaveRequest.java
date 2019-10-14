package io.renren.modules.shop.request;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.renren.common.base.BaseRequest;
    import java.io.Serializable;
import java.util.Date;

/**
 * 我的商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
@Data
@Api("我的商品-保存")
public class ShopMyGoodsSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

                                /**
 * 商品ID
 */
                @ApiModelProperty(value = "商品ID")
private String goodsId;
                    /**
 * 用户ID
 */
                @ApiModelProperty(value = "用户ID")
private String sysUserId;
                                                                                                                                                                                                

}
