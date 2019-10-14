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

import io.renren.modules.sys.request.SysResourcesPageRequest;
import io.renren.modules.sys.request.SysResourcesInfoRequest;
import io.renren.modules.sys.request.SysResourcesSaveRequest;
import io.renren.modules.sys.request.SysResourcesUpdateRequest;
import io.renren.modules.sys.request.SysResourcesDelRequest;
import io.renren.modules.sys.request.SysResourcesPushRequest;
import io.renren.modules.sys.entity.SysResourcesEntity;
import io.renren.modules.sys.service.SysResourcesService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 系统资源
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-21 09:55:54
 */
@Slf4j
@Api(tags = "系统资源")
@RestController
@RequestMapping("/sys/sysresources")
public class SysResourcesController extends AbstractController {
    @Autowired
    private SysResourcesService sysResourcesService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统资源-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_resources",
            authoritiesCnName = "系统资源权限",
            name = "sys_sysresources_list",
            cnName = "系统资源-列表",
            describe = "系统资源-列表",
            authzCode = "sys:sysresources:list"
    )
    @AuthzCode(authzCode = "sys:sysresources:list")
    public R list(@Valid @RequestBody SysResourcesPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = sysResourcesService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统资源-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_resources",
            authoritiesCnName = "系统资源权限",
            name = "sys_sysresources_info",
            cnName = "系统资源-信息",
            describe = "系统资源-信息",
            authzCode = "sys:sysresources:info"
    )
    @AuthzCode(authzCode = "sys:sysresources:info")
    public R info(@Valid @RequestBody SysResourcesInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            SysResourcesEntity sysResources = sysResourcesService.getById(request.getId());

        return R.ok().put("data", sysResources);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统资源-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_resources",
            authoritiesCnName = "系统资源权限",
            name = "sys_sysresources_save",
            cnName = "系统资源-保存",
            describe = "系统资源-保存",
            authzCode = "sys:sysresources:save"
    )
    @AuthzCode(authzCode = "sys:sysresources:save")
    public R save(@Valid @RequestBody SysResourcesSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            SysResourcesEntity entity = new SysResourcesEntity();

                                                        entity.setName(request.getName());
                                                entity.setCnName(request.getCnName());
                                                entity.setValue(request.getValue());
                                                entity.setType(request.getType());
                                                entity.setIsSys(request.getIsSys());
                                                                                                                                                                                                        
            sysResourcesService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统资源-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_resources",
            authoritiesCnName = "系统资源权限",
            name = "sys_sysresources_update",
            cnName = "系统资源-修改",
            describe = "系统资源-修改",
            authzCode = "sys:sysresources:update"
    )
    @AuthzCode(authzCode = "sys:sysresources:update")
    public R update(@Valid @RequestBody SysResourcesUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            SysResourcesEntity entity = new SysResourcesEntity();



                                    entity.setId(request.getId());
                                                entity.setName(request.getName());
                                                entity.setCnName(request.getCnName());
                                                entity.setValue(request.getValue());
                                                entity.setType(request.getType());
                                                entity.setIsSys(request.getIsSys());
                                                                                                                                                                                                        
            sysResourcesService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统资源-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_resources",
            authoritiesCnName = "系统资源权限",
            name = "sys_sysresources_delete",
            cnName = "系统资源-删除",
            describe = "系统资源-删除",
            authzCode = "sys:sysresources:delete"
    )
    @AuthzCode(authzCode = "sys:sysresources:delete")
    public R delete(@Valid @RequestBody SysResourcesDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysResourcesService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("系统资源-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_resources",
            authoritiesCnName = "系统资源权限",
            name = "sys_sysresources_push",
            cnName = "系统资源-发布redis",
            describe = "系统资源-发布redis",
            authzCode = "sys:sysresources:push"
    )
    @AuthzCode(authzCode = "sys:sysresources:push")
    public R push(@Valid @RequestBody SysResourcesPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        sysResourcesService.push(request);
        return R.ok();
    }

}
