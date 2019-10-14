package io.renren.modules.sys.request;

import io.renren.common.base.BaseRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 发送申请资料邮件
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 14:34:35
 */
@Data
@Api("短信验证码-发送邮件")
public class SysSmsCodeSendMailRequest extends BaseRequest {

    @ApiModelProperty(value = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    private String mail;

    @ApiModelProperty(value = "短信类型")
    @NotBlank(message = "邮件类型不能为空")
    private String smsType;
}
