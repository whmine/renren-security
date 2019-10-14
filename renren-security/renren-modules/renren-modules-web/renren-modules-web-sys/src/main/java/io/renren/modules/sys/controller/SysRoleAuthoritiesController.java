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

import io.renren.modules.sys.entity.SysRoleAuthoritiesEntity;
import io.renren.modules.sys.service.SysRoleAuthoritiesService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 系统角色权限关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Slf4j
@Api(tags = "系统角色权限关系")
@RestController
@RequestMapping("/sys/sysroleauthorities")
public class SysRoleAuthoritiesController extends AbstractController {
    @Autowired
    private SysRoleAuthoritiesService sysRoleAuthoritiesService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统角色权限关系-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role_authorities",
            authoritiesCnName = "系统角色权限关系权限",
            name = "sys_sysroleauthorities_list",
            cnName = "系统角色权限关系-列表",
            describe = "系统角色权限关系-列表",
            authzCode = "sys:sysroleauthorities:list"
    )
    @AuthzCode(authzCode = "sys:sysroleauthorities:list")
    public R list(@Valid @RequestBody SysRoleAuthoritiesPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = sysRoleAuthoritiesService.queryPage(request);
        return R.ok().put("data", page);
    }

    /**
     * 待选列表(右)
     */
    @Login
    @SysLog("系统角色权限关系-待选列表(右)")
    @PostMapping("rightList")
    @ApiOperation("待选列表(右)")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role_authorities",
            authoritiesCnName = "系统角色权限关系权限",
            name = "sys_sysroleauthorities_rightList",
            cnName = "系统角色权限关系-待选列表(右)",
            describe = "系统角色权限关系-待选列表(右)",
            authzCode = "sys:sysroleauthorities:rightList"
    )
    @AuthzCode(authzCode = "sys:sysroleauthorities:rightList")
    public R rightList(@Valid @RequestBody SysRoleAuthoritiesRightPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            return R.ok().put("data", sysRoleAuthoritiesService.rightList(request));
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }

    /**
     * 已选列表(左)
     */
    @Login
    @SysLog("系统角色权限关系-已选列表(左)")
    @PostMapping("leftList")
    @ApiOperation("已选列表(左)")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role_authorities",
            authoritiesCnName = "系统角色权限关系权限",
            name = "sys_sysroleauthorities_leftList",
            cnName = "系统角色权限关系-已选列表(左)",
            describe = "系统角色权限关系-已选列表(左)",
            authzCode = "sys:sysroleauthorities:leftList"
    )
    @AuthzCode(authzCode = "sys:sysroleauthorities:leftList")
    public R leftList(@Valid @RequestBody SysRoleAuthoritiesLeftPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            return R.ok().put("data", sysRoleAuthoritiesService.leftList(request));
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统角色权限关系-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role_authorities",
            authoritiesCnName = "系统角色权限关系权限",
            name = "sys_sysroleauthorities_info",
            cnName = "系统角色权限关系-信息",
            describe = "系统角色权限关系-信息",
            authzCode = "sys:sysroleauthorities:info"
    )
    @AuthzCode(authzCode = "sys:sysroleauthorities:info")
    public R info(@Valid @RequestBody SysRoleAuthoritiesInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        SysRoleAuthoritiesEntity sysRoleAuthorities = sysRoleAuthoritiesService.getById(request.getId());

        return R.ok().put("data", sysRoleAuthorities);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统角色权限关系-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role_authorities",
            authoritiesCnName = "系统角色权限关系权限",
            name = "sys_sysroleauthorities_save",
            cnName = "系统角色权限关系-保存",
            describe = "系统角色权限关系-保存",
            authzCode = "sys:sysroleauthorities:save"
    )
    @AuthzCode(authzCode = "sys:sysroleauthorities:save")
    public R save(@Valid @RequestBody SysRoleAuthoritiesSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        SysRoleAuthoritiesEntity entity = new SysRoleAuthoritiesEntity();

        entity.setSysAuthoritiesId(request.getSysAuthoritiesId());
        entity.setSysRoleId(request.getSysRoleId());

        sysRoleAuthoritiesService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统角色权限关系-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role_authorities",
            authoritiesCnName = "系统角色权限关系权限",
            name = "sys_sysroleauthorities_update",
            cnName = "系统角色权限关系-修改",
            describe = "系统角色权限关系-修改",
            authzCode = "sys:sysroleauthorities:update"
    )
    @AuthzCode(authzCode = "sys:sysroleauthorities:update")
    public R update(@Valid @RequestBody SysRoleAuthoritiesUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        SysRoleAuthoritiesEntity entity = new SysRoleAuthoritiesEntity();


        entity.setId(request.getId());
        entity.setSysAuthoritiesId(request.getSysAuthoritiesId());
        entity.setSysRoleId(request.getSysRoleId());

        sysRoleAuthoritiesService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统角色权限关系-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role_authorities",
            authoritiesCnName = "系统角色权限关系权限",
            name = "sys_sysroleauthorities_delete",
            cnName = "系统角色权限关系-删除",
            describe = "系统角色权限关系-删除",
            authzCode = "sys:sysroleauthorities:delete"
    )
    @AuthzCode(authzCode = "sys:sysroleauthorities:delete")
    public R delete(@Valid @RequestBody SysRoleAuthoritiesDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            sysRoleAuthoritiesService.remove(request);
            return R.ok();
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }


    @Login
    @SysLog("系统角色权限关系-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role_authorities",
            authoritiesCnName = "系统角色权限关系权限",
            name = "sys_sysroleauthorities_push",
            cnName = "系统角色权限关系-发布redis",
            describe = "系统角色权限关系-发布redis",
            authzCode = "sys:sysroleauthorities:push"
    )
    @AuthzCode(authzCode = "sys:sysroleauthorities:push")
    public R push(@Valid @RequestBody SysRoleAuthoritiesPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysRoleAuthoritiesService.push(request);
        return R.ok();
    }

}
