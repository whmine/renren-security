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
 * 客户基础信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 10:49:16
 */
@Data
@Api("客户基础信息-修改")
public class CuInfoUpdateRequest extends IDRequest {
    private static final long serialVersionUID = 1L;

                                            /**
             * 昵称
             */
                    @ApiModelProperty(value = "昵称")
    private String customerName;
                                /**
             * 头像
             */
                    @ApiModelProperty(value = "头像")
    private String customerHead;
                                /**
             * 手机号
             */
                    @ApiModelProperty(value = "手机号")
    private String customerPhone;
                                /**
             * 身份证
             */
                    @ApiModelProperty(value = "身份证")
    private String customerCard;
                                /**
             * 真实名称
             */
                    @ApiModelProperty(value = "真实名称")
    private String customerCardName;
                                /**
             * 首字母
             */
                    @ApiModelProperty(value = "首字母")
    private String customerAcronym;
                                                                                                                                                                                                
}
