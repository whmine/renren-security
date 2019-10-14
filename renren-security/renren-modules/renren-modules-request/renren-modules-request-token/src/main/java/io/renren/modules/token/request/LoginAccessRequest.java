package io.renren.modules.token.request;

import io.renren.common.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "登录-微信公众账号")
public class LoginAccessRequest extends BaseRequest {

    @ApiModelProperty(value = "Code")
    @NotBlank(message = "Code不能为空")
    private String code;
}
