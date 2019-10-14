package io.renren.modules.sys.request;
import lombok.Data;
import io.renren.common.base.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统服务
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-20 10:12:44
 */
@Data
@Api("系统服务-分页查询")
public class SysServicePageRequest extends PageRequest {

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;


}
