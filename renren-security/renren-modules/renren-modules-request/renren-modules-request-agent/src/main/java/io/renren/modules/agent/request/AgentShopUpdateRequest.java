package io.renren.modules.agent.request;
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
 * 代理商
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 09:24:45
 */
@Data
@Api("代理商-修改")
public class AgentShopUpdateRequest extends IDRequest {
    private static final long serialVersionUID = 1L;

                                            /**
             * 代理商编码
             */
                    @ApiModelProperty(value = "代理商编码")
    private String shopSn;
                                /**
             * 代理商名称
             */
                    @ApiModelProperty(value = "代理商名称")
    private String shopName;
                                /**
             * 代理商手机
             */
                    @ApiModelProperty(value = "代理商手机")
    private String shopPhone;
                                /**
             * 代理商模式
             */
                    @ApiModelProperty(value = "代理商模式")
    private String shopType;
                                /**
             * 代理商上级
             */
                    @ApiModelProperty(value = "代理商上级")
    private String shopParent;
                                /**
             * 佣金百分比
             */
                    @ApiModelProperty(value = "佣金百分比")
    private String shopRatio;
                                /**
             * 可打开所属机柜
             */
                    @ApiModelProperty(value = "可打开所属机柜")
    private String shopOpen;
                                /**
             * 客户编码
             */
                    @ApiModelProperty(value = "客户编码")
    private String customerId;
                                                                                                                                                                                                
}
