package io.renren.modules.oauth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.utils.DateUtils;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 授权TOKEN
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 08:49:27
 */
@Data
@TableName("oauth_token")
public class OauthTokenEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户表
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * TOKEN
     */
    private String token;
    /**
     * 用户ID
     */
    private String sysUserId;
    /**
     * 过期时间
     */
    private Date expireTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
