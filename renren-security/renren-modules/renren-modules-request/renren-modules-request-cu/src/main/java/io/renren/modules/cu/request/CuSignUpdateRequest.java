package io.renren.modules.cu.request;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.base.IDRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
    import java.io.Serializable;
import java.util.Date;

/**
 * 客户签到
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:41:36
 */
@Data
@Api("客户签到-修改")
public class CuSignUpdateRequest extends IDRequest {
    private static final long serialVersionUID = 1L;

                                            /**
             * 客户ID
             */
                    @ApiModelProperty(value = "客户ID")
    private String customerId;
                                /**
             * 签到日期
             */
                    @ApiModelProperty(value = "签到日期")
    private String customerDate;
                                /**
             * 签到日期年
             */
                    @ApiModelProperty(value = "签到日期年")
    private String customerYear;
                                /**
             * 签到日期月
             */
                    @ApiModelProperty(value = "签到日期月")
    private String customerMonth;
                                /**
             * 签到日期日
             */
                    @ApiModelProperty(value = "签到日期日")
    private String customerDay;
                                                                                                                                                                                                
}
