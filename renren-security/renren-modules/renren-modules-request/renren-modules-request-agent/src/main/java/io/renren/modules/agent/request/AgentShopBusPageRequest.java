package io.renren.modules.agent.request;

import lombok.Data;
import io.renren.common.base.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * 代理商业务员
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 09:24:45
 */
@Data
@Api("代理商业务员-分页查询")
public class AgentShopBusPageRequest extends PageRequest {

    @ApiModelProperty(value = "所属代理商")
    private String busParent;
}
