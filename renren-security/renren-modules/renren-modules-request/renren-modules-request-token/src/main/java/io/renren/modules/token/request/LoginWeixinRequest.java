package io.renren.modules.token.request;

import io.renren.common.base.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "登录-微信账号")
public class LoginWeixinRequest extends BaseRequest {

    @ApiModelProperty(value = "Code")
    @NotBlank(message = "Code不能为空")
    private String code;

    @ApiModelProperty(value = "昵称")
    private String name;

    @ApiModelProperty(value = "头像")
    private String url;
}
