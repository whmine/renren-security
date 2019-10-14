package io.renren.modules.web.token.controller;

import io.renren.common.exception.RRException;
import io.renren.common.utils.R;
import io.renren.framework.annotation.AuthzCode;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.Login;
import io.renren.framework.annotation.SysLog;
import io.renren.modules.token.request.LogOutRequest;
import io.renren.modules.web.token.service.ApiTokenLogOutService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/token")
@Api(tags = "用户公共接口")
@Slf4j
public class ApiTokenLogOutController {
    @Autowired
    private ApiTokenLogOutService apiTokenLogOutService;


    @Login
    @SysLog("登录-TOKEN退出")
    @PostMapping("logOut")
    @ApiOperation("登录-TOKEN退出")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "api_token",
            authoritiesCnName = "系统登录权限",
            name = "api_token_logOut",
            cnName = "登录-TOKEN退出",
            describe = "登录-TOKEN退出",
            authzCode = "api:token:logOut"
    )
    @AuthzCode(authzCode = "api:token:logOut")
    public R logOut(@Valid @RequestBody LogOutRequest request, BindingResult result) {

        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            apiTokenLogOutService.logOut(request);
        } catch (RRException e) {
            log.debug("异常[api:token:logOut]:" + e.getMessage());
            return R.error(e.getCode(), e.getMessage());
        }
        return R.ok();
    }
}
