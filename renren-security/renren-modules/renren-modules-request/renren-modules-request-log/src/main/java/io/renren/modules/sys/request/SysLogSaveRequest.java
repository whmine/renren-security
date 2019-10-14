package io.renren.modules.sys.request;

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
 * 系统日志
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:04:07
 */
@Data
@Api("系统日志-保存")
public class SysLogSaveRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 级别
     */
    @ApiModelProperty(value = "级别")
    private String grade;
    /**
     * 异常编码
     */
    @ApiModelProperty(value = "异常编码")
    private String sysExceptionId;
    /**
     * IP
     */
    @ApiModelProperty(value = "IP")
    private String ip;
    /**
     * 日志内容
     */
    @ApiModelProperty(value = "日志内容")
    private String text;
    /**
     * 服务ID
     */
    @ApiModelProperty(value = "服务ID")
    private String serviceId;
    /**
     * 操作类型
     */
    @ApiModelProperty(value = "操作类型")
    private String operation;
    /**
     * 方法和类名
     */
    @ApiModelProperty(value = "方法和类名")
    private String method;


}
