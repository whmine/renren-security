package io.renren.modules.sys.request;

import io.renren.common.base.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 字典类型
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 09:00:09
 */
@Data
@Api("字典类型-列表")
public class QueryDictDataRequest extends BaseRequest {

    /**
     * 字典类型
     */
    @ApiModelProperty(value = "字典类型")
    @NotBlank
    private String type;


}
