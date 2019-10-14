package io.renren.modules.sys.request;

import io.renren.common.base.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 系统权限资源关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Data
@Api("系统权限资源关系-已选列表(左)")
public class SysAuthoritiesResourcesLeftPageRequest extends PageRequest {
    /**
     * 系统权限
     */
    @ApiModelProperty(value = "系统权限")
    @NotBlank(message = "系统权限不能为空")
    private String sysAuthoritiesId;
}
