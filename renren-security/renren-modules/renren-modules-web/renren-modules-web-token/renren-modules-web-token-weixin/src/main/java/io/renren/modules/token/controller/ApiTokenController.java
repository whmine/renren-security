package io.renren.modules.token.controller;

import io.renren.common.utils.R;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.SysLog;
import io.renren.modules.token.request.LoginWeixinRequest;
import io.renren.modules.token.service.ApiTokenWeixinService;
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
    private ApiTokenWeixinService apiTokenService;

    @SysLog("登录-微信账号")
    @PostMapping("loginWeixin")
    @ApiOperation("登录-微信账号")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "api_token",
            authoritiesCnName = "系统登录权限",
            name = "api_token_loginWeixin",
            cnName = "登录-微信账号",
            describe = "登录-微信账号",
            authzCode = "api:token:loginWeixin"
    )
    public R loginWeixin(@Valid @RequestBody LoginWeixinRequest form, BindingResult result) {

        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map<String, Object> map = null;
        try {
            map = apiTokenService.loginWeixin(form);
        } catch (Exception e) {
            log.debug("异常：" + e.getMessage());
            return R.error(1, e.getMessage());
        }
        return R.ok().put("data", map);
    }


}
