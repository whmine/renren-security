package io.renren.modules.sys.request;

import io.renren.common.base.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 短信验证码
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 14:34:35
 */
@Data
@Api("短信验证码-发送验证码")
public class SysSmsCodeSendRequest extends BaseRequest {

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "短信类型")
    @NotNull(message = "短信类型不能为空")
    private Integer smsType;
}
