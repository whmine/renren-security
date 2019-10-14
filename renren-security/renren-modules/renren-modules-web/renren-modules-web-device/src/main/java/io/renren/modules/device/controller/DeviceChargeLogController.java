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

import io.renren.modules.device.request.DeviceChargeLogPageRequest;
import io.renren.modules.device.request.DeviceChargeLogInfoRequest;
import io.renren.modules.device.request.DeviceChargeLogSaveRequest;
import io.renren.modules.device.request.DeviceChargeLogUpdateRequest;
import io.renren.modules.device.request.DeviceChargeLogDelRequest;
import io.renren.modules.device.request.DeviceChargeLogPushRequest;
import io.renren.modules.device.entity.DeviceChargeLogEntity;
import io.renren.modules.device.service.DeviceChargeLogService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 充电宝租借
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
@Slf4j
@Api(tags = "充电宝租借")
@RestController
@RequestMapping("/device/devicechargelog")
public class DeviceChargeLogController extends AbstractController {
    @Autowired
    private DeviceChargeLogService deviceChargeLogService;

    /**
     * 列表
     */
    @Login
    @SysLog("充电宝租借-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_charge_log",
            authoritiesCnName = "充电宝租借权限",
            name = "device_devicechargelog_list",
            cnName = "充电宝租借-列表",
            describe = "充电宝租借-列表",
            authzCode = "device:devicechargelog:list"
    )
    @AuthzCode(authzCode = "device:devicechargelog:list")
    public R list(@Valid @RequestBody DeviceChargeLogPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = deviceChargeLogService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("充电宝租借-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_charge_log",
            authoritiesCnName = "充电宝租借权限",
            name = "device_devicechargelog_info",
            cnName = "充电宝租借-信息",
            describe = "充电宝租借-信息",
            authzCode = "device:devicechargelog:info"
    )
    @AuthzCode(authzCode = "device:devicechargelog:info")
    public R info(@Valid @RequestBody DeviceChargeLogInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            DeviceChargeLogEntity deviceChargeLog = deviceChargeLogService.getById(request.getId());

        return R.ok().put("data", deviceChargeLog);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("充电宝租借-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_charge_log",
            authoritiesCnName = "充电宝租借权限",
            name = "device_devicechargelog_save",
            cnName = "充电宝租借-保存",
            describe = "充电宝租借-保存",
            authzCode = "device:devicechargelog:save"
    )
    @AuthzCode(authzCode = "device:devicechargelog:save")
    public R save(@Valid @RequestBody DeviceChargeLogSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            DeviceChargeLogEntity entity = new DeviceChargeLogEntity();

                                                        entity.setChargeId(request.getChargeId());
                                                entity.setStartTime(request.getStartTime());
                                                entity.setEndTime(request.getEndTime());
                                                                                                                                                                                                        
            deviceChargeLogService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("充电宝租借-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_charge_log",
            authoritiesCnName = "充电宝租借权限",
            name = "device_devicechargelog_update",
            cnName = "充电宝租借-修改",
            describe = "充电宝租借-修改",
            authzCode = "device:devicechargelog:update"
    )
    @AuthzCode(authzCode = "device:devicechargelog:update")
    public R update(@Valid @RequestBody DeviceChargeLogUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            DeviceChargeLogEntity entity = new DeviceChargeLogEntity();



                                    entity.setId(request.getId());
                                                entity.setChargeId(request.getChargeId());
                                                entity.setStartTime(request.getStartTime());
                                                entity.setEndTime(request.getEndTime());
                                                                                                                                                                                                        
            deviceChargeLogService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("充电宝租借-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_charge_log",
            authoritiesCnName = "充电宝租借权限",
            name = "device_devicechargelog_delete",
            cnName = "充电宝租借-删除",
            describe = "充电宝租借-删除",
            authzCode = "device:devicechargelog:delete"
    )
    @AuthzCode(authzCode = "device:devicechargelog:delete")
    public R delete(@Valid @RequestBody DeviceChargeLogDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        deviceChargeLogService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("充电宝租借-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_charge_log",
            authoritiesCnName = "充电宝租借权限",
            name = "device_devicechargelog_push",
            cnName = "充电宝租借-发布redis",
            describe = "充电宝租借-发布redis",
            authzCode = "device:devicechargelog:push"
    )
    @AuthzCode(authzCode = "device:devicechargelog:push")
    public R push(@Valid @RequestBody DeviceChargeLogPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        deviceChargeLogService.push(request);
        return R.ok();
    }

}
