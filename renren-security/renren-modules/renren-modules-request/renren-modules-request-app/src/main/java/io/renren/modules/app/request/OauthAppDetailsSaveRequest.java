package io.renren.modules.app.request;
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
 * 系统信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-16 10:46:12
 */
@Data
@Api("系统信息-保存")
public class OauthAppDetailsSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

                                /**
 * 本地系统用户ID
 */
                @ApiModelProperty(value = "本地系统用户ID")
private String sysUserId;
                    /**
 * 微信OPENID
 */
                @ApiModelProperty(value = "微信OPENID")
private String openId;
                    /**
 * 系统作用域
 */
                @ApiModelProperty(value = "系统作用域")
private String scopes;
                    /**
 * 授权模式
 */
                @ApiModelProperty(value = "授权模式")
private String grantTypes;
                    /**
 * 重定向地址
 */
                @ApiModelProperty(value = "重定向地址")
private String redirectUrl;
                    /**
 * 是否自动授权
 */
                @ApiModelProperty(value = "是否自动授权")
private String autoApprove;
                    /**
 * 是否刷新
 */
                @ApiModelProperty(value = "是否刷新")
private String refreshToken;
                    /**
 * 随机串
 */
                @ApiModelProperty(value = "随机串")
private String noncestr;
                    /**
 * 签名
 */
                @ApiModelProperty(value = "签名")
private String signature;
                    /**
 * 凭证超时时限
 */
                @ApiModelProperty(value = "凭证超时时限")
private Integer expiresIn;
                    /**
 * 授权时间
 */
                @ApiModelProperty(value = "授权时间")
private Date oauthTime;
                                                                                                                                                                                                

}
