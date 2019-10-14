package io.renren.modules.cu.request;
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
 * 客户收益信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 10:49:16
 */
@Data
@Api("客户收益信息-保存")
public class CuProfitSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

                                /**
 * 客户
 */
                @ApiModelProperty(value = "客户")
private String customerId;
                    /**
 * 来源ID
 */
                @ApiModelProperty(value = "来源ID")
private String sourceId;
                    /**
 * 来源类型
 */
                @ApiModelProperty(value = "来源类型")
private String sourceType;
                    /**
 * 收益
 */
                @ApiModelProperty(value = "收益")
private BigDecimal profitNum;
                    /**
 * 货币类型
 */
                @ApiModelProperty(value = "货币类型")
private String profitType;
                    /**
 * 收益状态(1收入 0支出)
 */
                @ApiModelProperty(value = "收益状态(1收入 0支出)")
private String profitStatus;
                                                                                                                                                                                                

}
