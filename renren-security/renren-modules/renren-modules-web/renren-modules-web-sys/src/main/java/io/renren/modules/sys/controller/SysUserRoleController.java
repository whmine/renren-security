package io.renren.modules.sys.controller;

import java.util.Map;

import io.renren.common.exception.RRException;
import io.renren.framework.annotation.AuthzCode;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.Login;
import io.renren.framework.annotation.SysLog;
import io.renren.common.utils.AbstractController;
import io.renren.modules.sys.request.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import io.renren.modules.sys.entity.SysUserRoleEntity;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 系统用户角色关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Slf4j
@Api(tags = "系统用户角色关系")
@RestController
@RequestMapping("/sys/sysuserrole")
public class SysUserRoleController extends AbstractController {
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统用户角色关系-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user_role",
            authoritiesCnName = "系统用户角色关系权限",
            name = "sys_sysuserrole_list",
            cnName = "系统用户角色关系-列表",
            describe = "系统用户角色关系-列表",
            authzCode = "sys:sysuserrole:list"
    )
    @AuthzCode(authzCode = "sys:sysuserrole:list")
    public R list(@Valid @RequestBody SysUserRolePageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = sysUserRoleService.queryPage(request);
        return R.ok().put("data", page);
    }

    /**
     * 待选列表(右)
     */
    @Login
    @SysLog("系统用户角色关系-待选列表(右)")
    @PostMapping("rightList")
    @ApiOperation("待选列表(右)")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user_role",
            authoritiesCnName = "系统用户角色关系权限",
            name = "sys_sysuserrole_rightList",
            cnName = "系统用户角色关系-待选列表(右)",
            describe = "系统用户角色关系-待选列表(右)",
            authzCode = "sys:sysuserrole:rightList"
    )
    @AuthzCode(authzCode = "sys:sysuserrole:rightList")
    public R rightList(@Valid @RequestBody SysUserRoleRightPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            return R.ok().put("data", sysUserRoleService.rightList(request));
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }

    /**
     * 已选列表(左)
     */
    @Login
    @SysLog("系统用户角色关系-已选列表(左)")
    @PostMapping("leftList")
    @ApiOperation("已选列表(左)")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user_role",
            authoritiesCnName = "系统用户角色关系权限",
            name = "sys_sysuserrole_leftList",
            cnName = "系统用户角色关系-已选列表(左)",
            describe = "系统用户角色关系-已选列表(左)",
            authzCode = "sys:sysuserrole:leftList"
    )
    @AuthzCode(authzCode = "sys:sysuserrole:leftList")
    public R leftList(@Valid @RequestBody SysUserRoleLeftPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            return R.ok().put("data", sysUserRoleService.leftList(request));
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统用户角色关系-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user_role",
            authoritiesCnName = "系统用户角色关系权限",
            name = "sys_sysuserrole_info",
            cnName = "系统用户角色关系-信息",
            describe = "系统用户角色关系-信息",
            authzCode = "sys:sysuserrole:info"
    )
    @AuthzCode(authzCode = "sys:sysuserrole:info")
    public R info(@Valid @RequestBody SysUserRoleInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        SysUserRoleEntity sysUserRole = sysUserRoleService.getById(request.getId());

        return R.ok().put("data", sysUserRole);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统用户角色关系-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user_role",
            authoritiesCnName = "系统用户角色关系权限",
            name = "sys_sysuserrole_save",
            cnName = "系统用户角色关系-保存",
            describe = "系统用户角色关系-保存",
            authzCode = "sys:sysuserrole:save"
    )
    @AuthzCode(authzCode = "sys:sysuserrole:save")
    public R save(@Valid @RequestBody SysUserRoleSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        SysUserRoleEntity entity = new SysUserRoleEntity();

        entity.setSysRoleId(request.getSysRoleId());
        entity.setSysUserId(request.getSysUserId());

        sysUserRoleService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统用户角色关系-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user_role",
            authoritiesCnName = "系统用户角色关系权限",
            name = "sys_sysuserrole_update",
            cnName = "系统用户角色关系-修改",
            describe = "系统用户角色关系-修改",
            authzCode = "sys:sysuserrole:update"
    )
    @AuthzCode(authzCode = "sys:sysuserrole:update")
    public R update(@Valid @RequestBody SysUserRoleUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        SysUserRoleEntity entity = new SysUserRoleEntity();


        entity.setId(request.getId());
        entity.setSysRoleId(request.getSysRoleId());
        entity.setSysUserId(request.getSysUserId());

        sysUserRoleService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统用户角色关系-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user_role",
            authoritiesCnName = "系统用户角色关系权限",
            name = "sys_sysuserrole_delete",
            cnName = "系统用户角色关系-删除",
            describe = "系统用户角色关系-删除",
            authzCode = "sys:sysuserrole:delete"
    )
    @AuthzCode(authzCode = "sys:sysuserrole:delete")
    public R delete(@Valid @RequestBody SysUserRoleDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            sysUserRoleService.remove(request);
            return R.ok();
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }


    @Login
    @SysLog("系统用户角色关系-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user_role",
            authoritiesCnName = "系统用户角色关系权限",
            name = "sys_sysuserrole_push",
            cnName = "系统用户角色关系-发布redis",
            describe = "系统用户角色关系-发布redis",
            authzCode = "sys:sysuserrole:push"
    )
    @AuthzCode(authzCode = "sys:sysuserrole:push")
    public R push(@Valid @RequestBody SysUserRolePushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysUserRoleService.push(request);
        return R.ok();
    }

}
