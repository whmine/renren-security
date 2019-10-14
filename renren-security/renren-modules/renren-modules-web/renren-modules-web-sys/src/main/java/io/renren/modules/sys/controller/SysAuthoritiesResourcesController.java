package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.exception.RRException;
import io.renren.framework.annotation.AuthzCode;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.Login;
import io.renren.framework.annotation.SysLog;
import io.renren.common.utils.AbstractController;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.sys.request.*;
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

import io.renren.modules.sys.entity.SysAuthoritiesResourcesEntity;
import io.renren.modules.sys.service.SysAuthoritiesResourcesService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 系统权限资源关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Slf4j
@Api(tags = "系统权限资源关系")
@RestController
@RequestMapping("/sys/sysauthoritiesresources")
public class SysAuthoritiesResourcesController extends AbstractController {
    @Autowired
    private SysAuthoritiesResourcesService sysAuthoritiesResourcesService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统权限资源关系-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities_resources",
            authoritiesCnName = "系统权限资源关系权限",
            name = "sys_sysauthoritiesresources_list",
            cnName = "系统权限资源关系-列表",
            describe = "系统权限资源关系-列表",
            authzCode = "sys:sysauthoritiesresources:list"
    )
    @AuthzCode(authzCode = "sys:sysauthoritiesresources:list")
    public R list(@Valid @RequestBody SysAuthoritiesResourcesPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = sysAuthoritiesResourcesService.queryPage(request);
        return R.ok().put("data", page);
    }

    /**
     * 待选列表(右)
     */
    @Login
    @SysLog("系统权限资源关系-待选列表(右)")
    @PostMapping("rightList")
    @ApiOperation("待选列表(右)")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities_resources",
            authoritiesCnName = "系统权限资源关系权限",
            name = "sys_sysauthoritiesresources_rightList",
            cnName = "系统权限资源关系-待选列表(右)",
            describe = "系统权限资源关系-待选列表(右)",
            authzCode = "sys:sysauthoritiesresources:rightList"
    )
    @AuthzCode(authzCode = "sys:sysauthoritiesresources:rightList")
    public R rightList(@Valid @RequestBody SysAuthoritiesResourcesRightPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            return R.ok().put("data", sysAuthoritiesResourcesService.rightList(request));
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }

    /**
     * 已选列表(左)
     */
    @Login
    @SysLog("系统权限资源关系-已选列表(左)")
    @PostMapping("leftList")
    @ApiOperation("已选列表(左)")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities_resources",
            authoritiesCnName = "系统权限资源关系权限",
            name = "sys_sysauthoritiesresources_leftList",
            cnName = "系统权限资源关系-已选列表(左)",
            describe = "系统权限资源关系-已选列表(左)",
            authzCode = "sys:sysauthoritiesresources:leftList"
    )
    @AuthzCode(authzCode = "sys:sysauthoritiesresources:leftList")
    public R leftList(@Valid @RequestBody SysAuthoritiesResourcesLeftPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            return R.ok().put("data", sysAuthoritiesResourcesService.leftList(request));
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统权限资源关系-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities_resources",
            authoritiesCnName = "系统权限资源关系权限",
            name = "sys_sysauthoritiesresources_info",
            cnName = "系统权限资源关系-信息",
            describe = "系统权限资源关系-信息",
            authzCode = "sys:sysauthoritiesresources:info"
    )
    @AuthzCode(authzCode = "sys:sysauthoritiesresources:info")
    public R info(@Valid @RequestBody SysAuthoritiesResourcesInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        SysAuthoritiesResourcesEntity sysAuthoritiesResources = sysAuthoritiesResourcesService.getById(request.getId());

        return R.ok().put("data", sysAuthoritiesResources);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统权限资源关系-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities_resources",
            authoritiesCnName = "系统权限资源关系权限",
            name = "sys_sysauthoritiesresources_save",
            cnName = "系统权限资源关系-保存",
            describe = "系统权限资源关系-保存",
            authzCode = "sys:sysauthoritiesresources:save"
    )
    @AuthzCode(authzCode = "sys:sysauthoritiesresources:save")
    public R save(@Valid @RequestBody SysAuthoritiesResourcesSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        SysAuthoritiesResourcesEntity entity = new SysAuthoritiesResourcesEntity();

        entity.setSysAuthoritiesId(request.getSysAuthoritiesId());
        entity.setSysResourcesId(request.getSysResourcesId());

        sysAuthoritiesResourcesService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统权限资源关系-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities_resources",
            authoritiesCnName = "系统权限资源关系权限",
            name = "sys_sysauthoritiesresources_update",
            cnName = "系统权限资源关系-修改",
            describe = "系统权限资源关系-修改",
            authzCode = "sys:sysauthoritiesresources:update"
    )
    @AuthzCode(authzCode = "sys:sysauthoritiesresources:update")
    public R update(@Valid @RequestBody SysAuthoritiesResourcesUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        SysAuthoritiesResourcesEntity entity = new SysAuthoritiesResourcesEntity();


        entity.setId(request.getId());
        entity.setSysAuthoritiesId(request.getSysAuthoritiesId());
        entity.setSysResourcesId(request.getSysResourcesId());

        sysAuthoritiesResourcesService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统权限资源关系-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities_resources",
            authoritiesCnName = "系统权限资源关系权限",
            name = "sys_sysauthoritiesresources_delete",
            cnName = "系统权限资源关系-删除",
            describe = "系统权限资源关系-删除",
            authzCode = "sys:sysauthoritiesresources:delete"
    )
    @AuthzCode(authzCode = "sys:sysauthoritiesresources:delete")
    public R delete(@Valid @RequestBody SysAuthoritiesResourcesDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            sysAuthoritiesResourcesService.remove(request);
            return R.ok();
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }


    @Login
    @SysLog("系统权限资源关系-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_authorities_resources",
            authoritiesCnName = "系统权限资源关系权限",
            name = "sys_sysauthoritiesresources_push",
            cnName = "系统权限资源关系-发布redis",
            describe = "系统权限资源关系-发布redis",
            authzCode = "sys:sysauthoritiesresources:push"
    )
    @AuthzCode(authzCode = "sys:sysauthoritiesresources:push")
    public R push(@Valid @RequestBody SysAuthoritiesResourcesPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysAuthoritiesResourcesService.push(request);
        return R.ok();
    }

}
