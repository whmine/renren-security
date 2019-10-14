package io.renren.modules.sys.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.renren.common.base.BaseRequest;
import lombok.Data;
import io.renren.common.base.IDRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统角色权限关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Data
@Api("系统角色权限关系-删除")
public class SysRoleAuthoritiesDelRequest extends BaseRequest {

    /**
     * 系统权限
     */
    @ApiModelProperty(value = "系统权限")
    @NotBlank(message = "系统权限不能为空")
    private String sysAuthoritiesId;
    /**
     * 系统角色
     */
    @ApiModelProperty(value = "系统角色")
    @NotBlank(message = "系统角色不能为空")
    private String sysRoleId;

}
