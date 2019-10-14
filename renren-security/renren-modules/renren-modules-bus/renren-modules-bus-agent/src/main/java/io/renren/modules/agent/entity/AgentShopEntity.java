package io.renren.modules.agent.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

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
@TableName("agent_shop")
public class AgentShopEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 代理商编码
     */
    private String shopSn;
    /**
     * 代理商名称
     */
    private String shopName;
    /**
     * 代理商手机
     */
    private String shopPhone;
    /**
     * 代理商模式
     */
    private String shopType;
    /**
     * 代理商上级
     */
    private String shopParent;
    /**
     * 佣金百分比
     */
    private String shopRatio;
    /**
     * 可打开所属机柜
     */
    private String shopOpen;
    /**
     * 客户编码
     */
    private String customerId;

}
