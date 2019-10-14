package io.renren.modules.app.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-20 11:42:31
 */
@Data
@TableName("oauth_app_details")
public class OauthAppDetailsEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 本地系统用户ID
     */
    private String sysUserId;
    /**
     * 微信OPENID
     */
    private String openId;
    /**
     * 系统作用域
     */
    private String scopes;
    /**
     * 授权模式
     */
    private String grantTypes;
    /**
     * 重定向地址
     */
    private String redirectUrl;
    /**
     * 是否自动授权
     */
    private String autoApprove;
    /**
     * 是否刷新
     */
    private String refreshToken;
    /**
     * 随机串
     */
    private String noncestr;
    /**
     * 签名
     */
    private String signature;
    /**
     * 凭证超时时限
     */
    private Integer expiresIn;
    /**
     * 授权时间
     */
    private Date oauthTime;

}
