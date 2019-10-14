package io.renren.modules.sys.request;

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
 * 系统服务
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-20 10:12:44
 */
@Data
@Api("系统服务-修改")
public class SysServiceUpdateRequest extends IDRequest {
    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 中文名称
     */
    @ApiModelProperty(value = "中文名称")
    private String cnName;
    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String content;
    /**
     * 授权码
     */
    @ApiModelProperty(value = "授权码")
    private String authzCode;
    /**
     * 参数
     */
    @ApiModelProperty(value = "参数")
    private String perms;
    /**
     * 是否显示
     */
    @ApiModelProperty(value = "是否显示")
    private String isShow;
    /**
     * 启动类型
     */
    @ApiModelProperty(value = "启动类型")
    private String startUpType;
    /**
     * 服务状态
     */
    @ApiModelProperty(value = "服务状态")
    private String state;
    /**
     * 方法和类名
     */
    @ApiModelProperty(value = "方法和类名")
    private String method;

}
