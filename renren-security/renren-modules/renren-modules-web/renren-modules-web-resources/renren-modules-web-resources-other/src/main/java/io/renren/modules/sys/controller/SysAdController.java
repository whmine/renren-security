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

import io.renren.modules.sys.request.SysAdPageRequest;
import io.renren.modules.sys.request.SysAdInfoRequest;
import io.renren.modules.sys.request.SysAdSaveRequest;
import io.renren.modules.sys.request.SysAdUpdateRequest;
import io.renren.modules.sys.request.SysAdDelRequest;
import io.renren.modules.sys.entity.SysAdEntity;
import io.renren.modules.sys.service.SysAdService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 广告轮播图
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-07 10:49:21
 */
@Slf4j
@Api(tags = "广告轮播图")
@RestController
@RequestMapping("/sys/sysad")
public class SysAdController extends AbstractController {
    @Autowired
    private SysAdService sysAdService;

    /**
     * 列表
     */
    @Login
    @SysLog("广告轮播图-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_ad",
            authoritiesCnName = "广告轮播图权限",
            name = "sys_sysad_list",
            cnName = "广告轮播图-列表",
            describe = "广告轮播图-列表",
            authzCode = "sys:sysad:list"
    )
    @AuthzCode(authzCode = "sys:sysad:list")
    public R list(@Valid @RequestBody SysAdPageRequest request) {
        Map page = sysAdService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("广告轮播图-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_ad",
            authoritiesCnName = "广告轮播图权限",
            name = "sys_sysad_info",
            cnName = "广告轮播图-信息",
            describe = "广告轮播图-信息",
            authzCode = "sys:sysad:info"
    )
    @AuthzCode(authzCode = "sys:sysad:info")
    public R info(@Valid @RequestBody SysAdInfoRequest request) {
        SysAdEntity sysAd = sysAdService.getById(request.getId());

        return R.ok().put("data", sysAd);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("广告轮播图-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_ad",
            authoritiesCnName = "广告轮播图权限",
            name = "sys_sysad_save",
            cnName = "广告轮播图-保存",
            describe = "广告轮播图-保存",
            authzCode = "sys:sysad:save"
    )
    @AuthzCode(authzCode = "sys:sysad:save")
    public R save(@Valid @RequestBody SysAdSaveRequest request) {

        SysAdEntity entity = new SysAdEntity();

        entity.setAdName(request.getAdName());
        entity.setAdLink(request.getAdLink());
        entity.setAdCode(request.getAdCode());
        entity.setType(request.getType());
        entity.setLinkPhone(request.getLinkPhone());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setClickCount(request.getClickCount());
        entity.setEnabled(request.getEnabled());

        sysAdService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("广告轮播图-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_ad",
            authoritiesCnName = "广告轮播图权限",
            name = "sys_sysad_update",
            cnName = "广告轮播图-修改",
            describe = "广告轮播图-修改",
            authzCode = "sys:sysad:update"
    )
    @AuthzCode(authzCode = "sys:sysad:update")
    public R update(@Valid @RequestBody SysAdUpdateRequest request) {
        SysAdEntity entity = new SysAdEntity();


        entity.setId(request.getId());
        entity.setAdName(request.getAdName());
        entity.setAdLink(request.getAdLink());
        entity.setAdCode(request.getAdCode());
        entity.setType(request.getType());
        entity.setLinkPhone(request.getLinkPhone());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setClickCount(request.getClickCount());
        entity.setEnabled(request.getEnabled());

        sysAdService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("广告轮播图-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_ad",
            authoritiesCnName = "广告轮播图权限",
            name = "sys_sysad_delete",
            cnName = "广告轮播图-删除",
            describe = "广告轮播图-删除",
            authzCode = "sys:sysad:delete"
    )
    @AuthzCode(authzCode = "sys:sysad:delete")
    public R delete(@Valid @RequestBody SysAdDelRequest request) {
        sysAdService.removeById(request.getId());
        return R.ok();
    }

}
