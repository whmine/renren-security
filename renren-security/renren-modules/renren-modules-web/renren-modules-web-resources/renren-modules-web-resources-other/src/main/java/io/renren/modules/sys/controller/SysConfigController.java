package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.framework.annotation.AuthzCode;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.Login;
import io.renren.framework.annotation.SysLog;
import io.renren.common.utils.AbstractController;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import io.renren.modules.sys.entity.SysConfigEntity;
import io.renren.modules.sys.service.SysConfigService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 系统常量
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-07 10:49:21
 */
@Slf4j
@Api(tags = "系统常量")
@RestController
@RequestMapping("/sys/sysconfig")
public class SysConfigController extends AbstractController {
    @Autowired
    private SysConfigService sysConfigService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统常量-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_config",
            authoritiesCnName = "系统常量权限",
            name = "sys_sysconfig_list",
            cnName = "系统常量-列表",
            describe = "系统常量-列表",
            authzCode = "sys:sysconfig:list"
    )
    @AuthzCode(authzCode = "sys:sysconfig:list")
    public R list(@Valid @RequestBody SysConfigPageRequest request) {
        Map page = sysConfigService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统常量-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_config",
            authoritiesCnName = "系统常量权限",
            name = "sys_sysconfig_info",
            cnName = "系统常量-信息",
            describe = "系统常量-信息",
            authzCode = "sys:sysconfig:info"
    )
    @AuthzCode(authzCode = "sys:sysconfig:info")
    public R info(@Valid @RequestBody SysConfigInfoRequest request) {
        SysConfigEntity sysConfig = sysConfigService.getById(request.getId());

        return R.ok().put("data", sysConfig);
    }

    /**
     * 根据名称查询详情
     */
    @Login
    @SysLog("系统常量-根据名称查询详情")
    @PostMapping("findNameInfo")
    @ApiOperation("根据名称查询详情")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_config",
            authoritiesCnName = "系统常量权限",
            name = "sys_sysconfig_findNameInfo",
            cnName = "系统常量-根据名称查询详情",
            describe = "系统常量-根据名称查询详情",
            authzCode = "sys:sysconfig:findNameInfo"
    )
    @AuthzCode(authzCode = "sys:sysconfig:findNameInfo")
    public R findNameInfo(@Valid @RequestBody SysConfigfindNameInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        SysConfigEntity sysConfig = sysConfigService.findNameInfo(request);
        return R.ok().put("data", sysConfig);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统常量-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_config",
            authoritiesCnName = "系统常量权限",
            name = "sys_sysconfig_save",
            cnName = "系统常量-保存",
            describe = "系统常量-保存",
            authzCode = "sys:sysconfig:save"
    )
    @AuthzCode(authzCode = "sys:sysconfig:save")
    public R save(@Valid @RequestBody SysConfigSaveRequest request) {

        SysConfigEntity entity = new SysConfigEntity();

        entity.setName(request.getName());
        entity.setCnName(request.getCnName());
        entity.setValue(request.getValue());

        sysConfigService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统常量-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_config",
            authoritiesCnName = "系统常量权限",
            name = "sys_sysconfig_update",
            cnName = "系统常量-修改",
            describe = "系统常量-修改",
            authzCode = "sys:sysconfig:update"
    )
    @AuthzCode(authzCode = "sys:sysconfig:update")
    public R update(@Valid @RequestBody SysConfigUpdateRequest request) {
        SysConfigEntity entity = new SysConfigEntity();


        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setCnName(request.getCnName());
        entity.setValue(request.getValue());

        sysConfigService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统常量-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_config",
            authoritiesCnName = "系统常量权限",
            name = "sys_sysconfig_delete",
            cnName = "系统常量-删除",
            describe = "系统常量-删除",
            authzCode = "sys:sysconfig:delete"
    )
    @AuthzCode(authzCode = "sys:sysconfig:delete")
    public R delete(@Valid @RequestBody SysConfigDelRequest request) {
        sysConfigService.removeById(request.getId());
        return R.ok();
    }

}
