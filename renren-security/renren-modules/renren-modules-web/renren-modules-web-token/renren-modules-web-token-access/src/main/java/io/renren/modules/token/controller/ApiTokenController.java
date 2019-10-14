package io.renren.modules.token.controller;

import io.renren.common.utils.R;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.SysLog;
import io.renren.modules.token.request.LoginAccessRequest;
import io.renren.modules.token.request.LoginWeixinRequest;
import io.renren.modules.token.service.ApiTokenAccessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/token")
@Api(tags = "用户公共接口")
@Slf4j
public class ApiTokenController {

    @Autowired
    private ApiTokenAccessService apiTokenService;

    @SysLog("登录-微信公众账号")
    @PostMapping("loginAccess")
    @ApiOperation("登录-微信公众账号")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "api_token",
            authoritiesCnName = "系统登录权限",
            name = "api_token_loginAccess",
            cnName = "登录-微信公众账号",
            describe = "登录-微信公众账号",
            authzCode = "api:token:loginAccess"
    )
    public R loginAccess(@Valid @RequestBody LoginAccessRequest form, BindingResult result) {

        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map<String, Object> map = null;
        try {
            map = apiTokenService.loginAccess(form);
        } catch (Exception e) {
            log.debug("异常：" + e.getMessage());
            return R.error(1, e.getMessage());
        }
        return R.ok().put("data", map);
    }


}
