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

import io.renren.modules.device.request.DeviceLinePageRequest;
import io.renren.modules.device.request.DeviceLineInfoRequest;
import io.renren.modules.device.request.DeviceLineSaveRequest;
import io.renren.modules.device.request.DeviceLineUpdateRequest;
import io.renren.modules.device.request.DeviceLineDelRequest;
import io.renren.modules.device.request.DeviceLinePushRequest;
import io.renren.modules.device.entity.DeviceLineEntity;
import io.renren.modules.device.service.DeviceLineService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 密码线
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
@Slf4j
@Api(tags = "密码线")
@RestController
@RequestMapping("/device/deviceline")
public class DeviceLineController extends AbstractController {
    @Autowired
    private DeviceLineService deviceLineService;

    /**
     * 列表
     */
    @Login
    @SysLog("密码线-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_line",
            authoritiesCnName = "密码线权限",
            name = "device_deviceline_list",
            cnName = "密码线-列表",
            describe = "密码线-列表",
            authzCode = "device:deviceline:list"
    )
    @AuthzCode(authzCode = "device:deviceline:list")
    public R list(@Valid @RequestBody DeviceLinePageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = deviceLineService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("密码线-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_line",
            authoritiesCnName = "密码线权限",
            name = "device_deviceline_info",
            cnName = "密码线-信息",
            describe = "密码线-信息",
            authzCode = "device:deviceline:info"
    )
    @AuthzCode(authzCode = "device:deviceline:info")
    public R info(@Valid @RequestBody DeviceLineInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            DeviceLineEntity deviceLine = deviceLineService.getById(request.getId());

        return R.ok().put("data", deviceLine);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("密码线-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_line",
            authoritiesCnName = "密码线权限",
            name = "device_deviceline_save",
            cnName = "密码线-保存",
            describe = "密码线-保存",
            authzCode = "device:deviceline:save"
    )
    @AuthzCode(authzCode = "device:deviceline:save")
    public R save(@Valid @RequestBody DeviceLineSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            DeviceLineEntity entity = new DeviceLineEntity();

                                                        entity.setLineSn(request.getLineSn());
                                                entity.setQrCode(request.getQrCode());
                                                entity.setAgent(request.getAgent());
                                                entity.setBus(request.getBus());
                                                entity.setPos(request.getPos());
                                                entity.setLease(request.getLease());
                                                entity.setState(request.getState());
                                                                                                                                                                                                        
            deviceLineService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("密码线-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_line",
            authoritiesCnName = "密码线权限",
            name = "device_deviceline_update",
            cnName = "密码线-修改",
            describe = "密码线-修改",
            authzCode = "device:deviceline:update"
    )
    @AuthzCode(authzCode = "device:deviceline:update")
    public R update(@Valid @RequestBody DeviceLineUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            DeviceLineEntity entity = new DeviceLineEntity();



                                    entity.setId(request.getId());
                                                entity.setLineSn(request.getLineSn());
                                                entity.setQrCode(request.getQrCode());
                                                entity.setAgent(request.getAgent());
                                                entity.setBus(request.getBus());
                                                entity.setPos(request.getPos());
                                                entity.setLease(request.getLease());
                                                entity.setState(request.getState());
                                                                                                                                                                                                        
            deviceLineService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("密码线-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_line",
            authoritiesCnName = "密码线权限",
            name = "device_deviceline_delete",
            cnName = "密码线-删除",
            describe = "密码线-删除",
            authzCode = "device:deviceline:delete"
    )
    @AuthzCode(authzCode = "device:deviceline:delete")
    public R delete(@Valid @RequestBody DeviceLineDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        deviceLineService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("密码线-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_line",
            authoritiesCnName = "密码线权限",
            name = "device_deviceline_push",
            cnName = "密码线-发布redis",
            describe = "密码线-发布redis",
            authzCode = "device:deviceline:push"
    )
    @AuthzCode(authzCode = "device:deviceline:push")
    public R push(@Valid @RequestBody DeviceLinePushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        deviceLineService.push(request);
        return R.ok();
    }

}
