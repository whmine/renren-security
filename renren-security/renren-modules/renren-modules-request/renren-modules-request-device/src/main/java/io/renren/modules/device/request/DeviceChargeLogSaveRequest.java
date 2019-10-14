package io.renren.modules.device.request;
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
 * 充电宝租借
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
@Data
@Api("充电宝租借-保存")
public class DeviceChargeLogSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

                                /**
 * 充电宝编码
 */
                @ApiModelProperty(value = "充电宝编码")
private String chargeId;
                    /**
 * 开始时间
 */
                @ApiModelProperty(value = "开始时间")
private String startTime;
                    /**
 * 结束时间
 */
                @ApiModelProperty(value = "结束时间")
private String endTime;
                                                                                                                                                                                                

}
