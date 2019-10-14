package io.renren.modules.device.request;
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
 * 密码线
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
@Data
@Api("密码线-修改")
public class DeviceLineUpdateRequest extends IDRequest {
    private static final long serialVersionUID = 1L;

                                            /**
             * 机柜编码
             */
                    @ApiModelProperty(value = "机柜编码")
    private String lineSn;
                                /**
             * 二维码编号
             */
                    @ApiModelProperty(value = "二维码编号")
    private String qrCode;
                                /**
             * 所属代理商
             */
                    @ApiModelProperty(value = "所属代理商")
    private String agent;
                                /**
             * 所属业务员
             */
                    @ApiModelProperty(value = "所属业务员")
    private String bus;
                                /**
             * 所属商户
             */
                    @ApiModelProperty(value = "所属商户")
    private String pos;
                                /**
             * 是否可以租借
             */
                    @ApiModelProperty(value = "是否可以租借")
    private String lease;
                                /**
             * 设备状态
             */
                    @ApiModelProperty(value = "设备状态")
    private String state;
                                                                                                                                                                                                
}
