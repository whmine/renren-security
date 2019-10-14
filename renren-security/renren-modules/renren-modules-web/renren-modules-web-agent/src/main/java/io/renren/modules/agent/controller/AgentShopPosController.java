package io.renren.modules.agent.controller;

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

import io.renren.modules.agent.request.AgentShopPosPageRequest;
import io.renren.modules.agent.request.AgentShopPosInfoRequest;
import io.renren.modules.agent.request.AgentShopPosSaveRequest;
import io.renren.modules.agent.request.AgentShopPosUpdateRequest;
import io.renren.modules.agent.request.AgentShopPosDelRequest;
import io.renren.modules.agent.request.AgentShopPosPushRequest;
import io.renren.modules.agent.entity.AgentShopPosEntity;
import io.renren.modules.agent.service.AgentShopPosService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 代理商商户
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 09:24:45
 */
@Slf4j
@Api(tags = "代理商商户")
@RestController
@RequestMapping("/agent/agentshoppos")
public class AgentShopPosController extends AbstractController {
    @Autowired
    private AgentShopPosService agentShopPosService;

    /**
     * 列表
     */
    @Login
    @SysLog("代理商商户-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_pos",
            authoritiesCnName = "代理商商户权限",
            name = "agent_agentshoppos_list",
            cnName = "代理商商户-列表",
            describe = "代理商商户-列表",
            authzCode = "agent:agentshoppos:list"
    )
    @AuthzCode(authzCode = "agent:agentshoppos:list")
    public R list(@Valid @RequestBody AgentShopPosPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = agentShopPosService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("代理商商户-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_pos",
            authoritiesCnName = "代理商商户权限",
            name = "agent_agentshoppos_info",
            cnName = "代理商商户-信息",
            describe = "代理商商户-信息",
            authzCode = "agent:agentshoppos:info"
    )
    @AuthzCode(authzCode = "agent:agentshoppos:info")
    public R info(@Valid @RequestBody AgentShopPosInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        AgentShopPosEntity agentShopPos = agentShopPosService.getById(request.getId());

        return R.ok().put("data", agentShopPos);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("代理商商户-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_pos",
            authoritiesCnName = "代理商商户权限",
            name = "agent_agentshoppos_save",
            cnName = "代理商商户-保存",
            describe = "代理商商户-保存",
            authzCode = "agent:agentshoppos:save"
    )
    @AuthzCode(authzCode = "agent:agentshoppos:save")
    public R save(@Valid @RequestBody AgentShopPosSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        AgentShopPosEntity entity = new AgentShopPosEntity();

        entity.setPosSn(request.getPosSn());
        entity.setPosName(request.getPosName());
        entity.setPosAdmin(request.getPosAdmin());
        entity.setPosBus(request.getPosBus());
        entity.setPosBusRatio(request.getPosBusRatio());
        entity.setPosRatio(request.getPosRatio());
        entity.setPosLogo(request.getPosLogo());
        entity.setPosImg(request.getPosImg());
        entity.setProvince(request.getProvince());
        entity.setCity(request.getCity());
        entity.setDistrict(request.getDistrict());
        entity.setAddress(request.getAddress());
        entity.setWarp(request.getWarp());
        entity.setWeft(request.getWeft());
        entity.setChargingType(request.getChargingType());
        entity.setChargingCompany(request.getChargingCompany());
        entity.setChargingStandard(request.getChargingStandard());
        entity.setFreeTime(request.getFreeTime());
        entity.setConsume(request.getConsume());
        entity.setCipherLine(request.getCipherLine());
        entity.setShopPhone(request.getShopPhone());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setMoney(request.getMoney());

        agentShopPosService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("代理商商户-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_pos",
            authoritiesCnName = "代理商商户权限",
            name = "agent_agentshoppos_update",
            cnName = "代理商商户-修改",
            describe = "代理商商户-修改",
            authzCode = "agent:agentshoppos:update"
    )
    @AuthzCode(authzCode = "agent:agentshoppos:update")
    public R update(@Valid @RequestBody AgentShopPosUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        AgentShopPosEntity entity = new AgentShopPosEntity();


        entity.setId(request.getId());
        entity.setPosSn(request.getPosSn());
        entity.setPosName(request.getPosName());
        entity.setPosAdmin(request.getPosAdmin());
        entity.setPosBus(request.getPosBus());
        entity.setPosBusRatio(request.getPosBusRatio());
        entity.setPosRatio(request.getPosRatio());
        entity.setPosLogo(request.getPosLogo());
        entity.setPosImg(request.getPosImg());
        entity.setProvince(request.getProvince());
        entity.setCity(request.getCity());
        entity.setDistrict(request.getDistrict());
        entity.setAddress(request.getAddress());
        entity.setWarp(request.getWarp());
        entity.setWeft(request.getWeft());
        entity.setChargingType(request.getChargingType());
        entity.setChargingCompany(request.getChargingCompany());
        entity.setChargingStandard(request.getChargingStandard());
        entity.setFreeTime(request.getFreeTime());
        entity.setConsume(request.getConsume());
        entity.setCipherLine(request.getCipherLine());
        entity.setShopPhone(request.getShopPhone());
        entity.setStartTime(request.getStartTime());
        entity.setEndTime(request.getEndTime());
        entity.setMoney(request.getMoney());

        agentShopPosService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("代理商商户-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_pos",
            authoritiesCnName = "代理商商户权限",
            name = "agent_agentshoppos_delete",
            cnName = "代理商商户-删除",
            describe = "代理商商户-删除",
            authzCode = "agent:agentshoppos:delete"
    )
    @AuthzCode(authzCode = "agent:agentshoppos:delete")
    public R delete(@Valid @RequestBody AgentShopPosDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        agentShopPosService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("代理商商户-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_pos",
            authoritiesCnName = "代理商商户权限",
            name = "agent_agentshoppos_push",
            cnName = "代理商商户-发布redis",
            describe = "代理商商户-发布redis",
            authzCode = "agent:agentshoppos:push"
    )
    @AuthzCode(authzCode = "agent:agentshoppos:push")
    public R push(@Valid @RequestBody AgentShopPosPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        agentShopPosService.push(request);
        return R.ok();
    }

}
