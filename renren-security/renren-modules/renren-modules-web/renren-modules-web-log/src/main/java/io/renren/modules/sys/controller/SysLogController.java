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

import io.renren.modules.sys.request.SysLogPageRequest;
import io.renren.modules.sys.request.SysLogInfoRequest;
import io.renren.modules.sys.request.SysLogSaveRequest;
import io.renren.modules.sys.request.SysLogUpdateRequest;
import io.renren.modules.sys.request.SysLogDelRequest;
import io.renren.modules.sys.entity.SysLogEntity;
import io.renren.modules.sys.service.SysLogService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 系统日志
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-07 10:33:14
 */
@Slf4j
@Api(tags = "系统日志")
@RestController
@RequestMapping("/sys/syslog")
public class SysLogController extends AbstractController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 列表
     */
    @Login
    @SysLog("系统日志-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_log",
            authoritiesCnName = "系统日志权限",
            name = "sys_syslog_list",
            cnName = "系统日志-列表",
            describe = "系统日志-列表",
            authzCode = "sys:syslog:list"
    )
    @AuthzCode(authzCode = "sys:syslog:list")
    public R list(@Valid @RequestBody SysLogPageRequest request) {
        Map page = sysLogService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("系统日志-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_log",
            authoritiesCnName = "系统日志权限",
            name = "sys_syslog_info",
            cnName = "系统日志-信息",
            describe = "系统日志-信息",
            authzCode = "sys:syslog:info"
    )
    @AuthzCode(authzCode = "sys:syslog:info")
    public R info(@Valid @RequestBody SysLogInfoRequest request) {
            SysLogEntity sysLog = sysLogService.getById(request.getId());

        return R.ok().put("data", sysLog);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("系统日志-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_log",
            authoritiesCnName = "系统日志权限",
            name = "sys_syslog_save",
            cnName = "系统日志-保存",
            describe = "系统日志-保存",
            authzCode = "sys:syslog:save"
    )
    @AuthzCode(authzCode = "sys:syslog:save")
    public R save(@Valid @RequestBody SysLogSaveRequest request) {

            SysLogEntity entity = new SysLogEntity();

                                                        entity.setGrade(request.getGrade());
                                                entity.setSysExceptionId(request.getSysExceptionId());
                                                entity.setIp(request.getIp());
                                                entity.setText(request.getText());
                                                entity.setServiceId(request.getServiceId());
                                                entity.setOperation(request.getOperation());
                                                entity.setMethod(request.getMethod());
                                                                                                                                                                                                        
            sysLogService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("系统日志-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_log",
            authoritiesCnName = "系统日志权限",
            name = "sys_syslog_update",
            cnName = "系统日志-修改",
            describe = "系统日志-修改",
            authzCode = "sys:syslog:update"
    )
    @AuthzCode(authzCode = "sys:syslog:update")
    public R update(@Valid @RequestBody SysLogUpdateRequest request) {
            SysLogEntity entity = new SysLogEntity();



                                    entity.setId(request.getId());
                                                entity.setGrade(request.getGrade());
                                                entity.setSysExceptionId(request.getSysExceptionId());
                                                entity.setIp(request.getIp());
                                                entity.setText(request.getText());
                                                entity.setServiceId(request.getServiceId());
                                                entity.setOperation(request.getOperation());
                                                entity.setMethod(request.getMethod());
                                                                                                                                                                                                        
            sysLogService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("系统日志-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SYSTEM",
            authoritiesName = "sys_log",
            authoritiesCnName = "系统日志权限",
            name = "sys_syslog_delete",
            cnName = "系统日志-删除",
            describe = "系统日志-删除",
            authzCode = "sys:syslog:delete"
    )
    @AuthzCode(authzCode = "sys:syslog:delete")
    public R delete(@Valid @RequestBody SysLogDelRequest request) {
            sysLogService.removeById(request.getId());
        return R.ok();
    }

}
