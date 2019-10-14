package io.renren.modules.token.request;

import io.renren.common.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@ApiModel(value = "登录-本地账号表单")
public class LoginSysRequest extends BaseRequest {

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String username;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}
