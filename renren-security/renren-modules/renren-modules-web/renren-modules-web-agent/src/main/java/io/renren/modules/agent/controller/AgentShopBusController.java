package io.renren.modules.agent.controller;

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

import io.renren.modules.agent.request.AgentShopBusPageRequest;
import io.renren.modules.agent.request.AgentShopBusInfoRequest;
import io.renren.modules.agent.request.AgentShopBusSaveRequest;
import io.renren.modules.agent.request.AgentShopBusUpdateRequest;
import io.renren.modules.agent.request.AgentShopBusDelRequest;
import io.renren.modules.agent.request.AgentShopBusPushRequest;
import io.renren.modules.agent.entity.AgentShopBusEntity;
import io.renren.modules.agent.service.AgentShopBusService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 代理商业务员
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 09:24:45
 */
@Slf4j
@Api(tags = "代理商业务员")
@RestController
@RequestMapping("/agent/agentshopbus")
public class AgentShopBusController extends AbstractController {
    @Autowired
    private AgentShopBusService agentShopBusService;

    /**
     * 列表
     */
    @Login
    @SysLog("代理商业务员-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_bus",
            authoritiesCnName = "代理商业务员权限",
            name = "agent_agentshopbus_list",
            cnName = "代理商业务员-列表",
            describe = "代理商业务员-列表",
            authzCode = "agent:agentshopbus:list"
    )
    @AuthzCode(authzCode = "agent:agentshopbus:list")
    public R list(@Valid @RequestBody AgentShopBusPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = agentShopBusService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("代理商业务员-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_bus",
            authoritiesCnName = "代理商业务员权限",
            name = "agent_agentshopbus_info",
            cnName = "代理商业务员-信息",
            describe = "代理商业务员-信息",
            authzCode = "agent:agentshopbus:info"
    )
    @AuthzCode(authzCode = "agent:agentshopbus:info")
    public R info(@Valid @RequestBody AgentShopBusInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        AgentShopBusEntity agentShopBus = agentShopBusService.getById(request.getId());

        return R.ok().put("data", agentShopBus);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("代理商业务员-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_bus",
            authoritiesCnName = "代理商业务员权限",
            name = "agent_agentshopbus_save",
            cnName = "代理商业务员-保存",
            describe = "代理商业务员-保存",
            authzCode = "agent:agentshopbus:save"
    )
    @AuthzCode(authzCode = "agent:agentshopbus:save")
    public R save(@Valid @RequestBody AgentShopBusSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }

        agentShopBusService.save(request);
        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("代理商业务员-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_bus",
            authoritiesCnName = "代理商业务员权限",
            name = "agent_agentshopbus_update",
            cnName = "代理商业务员-修改",
            describe = "代理商业务员-修改",
            authzCode = "agent:agentshopbus:update"
    )
    @AuthzCode(authzCode = "agent:agentshopbus:update")
    public R update(@Valid @RequestBody AgentShopBusUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
//        AgentShopBusEntity entity = new AgentShopBusEntity();
//
//
//        entity.setId(request.getId());
//        entity.setBusSn(request.getBusSn());
//        entity.setBusName(request.getBusName());
//        entity.setBusPhone(request.getBusPhone());
//        entity.setBusParent(request.getBusParent());
//        entity.setBusRatio(request.getBusRatio());
//        entity.setBusOpen(request.getBusOpen());
//        entity.setCustomerId(request.getCustomerId());

        agentShopBusService.update(request);
        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("代理商业务员-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_bus",
            authoritiesCnName = "代理商业务员权限",
            name = "agent_agentshopbus_delete",
            cnName = "代理商业务员-删除",
            describe = "代理商业务员-删除",
            authzCode = "agent:agentshopbus:delete"
    )
    @AuthzCode(authzCode = "agent:agentshopbus:delete")
    public R delete(@Valid @RequestBody AgentShopBusDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        agentShopBusService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("代理商业务员-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_bus",
            authoritiesCnName = "代理商业务员权限",
            name = "agent_agentshopbus_push",
            cnName = "代理商业务员-发布redis",
            describe = "代理商业务员-发布redis",
            authzCode = "agent:agentshopbus:push"
    )
    @AuthzCode(authzCode = "agent:agentshopbus:push")
    public R push(@Valid @RequestBody AgentShopBusPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        agentShopBusService.push(request);
        return R.ok();
    }

}
