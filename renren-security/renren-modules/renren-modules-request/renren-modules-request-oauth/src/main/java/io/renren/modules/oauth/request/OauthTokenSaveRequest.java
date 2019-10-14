package io.renren.modules.oauth.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.base.BaseRequest;
import lombok.Data;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 授权TOKEN
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 09:23:04
 */
@Data
@Api("授权TOKEN-保存")
public class OauthTokenSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    /**
     * TOKEN
     */
    @ApiModelProperty(value = "TOKEN")
    private String token;
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String sysUserId;
    /**
     * 过期时间
     */
    @ApiModelProperty(value = "过期时间")
    private Date expireTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


}
