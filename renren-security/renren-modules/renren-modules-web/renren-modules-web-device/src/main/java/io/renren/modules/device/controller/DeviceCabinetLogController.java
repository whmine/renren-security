package io.renren.modules.device.controller;

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

import io.renren.modules.device.request.DeviceCabinetLogPageRequest;
import io.renren.modules.device.request.DeviceCabinetLogInfoRequest;
import io.renren.modules.device.request.DeviceCabinetLogSaveRequest;
import io.renren.modules.device.request.DeviceCabinetLogUpdateRequest;
import io.renren.modules.device.request.DeviceCabinetLogDelRequest;
import io.renren.modules.device.request.DeviceCabinetLogPushRequest;
import io.renren.modules.device.entity.DeviceCabinetLogEntity;
import io.renren.modules.device.service.DeviceCabinetLogService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 机柜租借
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
@Slf4j
@Api(tags = "机柜租借")
@RestController
@RequestMapping("/device/devicecabinetlog")
public class DeviceCabinetLogController extends AbstractController {
    @Autowired
    private DeviceCabinetLogService deviceCabinetLogService;

    /**
     * 列表
     */
    @Login
    @SysLog("机柜租借-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_cabinet_log",
            authoritiesCnName = "机柜租借权限",
            name = "device_devicecabinetlog_list",
            cnName = "机柜租借-列表",
            describe = "机柜租借-列表",
            authzCode = "device:devicecabinetlog:list"
    )
    @AuthzCode(authzCode = "device:devicecabinetlog:list")
    public R list(@Valid @RequestBody DeviceCabinetLogPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = deviceCabinetLogService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("机柜租借-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_cabinet_log",
            authoritiesCnName = "机柜租借权限",
            name = "device_devicecabinetlog_info",
            cnName = "机柜租借-信息",
            describe = "机柜租借-信息",
            authzCode = "device:devicecabinetlog:info"
    )
    @AuthzCode(authzCode = "device:devicecabinetlog:info")
    public R info(@Valid @RequestBody DeviceCabinetLogInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            DeviceCabinetLogEntity deviceCabinetLog = deviceCabinetLogService.getById(request.getId());

        return R.ok().put("data", deviceCabinetLog);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("机柜租借-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_cabinet_log",
            authoritiesCnName = "机柜租借权限",
            name = "device_devicecabinetlog_save",
            cnName = "机柜租借-保存",
            describe = "机柜租借-保存",
            authzCode = "device:devicecabinetlog:save"
    )
    @AuthzCode(authzCode = "device:devicecabinetlog:save")
    public R save(@Valid @RequestBody DeviceCabinetLogSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            DeviceCabinetLogEntity entity = new DeviceCabinetLogEntity();

                                                        entity.setCabinetId(request.getCabinetId());
                                                entity.setQrCode(request.getQrCode());
                                                entity.setStartTime(request.getStartTime());
                                                entity.setEndTime(request.getEndTime());
                                                                                                                                                                                                        
            deviceCabinetLogService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("机柜租借-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_cabinet_log",
            authoritiesCnName = "机柜租借权限",
            name = "device_devicecabinetlog_update",
            cnName = "机柜租借-修改",
            describe = "机柜租借-修改",
            authzCode = "device:devicecabinetlog:update"
    )
    @AuthzCode(authzCode = "device:devicecabinetlog:update")
    public R update(@Valid @RequestBody DeviceCabinetLogUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            DeviceCabinetLogEntity entity = new DeviceCabinetLogEntity();



                                    entity.setId(request.getId());
                                                entity.setCabinetId(request.getCabinetId());
                                                entity.setQrCode(request.getQrCode());
                                                entity.setStartTime(request.getStartTime());
                                                entity.setEndTime(request.getEndTime());
                                                                                                                                                                                                        
            deviceCabinetLogService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("机柜租借-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_cabinet_log",
            authoritiesCnName = "机柜租借权限",
            name = "device_devicecabinetlog_delete",
            cnName = "机柜租借-删除",
            describe = "机柜租借-删除",
            authzCode = "device:devicecabinetlog:delete"
    )
    @AuthzCode(authzCode = "device:devicecabinetlog:delete")
    public R delete(@Valid @RequestBody DeviceCabinetLogDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        deviceCabinetLogService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("机柜租借-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_cabinet_log",
            authoritiesCnName = "机柜租借权限",
            name = "device_devicecabinetlog_push",
            cnName = "机柜租借-发布redis",
            describe = "机柜租借-发布redis",
            authzCode = "device:devicecabinetlog:push"
    )
    @AuthzCode(authzCode = "device:devicecabinetlog:push")
    public R push(@Valid @RequestBody DeviceCabinetLogPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        deviceCabinetLogService.push(request);
        return R.ok();
    }

}
