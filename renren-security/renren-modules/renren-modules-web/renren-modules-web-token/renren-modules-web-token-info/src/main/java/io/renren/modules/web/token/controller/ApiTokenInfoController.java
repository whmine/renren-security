package io.renren.modules.web.token.controller;

import io.renren.common.exception.RRException;
import io.renren.common.utils.R;
import io.renren.framework.annotation.AuthzCode;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.Login;
import io.renren.framework.annotation.SysLog;
import io.renren.modules.token.request.InfoRequest;
import io.renren.modules.web.token.service.ApiTokenInfoService;
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
public class ApiTokenInfoController {
    @Autowired
    private ApiTokenInfoService apiTokenInfoService;


    @Login
    @SysLog("登录-信息")
    @PostMapping("info")
    @ApiOperation("登录-信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "api_token",
            authoritiesCnName = "系统登录权限",
            name = "api_token_info",
            cnName = "登录-信息",
            describe = "登录-信息",
            authzCode = "api:token:info"
    )
    @AuthzCode(authzCode = "api:token:info")
    public R info(@Valid @RequestBody InfoRequest request, BindingResult result) {

        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            Map map = apiTokenInfoService.info(request);
            log.debug("登录-信息:" + map);
            return R.ok().put("data", map);
        } catch (RRException e) {
            log.debug("异常[api:token:info]:" + e.getMessage());
            return R.error(e.getCode(), e.getMessage());
        }

    }
}
