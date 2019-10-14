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

import io.renren.modules.sys.request.SysAuthoritiesPageRequest;
import io.renren.modules.sys.request.SysAuthoritiesInfoRequest;
import io.renren.modules.sys.request.SysAuthoritiesSaveRequest;
import io.renren.modules.sys.request.SysAuthoritiesUpdateRequest;
import io.renren.modules.sys.request.SysAuthoritiesDelRequest;
import io.renren.modules.sys.request.SysAuthoritiesPushRequest;
import io.renren.modules.sys.entity.SysAuthoritiesEntity;
import io.renren.modules.sys.service.SysAuthoritiesService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 系统权限
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Slf4j
@Api(tags = "系统权限")
@RestController
@RequestMapping("/sys/sysauthorities")
public class SysAuthoritiesController extends AbstractController {
    @Autowired
    private SysAuthoritiesService sysAuthoritiesService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统权限-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities",
            authoritiesCnName = "系统权限权限",
            name = "sys_sysauthorities_list",
            cnName = "系统权限-列表",
            describe = "系统权限-列表",
            authzCode = "sys:sysauthorities:list"
    )
    @AuthzCode(authzCode = "sys:sysauthorities:list")
    public R list(@Valid @RequestBody SysAuthoritiesPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = sysAuthoritiesService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统权限-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities",
            authoritiesCnName = "系统权限权限",
            name = "sys_sysauthorities_info",
            cnName = "系统权限-信息",
            describe = "系统权限-信息",
            authzCode = "sys:sysauthorities:info"
    )
    @AuthzCode(authzCode = "sys:sysauthorities:info")
    public R info(@Valid @RequestBody SysAuthoritiesInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            SysAuthoritiesEntity sysAuthorities = sysAuthoritiesService.getById(request.getId());

        return R.ok().put("data", sysAuthorities);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统权限-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities",
            authoritiesCnName = "系统权限权限",
            name = "sys_sysauthorities_save",
            cnName = "系统权限-保存",
            describe = "系统权限-保存",
            authzCode = "sys:sysauthorities:save"
    )
    @AuthzCode(authzCode = "sys:sysauthorities:save")
    public R save(@Valid @RequestBody SysAuthoritiesSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            SysAuthoritiesEntity entity = new SysAuthoritiesEntity();

                                                        entity.setName(request.getName());
                                                entity.setCnName(request.getCnName());
                                                entity.setAuthorities(request.getAuthorities());
                                                entity.setIsSys(request.getIsSys());
                                                                                                                                                                                                        
            sysAuthoritiesService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统权限-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities",
            authoritiesCnName = "系统权限权限",
            name = "sys_sysauthorities_update",
            cnName = "系统权限-修改",
            describe = "系统权限-修改",
            authzCode = "sys:sysauthorities:update"
    )
    @AuthzCode(authzCode = "sys:sysauthorities:update")
    public R update(@Valid @RequestBody SysAuthoritiesUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            SysAuthoritiesEntity entity = new SysAuthoritiesEntity();



                                    entity.setId(request.getId());
                                                entity.setName(request.getName());
                                                entity.setCnName(request.getCnName());
                                                entity.setAuthorities(request.getAuthorities());
                                                entity.setIsSys(request.getIsSys());
                                                                                                                                                                                                        
            sysAuthoritiesService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统权限-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities",
            authoritiesCnName = "系统权限权限",
            name = "sys_sysauthorities_delete",
            cnName = "系统权限-删除",
            describe = "系统权限-删除",
            authzCode = "sys:sysauthorities:delete"
    )
    @AuthzCode(authzCode = "sys:sysauthorities:delete")
    public R delete(@Valid @RequestBody SysAuthoritiesDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysAuthoritiesService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("系统权限-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities",
            authoritiesCnName = "系统权限权限",
            name = "sys_sysauthorities_push",
            cnName = "系统权限-发布redis",
            describe = "系统权限-发布redis",
            authzCode = "sys:sysauthorities:push"
    )
    @AuthzCode(authzCode = "sys:sysauthorities:push")
    public R push(@Valid @RequestBody SysAuthoritiesPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysAuthoritiesService.push(request);
        return R.ok();
    }

}
