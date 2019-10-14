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

import io.renren.modules.device.request.DeviceCabinetPageRequest;
import io.renren.modules.device.request.DeviceCabinetInfoRequest;
import io.renren.modules.device.request.DeviceCabinetSaveRequest;
import io.renren.modules.device.request.DeviceCabinetUpdateRequest;
import io.renren.modules.device.request.DeviceCabinetDelRequest;
import io.renren.modules.device.request.DeviceCabinetPushRequest;
import io.renren.modules.device.entity.DeviceCabinetEntity;
import io.renren.modules.device.service.DeviceCabinetService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 机柜
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
@Slf4j
@Api(tags = "机柜")
@RestController
@RequestMapping("/device/devicecabinet")
public class DeviceCabinetController extends AbstractController {
    @Autowired
    private DeviceCabinetService deviceCabinetService;

    /**
     * 列表
     */
    @Login
    @SysLog("机柜-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_cabinet",
            authoritiesCnName = "机柜权限",
            name = "device_devicecabinet_list",
            cnName = "机柜-列表",
            describe = "机柜-列表",
            authzCode = "device:devicecabinet:list"
    )
    @AuthzCode(authzCode = "device:devicecabinet:list")
    public R list(@Valid @RequestBody DeviceCabinetPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = deviceCabinetService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("机柜-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_cabinet",
            authoritiesCnName = "机柜权限",
            name = "device_devicecabinet_info",
            cnName = "机柜-信息",
            describe = "机柜-信息",
            authzCode = "device:devicecabinet:info"
    )
    @AuthzCode(authzCode = "device:devicecabinet:info")
    public R info(@Valid @RequestBody DeviceCabinetInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            DeviceCabinetEntity deviceCabinet = deviceCabinetService.getById(request.getId());

        return R.ok().put("data", deviceCabinet);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("机柜-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_cabinet",
            authoritiesCnName = "机柜权限",
            name = "device_devicecabinet_save",
            cnName = "机柜-保存",
            describe = "机柜-保存",
            authzCode = "device:devicecabinet:save"
    )
    @AuthzCode(authzCode = "device:devicecabinet:save")
    public R save(@Valid @RequestBody DeviceCabinetSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            DeviceCabinetEntity entity = new DeviceCabinetEntity();

                                                        entity.setCabinetSn(request.getCabinetSn());
                                                entity.setQrCode(request.getQrCode());
                                                entity.setDeviceType(request.getDeviceType());
                                                entity.setDeviceNum(request.getDeviceNum());
                                                entity.setAgent(request.getAgent());
                                                entity.setBus(request.getBus());
                                                entity.setPos(request.getPos());
                                                entity.setLease(request.getLease());
                                                entity.setState(request.getState());
                                                                                                                                                                                                        
            deviceCabinetService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("机柜-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_cabinet",
            authoritiesCnName = "机柜权限",
            name = "device_devicecabinet_update",
            cnName = "机柜-修改",
            describe = "机柜-修改",
            authzCode = "device:devicecabinet:update"
    )
    @AuthzCode(authzCode = "device:devicecabinet:update")
    public R update(@Valid @RequestBody DeviceCabinetUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            DeviceCabinetEntity entity = new DeviceCabinetEntity();



                                    entity.setId(request.getId());
                                                entity.setCabinetSn(request.getCabinetSn());
                                                entity.setQrCode(request.getQrCode());
                                                entity.setDeviceType(request.getDeviceType());
                                                entity.setDeviceNum(request.getDeviceNum());
                                                entity.setAgent(request.getAgent());
                                                entity.setBus(request.getBus());
                                                entity.setPos(request.getPos());
                                                entity.setLease(request.getLease());
                                                entity.setState(request.getState());
                                                                                                                                                                                                        
            deviceCabinetService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("机柜-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_cabinet",
            authoritiesCnName = "机柜权限",
            name = "device_devicecabinet_delete",
            cnName = "机柜-删除",
            describe = "机柜-删除",
            authzCode = "device:devicecabinet:delete"
    )
    @AuthzCode(authzCode = "device:devicecabinet:delete")
    public R delete(@Valid @RequestBody DeviceCabinetDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        deviceCabinetService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("机柜-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_cabinet",
            authoritiesCnName = "机柜权限",
            name = "device_devicecabinet_push",
            cnName = "机柜-发布redis",
            describe = "机柜-发布redis",
            authzCode = "device:devicecabinet:push"
    )
    @AuthzCode(authzCode = "device:devicecabinet:push")
    public R push(@Valid @RequestBody DeviceCabinetPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        deviceCabinetService.push(request);
        return R.ok();
    }

}
