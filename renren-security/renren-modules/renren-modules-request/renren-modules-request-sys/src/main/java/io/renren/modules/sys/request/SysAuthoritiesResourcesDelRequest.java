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
 * 系统权限资源关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Data
@Api("系统权限资源关系-删除")
public class SysAuthoritiesResourcesDelRequest extends BaseRequest {
    /**
     * 系统权限
     */
    @ApiModelProperty(value = "系统权限")
    @NotBlank(message = "系统权限不能为空")
    private String sysAuthoritiesId;

    /**
     * 系统资源
     */
    @ApiModelProperty(value = "系统资源")
    @NotBlank(message = "系统资源不能为空")
    private String sysResourcesId;

}
