package io.renren.modules.sys.request;

import io.renren.common.base.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 系统角色权限关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Data
@Api("系统角色权限关系-待选列表(右)")
public class SysRoleAuthoritiesRightPageRequest extends PageRequest {

    /**
     * 系统角色
     */
    @ApiModelProperty(value = "系统角色")
    @NotBlank(message = "系统角色不能为空")
    private String sysRoleId;
}
