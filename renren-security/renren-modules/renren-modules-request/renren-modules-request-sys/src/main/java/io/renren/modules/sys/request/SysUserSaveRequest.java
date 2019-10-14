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
 * 系统用户表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Data
@Api("系统用户表-保存")
public class SysUserSaveRequest extends BaseRequest {

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
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String sysPasswd;
    /**
     * 是否实名认证
     */
    @ApiModelProperty(value = "是否实名认证")
    private String isAuth;
    /**
     * 是否系统管理员
     */
    @ApiModelProperty(value = "是否系统管理员")
    private String isSys;
    /**
     * 是否超期
     */
    @ApiModelProperty(value = "是否超期")
    private String isOver;
    /**
     * 超期日期
     */
    @ApiModelProperty(value = "超期日期")
    private Date overTime;
    /**
     * 是否激活
     */
    @ApiModelProperty(value = "是否激活")
    private String isAct;
    /**
     * 激活日期
     */
    @ApiModelProperty(value = "激活日期")
    private Date actTime;
    /**
     * 是否锁定
     */
    @ApiModelProperty(value = "是否锁定")
    private String isLocked;
    /**
     * 锁定日期
     */
    @ApiModelProperty(value = "锁定日期")
    private Date lockedTime;
    /**
     * 是否过期凭证
     */
    @ApiModelProperty(value = "是否过期凭证")
    private String isExpired;
    /**
     * 过期凭证时间
     */
    @ApiModelProperty(value = "过期凭证时间")
    private Date expiredTime;
    /**
     * 所属员工
     */
    @ApiModelProperty(value = "所属员工")
    private String staffId;
    /**
     * 服务区
     */
    @ApiModelProperty(value = "服务区")
    private String areaId;


}
