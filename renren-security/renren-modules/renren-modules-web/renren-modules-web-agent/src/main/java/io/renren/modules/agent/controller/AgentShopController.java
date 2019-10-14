package io.renren.modules.agent.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.exception.RRException;
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

import io.renren.modules.agent.request.AgentShopPageRequest;
import io.renren.modules.agent.request.AgentShopInfoRequest;
import io.renren.modules.agent.request.AgentShopSaveRequest;
import io.renren.modules.agent.request.AgentShopUpdateRequest;
import io.renren.modules.agent.request.AgentShopDelRequest;
import io.renren.modules.agent.request.AgentShopPushRequest;
import io.renren.modules.agent.entity.AgentShopEntity;
import io.renren.modules.agent.service.AgentShopService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 代理商
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 09:24:45
 */
@Slf4j
@Api(tags = "代理商")
@RestController
@RequestMapping("/agent/agentshop")
public class AgentShopController extends AbstractController {
    @Autowired
    private AgentShopService agentShopService;

    /**
     * 列表
     */
    @Login
    @SysLog("代理商-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop",
            authoritiesCnName = "代理商权限",
            name = "agent_agentshop_list",
            cnName = "代理商-列表",
            describe = "代理商-列表",
            authzCode = "agent:agentshop:list"
    )
    @AuthzCode(authzCode = "agent:agentshop:list")
    public R list(@Valid @RequestBody AgentShopPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = agentShopService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("代理商-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop",
            authoritiesCnName = "代理商权限",
            name = "agent_agentshop_info",
            cnName = "代理商-信息",
            describe = "代理商-信息",
            authzCode = "agent:agentshop:info"
    )
    @AuthzCode(authzCode = "agent:agentshop:info")
    public R info(@Valid @RequestBody AgentShopInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        AgentShopEntity agentShop = agentShopService.getById(request.getId());

        return R.ok().put("data", agentShop);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("代理商-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop",
            authoritiesCnName = "代理商权限",
            name = "agent_agentshop_save",
            cnName = "代理商-保存",
            describe = "代理商-保存",
            authzCode = "agent:agentshop:save"
    )
    @AuthzCode(authzCode = "agent:agentshop:save")
    public R save(@Valid @RequestBody AgentShopSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            agentShopService.save(request);
            return R.ok();
        } catch (RRException e) {
            return R.error("异常：" + e.getMsg());
        }
    }

    /**
     * 修改
     */
    @Login
    @SysLog("代理商-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop",
            authoritiesCnName = "代理商权限",
            name = "agent_agentshop_update",
            cnName = "代理商-修改",
            describe = "代理商-修改",
            authzCode = "agent:agentshop:update"
    )
    @AuthzCode(authzCode = "agent:agentshop:update")
    public R update(@Valid @RequestBody AgentShopUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }

        try {
            agentShopService.update(request);
            return R.ok();
        } catch (RRException e) {
            return R.error("异常：" + e.getMsg());
        }
    }

    /**
     * 删除
     */
    @Login
    @SysLog("代理商-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop",
            authoritiesCnName = "代理商权限",
            name = "agent_agentshop_delete",
            cnName = "代理商-删除",
            describe = "代理商-删除",
            authzCode = "agent:agentshop:delete"
    )
    @AuthzCode(authzCode = "agent:agentshop:delete")
    public R delete(@Valid @RequestBody AgentShopDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        agentShopService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("代理商-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop",
            authoritiesCnName = "代理商权限",
            name = "agent_agentshop_push",
            cnName = "代理商-发布redis",
            describe = "代理商-发布redis",
            authzCode = "agent:agentshop:push"
    )
    @AuthzCode(authzCode = "agent:agentshop:push")
    public R push(@Valid @RequestBody AgentShopPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        agentShopService.push(request);
        return R.ok();
    }

}
