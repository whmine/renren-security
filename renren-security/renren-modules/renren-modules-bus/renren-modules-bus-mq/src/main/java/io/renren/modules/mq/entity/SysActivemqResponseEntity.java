package io.renren.modules.mq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 消息结果表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-05-28 15:39:34
 */
@Data
@TableName("sys_activemq_response")
public class SysActivemqResponseEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 系统用户
     */
    private BigDecimal sysUserId;
    /**
     * 消息类型
     */
    private String activemqType;
    /**
     * 消息内容
     */
    private String activemqContext;
    /**
     * 所属系统
     */
    private String sysSystem;

}
