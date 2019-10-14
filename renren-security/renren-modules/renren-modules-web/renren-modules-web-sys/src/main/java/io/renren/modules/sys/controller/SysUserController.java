package io.renren.modules.sys.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.renren.framework.annotation.AuthzCode;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.Login;
import io.renren.framework.annotation.SysLog;
import io.renren.common.utils.AbstractController;
import io.renren.common.validator.ValidatorUtils;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
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

import io.renren.modules.sys.request.SysUserPageRequest;
import io.renren.modules.sys.request.SysUserInfoRequest;
import io.renren.modules.sys.request.SysUserSaveRequest;
import io.renren.modules.sys.request.SysUserUpdateRequest;
import io.renren.modules.sys.request.SysUserDelRequest;
import io.renren.modules.sys.request.SysUserPushRequest;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 系统用户表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Slf4j
@Api(tags = "系统用户表")
@RestController
@RequestMapping("/sys/sysuser")
public class SysUserController extends AbstractController {
    @Autowired
    private SysUserService sysUserService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统用户表-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user",
            authoritiesCnName = "系统用户表权限",
            name = "sys_sysuser_list",
            cnName = "系统用户表-列表",
            describe = "系统用户表-列表",
            authzCode = "sys:sysuser:list"
    )
    @AuthzCode(authzCode = "sys:sysuser:list")
    public R list(@Valid @RequestBody SysUserPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = sysUserService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统用户表-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user",
            authoritiesCnName = "系统用户表权限",
            name = "sys_sysuser_info",
            cnName = "系统用户表-信息",
            describe = "系统用户表-信息",
            authzCode = "sys:sysuser:info"
    )
    @AuthzCode(authzCode = "sys:sysuser:info")
    public R info(@Valid @RequestBody SysUserInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        SysUserEntity sysUser = sysUserService.getById(request.getId());

        return R.ok().put("data", sysUser);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统用户表-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user",
            authoritiesCnName = "系统用户表权限",
            name = "sys_sysuser_save",
            cnName = "系统用户表-保存",
            describe = "系统用户表-保存",
            authzCode = "sys:sysuser:save"
    )
    @AuthzCode(authzCode = "sys:sysuser:save")
    public R save(@Valid @RequestBody SysUserSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysUserService.save(request);
        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统用户表-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user",
            authoritiesCnName = "系统用户表权限",
            name = "sys_sysuser_update",
            cnName = "系统用户表-修改",
            describe = "系统用户表-修改",
            authzCode = "sys:sysuser:update"
    )
    @AuthzCode(authzCode = "sys:sysuser:update")
    public R update(@Valid @RequestBody SysUserUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        SysUserEntity entity = new SysUserEntity();


        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setCnName(request.getCnName());
        if (!StringUtils.isBlank(request.getSysPasswd())) {
            String sysPasswd = DigestUtils.sha256Hex(request.getSysPasswd());
            entity.setSysPasswd(sysPasswd);
        }
//        entity.setSysPasswd(request.getSysPasswd());
        entity.setIsAuth(request.getIsAuth());
        entity.setIsSys(request.getIsSys());
        entity.setIsOver(request.getIsOver());
        entity.setOverTime(request.getOverTime());
        entity.setIsAct(request.getIsAct());
        entity.setActTime(request.getActTime());
        entity.setIsLocked(request.getIsLocked());
        entity.setLockedTime(request.getLockedTime());
        entity.setIsExpired(request.getIsExpired());
        entity.setExpiredTime(request.getExpiredTime());
        entity.setStaffId(request.getStaffId());
        entity.setAreaId(request.getAreaId());

        sysUserService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统用户表-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user",
            authoritiesCnName = "系统用户表权限",
            name = "sys_sysuser_delete",
            cnName = "系统用户表-删除",
            describe = "系统用户表-删除",
            authzCode = "sys:sysuser:delete"
    )
    @AuthzCode(authzCode = "sys:sysuser:delete")
    public R delete(@Valid @RequestBody SysUserDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysUserService.remove(request);
        return R.ok();
    }


    @Login
    @SysLog("系统用户表-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_user",
            authoritiesCnName = "系统用户表权限",
            name = "sys_sysuser_push",
            cnName = "系统用户表-发布redis",
            describe = "系统用户表-发布redis",
            authzCode = "sys:sysuser:push"
    )
    @AuthzCode(authzCode = "sys:sysuser:push")
    public R push(@Valid @RequestBody SysUserPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysUserService.push(request);
        return R.ok();
    }

}
