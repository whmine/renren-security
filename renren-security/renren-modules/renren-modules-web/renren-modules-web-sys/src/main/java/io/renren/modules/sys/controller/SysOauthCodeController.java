package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.framework.annotation.AuthzCode;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.Login;
import io.renren.framework.annotation.SysLog;
import io.renren.common.utils.AbstractController;
import io.renren.common.validator.ValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

import io.renren.modules.sys.request.SysOauthCodePageRequest;
import io.renren.modules.sys.request.SysOauthCodeInfoRequest;
import io.renren.modules.sys.request.SysOauthCodeSaveRequest;
import io.renren.modules.sys.request.SysOauthCodeUpdateRequest;
import io.renren.modules.sys.request.SysOauthCodeDelRequest;
import io.renren.modules.sys.request.SysOauthCodePushRequest;
import io.renren.modules.sys.entity.SysOauthCodeEntity;
import io.renren.modules.sys.service.SysOauthCodeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 授权CODE
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Slf4j
@Api(tags = "授权CODE")
@RestController
@RequestMapping("/sys/sysoauthcode")
public class SysOauthCodeController extends AbstractController {
    @Autowired
    private SysOauthCodeService sysOauthCodeService;

    /**
     * 列表
     */
    @Login
    @SysLog("授权CODE-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_oauth_code",
            authoritiesCnName = "授权CODE权限",
            name = "sys_sysoauthcode_list",
            cnName = "授权CODE-列表",
            describe = "授权CODE-列表",
            authzCode = "sys:sysoauthcode:list"
    )
    @AuthzCode(authzCode = "sys:sysoauthcode:list")
    public R list(@Valid @RequestBody SysOauthCodePageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = sysOauthCodeService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("授权CODE-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_oauth_code",
            authoritiesCnName = "授权CODE权限",
            name = "sys_sysoauthcode_info",
            cnName = "授权CODE-信息",
            describe = "授权CODE-信息",
            authzCode = "sys:sysoauthcode:info"
    )
    @AuthzCode(authzCode = "sys:sysoauthcode:info")
    public R info(@Valid @RequestBody SysOauthCodeInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            SysOauthCodeEntity sysOauthCode = sysOauthCodeService.getById(request.getId());

        return R.ok().put("data", sysOauthCode);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("授权CODE-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_oauth_code",
            authoritiesCnName = "授权CODE权限",
            name = "sys_sysoauthcode_save",
            cnName = "授权CODE-保存",
            describe = "授权CODE-保存",
            authzCode = "sys:sysoauthcode:save"
    )
    @AuthzCode(authzCode = "sys:sysoauthcode:save")
    public R save(@Valid @RequestBody SysOauthCodeSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            SysOauthCodeEntity entity = new SysOauthCodeEntity();

                                                        entity.setOpenId(request.getOpenId());
                                                entity.setSysUserId(request.getSysUserId());
                                                                                                                                                                                                        
            sysOauthCodeService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("授权CODE-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_oauth_code",
            authoritiesCnName = "授权CODE权限",
            name = "sys_sysoauthcode_update",
            cnName = "授权CODE-修改",
            describe = "授权CODE-修改",
            authzCode = "sys:sysoauthcode:update"
    )
    @AuthzCode(authzCode = "sys:sysoauthcode:update")
    public R update(@Valid @RequestBody SysOauthCodeUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            SysOauthCodeEntity entity = new SysOauthCodeEntity();



                                    entity.setId(request.getId());
                                                entity.setOpenId(request.getOpenId());
                                                entity.setSysUserId(request.getSysUserId());
                                                                                                                                                                                                        
            sysOauthCodeService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("授权CODE-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_oauth_code",
            authoritiesCnName = "授权CODE权限",
            name = "sys_sysoauthcode_delete",
            cnName = "授权CODE-删除",
            describe = "授权CODE-删除",
            authzCode = "sys:sysoauthcode:delete"
    )
    @AuthzCode(authzCode = "sys:sysoauthcode:delete")
    public R delete(@Valid @RequestBody SysOauthCodeDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysOauthCodeService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("授权CODE-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_oauth_code",
            authoritiesCnName = "授权CODE权限",
            name = "sys_sysoauthcode_push",
            cnName = "授权CODE-发布redis",
            describe = "授权CODE-发布redis",
            authzCode = "sys:sysoauthcode:push"
    )
    @AuthzCode(authzCode = "sys:sysoauthcode:push")
    public R push(@Valid @RequestBody SysOauthCodePushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysOauthCodeService.push(request);
        return R.ok();
    }

}
