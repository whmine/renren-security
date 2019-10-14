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

import io.renren.modules.agent.request.AgentShopAdminPageRequest;
import io.renren.modules.agent.request.AgentShopAdminInfoRequest;
import io.renren.modules.agent.request.AgentShopAdminSaveRequest;
import io.renren.modules.agent.request.AgentShopAdminUpdateRequest;
import io.renren.modules.agent.request.AgentShopAdminDelRequest;
import io.renren.modules.agent.request.AgentShopAdminPushRequest;
import io.renren.modules.agent.entity.AgentShopAdminEntity;
import io.renren.modules.agent.service.AgentShopAdminService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 代理商管理员
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 09:24:45
 */
@Slf4j
@Api(tags = "代理商管理员")
@RestController
@RequestMapping("/agent/agentshopadmin")
public class AgentShopAdminController extends AbstractController {
    @Autowired
    private AgentShopAdminService agentShopAdminService;

    /**
     * 列表
     */
    @Login
    @SysLog("代理商管理员-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_admin",
            authoritiesCnName = "代理商管理员权限",
            name = "agent_agentshopadmin_list",
            cnName = "代理商管理员-列表",
            describe = "代理商管理员-列表",
            authzCode = "agent:agentshopadmin:list"
    )
    @AuthzCode(authzCode = "agent:agentshopadmin:list")
    public R list(@Valid @RequestBody AgentShopAdminPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = agentShopAdminService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("代理商管理员-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_admin",
            authoritiesCnName = "代理商管理员权限",
            name = "agent_agentshopadmin_info",
            cnName = "代理商管理员-信息",
            describe = "代理商管理员-信息",
            authzCode = "agent:agentshopadmin:info"
    )
    @AuthzCode(authzCode = "agent:agentshopadmin:info")
    public R info(@Valid @RequestBody AgentShopAdminInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        AgentShopAdminEntity agentShopAdmin = agentShopAdminService.getById(request.getId());

        return R.ok().put("data", agentShopAdmin);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("代理商管理员-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_admin",
            authoritiesCnName = "代理商管理员权限",
            name = "agent_agentshopadmin_save",
            cnName = "代理商管理员-保存",
            describe = "代理商管理员-保存",
            authzCode = "agent:agentshopadmin:save"
    )
    @AuthzCode(authzCode = "agent:agentshopadmin:save")
    public R save(@Valid @RequestBody AgentShopAdminSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            agentShopAdminService.save(request);
            return R.ok();
        } catch (RRException e) {
            return R.error("异常：" + e.getMsg());
        }
    }

    /**
     * 修改
     */
    @Login
    @SysLog("代理商管理员-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_admin",
            authoritiesCnName = "代理商管理员权限",
            name = "agent_agentshopadmin_update",
            cnName = "代理商管理员-修改",
            describe = "代理商管理员-修改",
            authzCode = "agent:agentshopadmin:update"
    )
    @AuthzCode(authzCode = "agent:agentshopadmin:update")
    public R update(@Valid @RequestBody AgentShopAdminUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            agentShopAdminService.update(request);
            return R.ok();
        } catch (RRException e) {
            return R.error("异常：" + e.getMsg());
        }
    }

    /**
     * 删除
     */
    @Login
    @SysLog("代理商管理员-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_admin",
            authoritiesCnName = "代理商管理员权限",
            name = "agent_agentshopadmin_delete",
            cnName = "代理商管理员-删除",
            describe = "代理商管理员-删除",
            authzCode = "agent:agentshopadmin:delete"
    )
    @AuthzCode(authzCode = "agent:agentshopadmin:delete")
    public R delete(@Valid @RequestBody AgentShopAdminDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        agentShopAdminService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("代理商管理员-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "agent",
            authoritiesName = "agent_shop_admin",
            authoritiesCnName = "代理商管理员权限",
            name = "agent_agentshopadmin_push",
            cnName = "代理商管理员-发布redis",
            describe = "代理商管理员-发布redis",
            authzCode = "agent:agentshopadmin:push"
    )
    @AuthzCode(authzCode = "agent:agentshopadmin:push")
    public R push(@Valid @RequestBody AgentShopAdminPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        agentShopAdminService.push(request);
        return R.ok();
    }

}
