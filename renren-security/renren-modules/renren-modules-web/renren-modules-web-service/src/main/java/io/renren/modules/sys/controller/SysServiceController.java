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

import javax.validation.Valid;

import io.renren.modules.sys.request.SysServicePageRequest;
import io.renren.modules.sys.request.SysServiceInfoRequest;
import io.renren.modules.sys.request.SysServiceSaveRequest;
import io.renren.modules.sys.request.SysServiceUpdateRequest;
import io.renren.modules.sys.request.SysServiceDelRequest;
import io.renren.modules.sys.entity.SysServiceEntity;
import io.renren.modules.sys.service.SysServiceService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 系统服务
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-07 11:00:37
 */
@Slf4j
@Api(tags = "系统服务")
@RestController
@RequestMapping("/sys/sysservice")
public class SysServiceController extends AbstractController {
    @Autowired
    private SysServiceService sysServiceService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统服务-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_service",
            authoritiesCnName = "系统服务权限",
            name = "sys_sysservice_list",
            cnName = "系统服务-列表",
            describe = "系统服务-列表",
            authzCode = "sys:sysservice:list"
    )
    @AuthzCode(authzCode = "sys:sysservice:list")
    public R list(@Valid @RequestBody SysServicePageRequest request) {
        Map page = sysServiceService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统服务-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_service",
            authoritiesCnName = "系统服务权限",
            name = "sys_sysservice_info",
            cnName = "系统服务-信息",
            describe = "系统服务-信息",
            authzCode = "sys:sysservice:info"
    )
    @AuthzCode(authzCode = "sys:sysservice:info")
    public R info(@Valid @RequestBody SysServiceInfoRequest request) {
        SysServiceEntity sysService = sysServiceService.getById(request.getId());

        return R.ok().put("data", sysService);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统服务-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_service",
            authoritiesCnName = "系统服务权限",
            name = "sys_sysservice_save",
            cnName = "系统服务-保存",
            describe = "系统服务-保存",
            authzCode = "sys:sysservice:save"
    )
    @AuthzCode(authzCode = "sys:sysservice:save")
    public R save(@Valid @RequestBody SysServiceSaveRequest request) {

        SysServiceEntity entity = new SysServiceEntity();

        entity.setName(request.getName());
        entity.setCnName(request.getCnName());
        entity.setIcon(request.getIcon());
        entity.setContent(request.getContent());
        entity.setAuthzCode(request.getAuthzCode());
        entity.setPerms(request.getPerms());
        entity.setIsShow(request.getIsShow());
        entity.setStartUpType(request.getStartUpType());
        entity.setState(request.getState());
        entity.setMethod(request.getMethod());

        sysServiceService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统服务-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_service",
            authoritiesCnName = "系统服务权限",
            name = "sys_sysservice_update",
            cnName = "系统服务-修改",
            describe = "系统服务-修改",
            authzCode = "sys:sysservice:update"
    )
    @AuthzCode(authzCode = "sys:sysservice:update")
    public R update(@Valid @RequestBody SysServiceUpdateRequest request) {
        SysServiceEntity entity = new SysServiceEntity();


        entity.setId(request.getId());
        entity.setName(request.getName());
        entity.setCnName(request.getCnName());
        entity.setIcon(request.getIcon());
        entity.setContent(request.getContent());
        entity.setAuthzCode(request.getAuthzCode());
        entity.setPerms(request.getPerms());
        entity.setIsShow(request.getIsShow());
        entity.setStartUpType(request.getStartUpType());
        entity.setState(request.getState());
        entity.setMethod(request.getMethod());

        sysServiceService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统服务-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_service",
            authoritiesCnName = "系统服务权限",
            name = "sys_sysservice_delete",
            cnName = "系统服务-删除",
            describe = "系统服务-删除",
            authzCode = "sys:sysservice:delete"
    )
    @AuthzCode(authzCode = "sys:sysservice:delete")
    public R delete(@Valid @RequestBody SysServiceDelRequest request) {
        sysServiceService.removeById(request.getId());
        return R.ok();
    }

}
