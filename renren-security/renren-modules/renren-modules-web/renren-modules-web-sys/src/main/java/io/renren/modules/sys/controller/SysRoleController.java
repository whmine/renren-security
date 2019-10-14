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

import io.renren.modules.sys.request.SysRolePageRequest;
import io.renren.modules.sys.request.SysRoleInfoRequest;
import io.renren.modules.sys.request.SysRoleSaveRequest;
import io.renren.modules.sys.request.SysRoleUpdateRequest;
import io.renren.modules.sys.request.SysRoleDelRequest;
import io.renren.modules.sys.request.SysRolePushRequest;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 系统角色
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Slf4j
@Api(tags = "系统角色")
@RestController
@RequestMapping("/sys/sysrole")
public class SysRoleController extends AbstractController {
    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统角色-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role",
            authoritiesCnName = "系统角色权限",
            name = "sys_sysrole_list",
            cnName = "系统角色-列表",
            describe = "系统角色-列表",
            authzCode = "sys:sysrole:list"
    )
    @AuthzCode(authzCode = "sys:sysrole:list")
    public R list(@Valid @RequestBody SysRolePageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = sysRoleService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统角色-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role",
            authoritiesCnName = "系统角色权限",
            name = "sys_sysrole_info",
            cnName = "系统角色-信息",
            describe = "系统角色-信息",
            authzCode = "sys:sysrole:info"
    )
    @AuthzCode(authzCode = "sys:sysrole:info")
    public R info(@Valid @RequestBody SysRoleInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            SysRoleEntity sysRole = sysRoleService.getById(request.getId());

        return R.ok().put("data", sysRole);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统角色-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role",
            authoritiesCnName = "系统角色权限",
            name = "sys_sysrole_save",
            cnName = "系统角色-保存",
            describe = "系统角色-保存",
            authzCode = "sys:sysrole:save"
    )
    @AuthzCode(authzCode = "sys:sysrole:save")
    public R save(@Valid @RequestBody SysRoleSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            SysRoleEntity entity = new SysRoleEntity();

                                                        entity.setName(request.getName());
                                                entity.setCnName(request.getCnName());
                                                entity.setIsSys(request.getIsSys());
                                                entity.setLevel(request.getLevel());
                                                entity.setInheritable(request.getInheritable());
                                                entity.setParentRole(request.getParentRole());
                                                                                                                                                                                                        
            sysRoleService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统角色-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role",
            authoritiesCnName = "系统角色权限",
            name = "sys_sysrole_update",
            cnName = "系统角色-修改",
            describe = "系统角色-修改",
            authzCode = "sys:sysrole:update"
    )
    @AuthzCode(authzCode = "sys:sysrole:update")
    public R update(@Valid @RequestBody SysRoleUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            SysRoleEntity entity = new SysRoleEntity();



                                    entity.setId(request.getId());
                                                entity.setName(request.getName());
                                                entity.setCnName(request.getCnName());
                                                entity.setIsSys(request.getIsSys());
                                                entity.setLevel(request.getLevel());
                                                entity.setInheritable(request.getInheritable());
                                                entity.setParentRole(request.getParentRole());
                                                                                                                                                                                                        
            sysRoleService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统角色-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role",
            authoritiesCnName = "系统角色权限",
            name = "sys_sysrole_delete",
            cnName = "系统角色-删除",
            describe = "系统角色-删除",
            authzCode = "sys:sysrole:delete"
    )
    @AuthzCode(authzCode = "sys:sysrole:delete")
    public R delete(@Valid @RequestBody SysRoleDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysRoleService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("系统角色-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_role",
            authoritiesCnName = "系统角色权限",
            name = "sys_sysrole_push",
            cnName = "系统角色-发布redis",
            describe = "系统角色-发布redis",
            authzCode = "sys:sysrole:push"
    )
    @AuthzCode(authzCode = "sys:sysrole:push")
    public R push(@Valid @RequestBody SysRolePushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysRoleService.push(request);
        return R.ok();
    }

}
