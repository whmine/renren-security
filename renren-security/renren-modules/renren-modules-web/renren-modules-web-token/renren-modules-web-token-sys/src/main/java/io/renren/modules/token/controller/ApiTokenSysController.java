package io.renren.modules.token.controller;

import io.renren.common.exception.RRException;
import io.renren.common.utils.R;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.SysLog;
import io.renren.modules.token.request.LoginSysRequest;
import io.renren.modules.token.service.ApiTokenSysService;
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
public class ApiTokenSysController {

    @Autowired
    private ApiTokenSysService apiTokenSysService;


    @SysLog("登录-本地账号")
    @PostMapping("loginSys")
    @ApiOperation("登录-本地账号")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "api_token",
            authoritiesCnName = "系统登录权限",
            name = "api_token_loginSys",
            cnName = "登录-本地账号",
            describe = "登录-本地账号",
            authzCode = "api:token:loginSys"
    )
    public R loginSys(@Valid @RequestBody LoginSysRequest form, BindingResult result) {

        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map<String, Object> map = null;
        try {
            map = apiTokenSysService.loginSys(form);
        } catch (RRException e) {
            log.debug("异常[api:token:loginSys]:" + e.getMessage());
            return R.error(e.getCode(), e.getMessage());
        }
        return R.ok().put("data", map);
    }

}
