package io.renren.modules.sys.request;

import io.renren.common.base.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 系统用户角色关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Data
@Api("系统用户角色关系-待选列表(右)")
public class SysUserRoleRightPageRequest extends PageRequest {
    /**
     * 系统用户
     */
    @ApiModelProperty(value = "系统用户")
    private String sysUserId;
}
