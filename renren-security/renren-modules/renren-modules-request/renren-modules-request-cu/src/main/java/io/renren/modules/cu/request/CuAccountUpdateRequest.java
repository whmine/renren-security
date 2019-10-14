package io.renren.modules.cu.request;
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
 * 客户账目信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 10:49:16
 */
@Data
@Api("客户账目信息-修改")
public class CuAccountUpdateRequest extends IDRequest {
    private static final long serialVersionUID = 1L;

                                            /**
             * 余额
             */
                    @ApiModelProperty(value = "余额")
    private BigDecimal customerMoney;
                                                                                                                                                                                                
}
