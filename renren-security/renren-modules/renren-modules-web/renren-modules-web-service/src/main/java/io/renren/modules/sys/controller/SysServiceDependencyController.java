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

import io.renren.modules.sys.request.SysServiceDependencyPageRequest;
import io.renren.modules.sys.request.SysServiceDependencyInfoRequest;
import io.renren.modules.sys.request.SysServiceDependencySaveRequest;
import io.renren.modules.sys.request.SysServiceDependencyUpdateRequest;
import io.renren.modules.sys.request.SysServiceDependencyDelRequest;
import io.renren.modules.sys.entity.SysServiceDependencyEntity;
import io.renren.modules.sys.service.SysServiceDependencyService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 系统服务依赖
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-07 11:00:37
 */
@Slf4j
@Api(tags = "系统服务依赖")
@RestController
@RequestMapping("/sys/sysservicedependency")
public class SysServiceDependencyController extends AbstractController {
    @Autowired
    private SysServiceDependencyService sysServiceDependencyService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统服务依赖-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_service_dependency",
            authoritiesCnName = "系统服务依赖权限",
            name = "sys_sysservicedependency_list",
            cnName = "系统服务依赖-列表",
            describe = "系统服务依赖-列表",
            authzCode = "sys:sysservicedependency:list"
    )
    @AuthzCode(authzCode = "sys:sysservicedependency:list")
    public R list(@Valid @RequestBody SysServiceDependencyPageRequest request) {
        Map page = sysServiceDependencyService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统服务依赖-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_service_dependency",
            authoritiesCnName = "系统服务依赖权限",
            name = "sys_sysservicedependency_info",
            cnName = "系统服务依赖-信息",
            describe = "系统服务依赖-信息",
            authzCode = "sys:sysservicedependency:info"
    )
    @AuthzCode(authzCode = "sys:sysservicedependency:info")
    public R info(@Valid @RequestBody SysServiceDependencyInfoRequest request) {
        SysServiceDependencyEntity sysServiceDependency = sysServiceDependencyService.getById(request.getId());

        return R.ok().put("data", sysServiceDependency);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统服务依赖-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_service_dependency",
            authoritiesCnName = "系统服务依赖权限",
            name = "sys_sysservicedependency_save",
            cnName = "系统服务依赖-保存",
            describe = "系统服务依赖-保存",
            authzCode = "sys:sysservicedependency:save"
    )
    @AuthzCode(authzCode = "sys:sysservicedependency:save")
    public R save(@Valid @RequestBody SysServiceDependencySaveRequest request) {

        SysServiceDependencyEntity entity = new SysServiceDependencyEntity();

        entity.setSysServiceId(request.getSysServiceId());
        entity.setSysServiceDependencyId(request.getSysServiceDependencyId());

        sysServiceDependencyService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统服务依赖-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_service_dependency",
            authoritiesCnName = "系统服务依赖权限",
            name = "sys_sysservicedependency_update",
            cnName = "系统服务依赖-修改",
            describe = "系统服务依赖-修改",
            authzCode = "sys:sysservicedependency:update"
    )
    @AuthzCode(authzCode = "sys:sysservicedependency:update")
    public R update(@Valid @RequestBody SysServiceDependencyUpdateRequest request) {
        SysServiceDependencyEntity entity = new SysServiceDependencyEntity();


        entity.setId(request.getId());
        entity.setSysServiceId(request.getSysServiceId());
        entity.setSysServiceDependencyId(request.getSysServiceDependencyId());

        sysServiceDependencyService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统服务依赖-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_service_dependency",
            authoritiesCnName = "系统服务依赖权限",
            name = "sys_sysservicedependency_delete",
            cnName = "系统服务依赖-删除",
            describe = "系统服务依赖-删除",
            authzCode = "sys:sysservicedependency:delete"
    )
    @AuthzCode(authzCode = "sys:sysservicedependency:delete")
    public R delete(@Valid @RequestBody SysServiceDependencyDelRequest request) {
        sysServiceDependencyService.removeById(request.getId());
        return R.ok();
    }

}
