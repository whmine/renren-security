package io.renren.modules.sys.request;

import io.renren.common.base.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 系统常量
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
@Data
@Api("系统常量-根据名称查询详情")
public class SysConfigfindNameInfoRequest extends BaseRequest {

    /**
     * 名称编码
     */
    @ApiModelProperty(value = "名称编码")
    @NotBlank(message = "名称编码不能为空")
    private String name;

}
