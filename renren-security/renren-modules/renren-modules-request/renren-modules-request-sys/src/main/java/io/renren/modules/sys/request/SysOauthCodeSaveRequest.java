package io.renren.modules.sys.request;
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
 * 授权CODE
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Data
@Api("授权CODE-保存")
public class SysOauthCodeSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

                                /**
 * OPEN_ID
 */
                @ApiModelProperty(value = "OPEN_ID")
private String openId;
                    /**
 * 用户ID
 */
                @ApiModelProperty(value = "用户ID")
private String sysUserId;
                                                                                                                                                                                                

}
