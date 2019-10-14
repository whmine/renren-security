package io.renren.modules.agent.request;
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
 * 代理商业务员
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 09:24:45
 */
@Data
@Api("代理商业务员-保存")
public class AgentShopBusSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

                                /**
 * 业务员编码
 */
                @ApiModelProperty(value = "业务员编码")
private String busSn;
                    /**
 * 业务员名称
 */
                @ApiModelProperty(value = "业务员名称")
private String busName;
                    /**
 * 业务员手机
 */
                @ApiModelProperty(value = "业务员手机")
private String busPhone;
                    /**
 * 所属代理商
 */
                @ApiModelProperty(value = "所属代理商")
private String busParent;
                    /**
 * 佣金百分比
 */
                @ApiModelProperty(value = "佣金百分比")
private String busRatio;
                    /**
 * 可打开所属机柜
 */
                @ApiModelProperty(value = "可打开所属机柜")
private String busOpen;
                    /**
 * 客户编码
 */
                @ApiModelProperty(value = "客户编码")
private String customerId;
                                                                                                                                                                                                

}
