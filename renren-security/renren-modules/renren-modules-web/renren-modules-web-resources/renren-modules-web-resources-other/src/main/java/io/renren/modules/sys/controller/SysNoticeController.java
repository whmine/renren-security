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

import io.renren.modules.sys.request.SysNoticePageRequest;
import io.renren.modules.sys.request.SysNoticeInfoRequest;
import io.renren.modules.sys.request.SysNoticeSaveRequest;
import io.renren.modules.sys.request.SysNoticeUpdateRequest;
import io.renren.modules.sys.request.SysNoticeDelRequest;
import io.renren.modules.sys.entity.SysNoticeEntity;
import io.renren.modules.sys.service.SysNoticeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 系统公告表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-07 10:49:21
 */
@Slf4j
@Api(tags = "系统公告表")
@RestController
@RequestMapping("/sys/sysnotice")
public class SysNoticeController extends AbstractController {
    @Autowired
    private SysNoticeService sysNoticeService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统公告表-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_notice",
            authoritiesCnName = "系统公告表权限",
            name = "sys_sysnotice_list",
            cnName = "系统公告表-列表",
            describe = "系统公告表-列表",
            authzCode = "sys:sysnotice:list"
    )
    @AuthzCode(authzCode = "sys:sysnotice:list")
    public R list(@Valid @RequestBody SysNoticePageRequest request) {
        Map page = sysNoticeService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统公告表-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_notice",
            authoritiesCnName = "系统公告表权限",
            name = "sys_sysnotice_info",
            cnName = "系统公告表-信息",
            describe = "系统公告表-信息",
            authzCode = "sys:sysnotice:info"
    )
    @AuthzCode(authzCode = "sys:sysnotice:info")
    public R info(@Valid @RequestBody SysNoticeInfoRequest request) {
            SysNoticeEntity sysNotice = sysNoticeService.getById(request.getId());

        return R.ok().put("data", sysNotice);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统公告表-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_notice",
            authoritiesCnName = "系统公告表权限",
            name = "sys_sysnotice_save",
            cnName = "系统公告表-保存",
            describe = "系统公告表-保存",
            authzCode = "sys:sysnotice:save"
    )
    @AuthzCode(authzCode = "sys:sysnotice:save")
    public R save(@Valid @RequestBody SysNoticeSaveRequest request) {

            SysNoticeEntity entity = new SysNoticeEntity();

                                                        entity.setContent(request.getContent());
                                                entity.setTname(request.getTname());
                                                entity.setCementUrl(request.getCementUrl());
                                                entity.setReadNum(request.getReadNum());
                                                                                                                                                                                                        
            sysNoticeService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统公告表-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_notice",
            authoritiesCnName = "系统公告表权限",
            name = "sys_sysnotice_update",
            cnName = "系统公告表-修改",
            describe = "系统公告表-修改",
            authzCode = "sys:sysnotice:update"
    )
    @AuthzCode(authzCode = "sys:sysnotice:update")
    public R update(@Valid @RequestBody SysNoticeUpdateRequest request) {
            SysNoticeEntity entity = new SysNoticeEntity();



                                    entity.setId(request.getId());
                                                entity.setContent(request.getContent());
                                                entity.setTname(request.getTname());
                                                entity.setCementUrl(request.getCementUrl());
                                                entity.setReadNum(request.getReadNum());
                                                                                                                                                                                                        
            sysNoticeService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统公告表-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_notice",
            authoritiesCnName = "系统公告表权限",
            name = "sys_sysnotice_delete",
            cnName = "系统公告表-删除",
            describe = "系统公告表-删除",
            authzCode = "sys:sysnotice:delete"
    )
    @AuthzCode(authzCode = "sys:sysnotice:delete")
    public R delete(@Valid @RequestBody SysNoticeDelRequest request) {
            sysNoticeService.removeById(request.getId());
        return R.ok();
    }

}
