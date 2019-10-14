package io.renren.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import io.renren.common.utils.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:04:07
 */
@Data
@TableName("sys_log")
public class SysLogEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "ID", type = IdType.UUID)
    private String id;
    /**
     * 级别
     */
    private String grade;
    /**
     * 异常编码
     */
    private String sysExceptionId;
    /**
     * IP
     */
    private String ip;
    /**
     * 日志内容
     */
    private String text;
    /**
     * 服务ID
     */
    private String serviceId;
    /**
     * 操作类型
     */
    private String operation;
    /**
     * 方法和类名
     */
    private String method;

}
