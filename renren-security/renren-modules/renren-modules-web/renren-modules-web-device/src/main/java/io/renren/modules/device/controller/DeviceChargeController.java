package io.renren.modules.device.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.utils.PayUtil;
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

import io.renren.modules.device.request.DeviceChargePageRequest;
import io.renren.modules.device.request.DeviceChargeInfoRequest;
import io.renren.modules.device.request.DeviceChargeSaveRequest;
import io.renren.modules.device.request.DeviceChargeUpdateRequest;
import io.renren.modules.device.request.DeviceChargeDelRequest;
import io.renren.modules.device.request.DeviceChargePushRequest;
import io.renren.modules.device.entity.DeviceChargeEntity;
import io.renren.modules.device.service.DeviceChargeService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 充电宝
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 10:46:56
 */
@Slf4j
@Api(tags = "充电宝")
@RestController
@RequestMapping("/device/devicecharge")
public class DeviceChargeController extends AbstractController {
    @Autowired
    private DeviceChargeService deviceChargeService;

    /**
     * 列表
     */
    @Login
    @SysLog("充电宝-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_charge",
            authoritiesCnName = "充电宝权限",
            name = "device_devicecharge_list",
            cnName = "充电宝-列表",
            describe = "充电宝-列表",
            authzCode = "device:devicecharge:list"
    )
    @AuthzCode(authzCode = "device:devicecharge:list")
    public R list(@Valid @RequestBody DeviceChargePageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = deviceChargeService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("充电宝-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_charge",
            authoritiesCnName = "充电宝权限",
            name = "device_devicecharge_info",
            cnName = "充电宝-信息",
            describe = "充电宝-信息",
            authzCode = "device:devicecharge:info"
    )
    @AuthzCode(authzCode = "device:devicecharge:info")
    public R info(@Valid @RequestBody DeviceChargeInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        DeviceChargeEntity deviceCharge = deviceChargeService.getById(request.getId());

        return R.ok().put("data", deviceCharge);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("充电宝-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_charge",
            authoritiesCnName = "充电宝权限",
            name = "device_devicecharge_save",
            cnName = "充电宝-保存",
            describe = "充电宝-保存",
            authzCode = "device:devicecharge:save"
    )
    @AuthzCode(authzCode = "device:devicecharge:save")
    public R save(@Valid @RequestBody DeviceChargeSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        DeviceChargeEntity entity = new DeviceChargeEntity();

        entity.setChargeSn(PayUtil.getKeyNo("DT"));
        entity.setChargeSum(request.getChargeSum());

        deviceChargeService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("充电宝-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_charge",
            authoritiesCnName = "充电宝权限",
            name = "device_devicecharge_update",
            cnName = "充电宝-修改",
            describe = "充电宝-修改",
            authzCode = "device:devicecharge:update"
    )
    @AuthzCode(authzCode = "device:devicecharge:update")
    public R update(@Valid @RequestBody DeviceChargeUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        DeviceChargeEntity entity = new DeviceChargeEntity();


        entity.setId(request.getId());
        entity.setChargeSn(request.getChargeSn());
        entity.setChargeSum(request.getChargeSum());

        deviceChargeService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("充电宝-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_charge",
            authoritiesCnName = "充电宝权限",
            name = "device_devicecharge_delete",
            cnName = "充电宝-删除",
            describe = "充电宝-删除",
            authzCode = "device:devicecharge:delete"
    )
    @AuthzCode(authzCode = "device:devicecharge:delete")
    public R delete(@Valid @RequestBody DeviceChargeDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        deviceChargeService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("充电宝-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "device",
            authoritiesName = "device_charge",
            authoritiesCnName = "充电宝权限",
            name = "device_devicecharge_push",
            cnName = "充电宝-发布redis",
            describe = "充电宝-发布redis",
            authzCode = "device:devicecharge:push"
    )
    @AuthzCode(authzCode = "device:devicecharge:push")
    public R push(@Valid @RequestBody DeviceChargePushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        deviceChargeService.push(request);
        return R.ok();
    }

}
