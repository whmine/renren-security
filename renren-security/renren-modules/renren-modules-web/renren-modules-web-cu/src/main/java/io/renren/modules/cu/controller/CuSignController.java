package io.renren.modules.cu.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.exception.RRException;
import io.renren.framework.annotation.AuthzCode;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.Login;
import io.renren.framework.annotation.SysLog;
import io.renren.common.utils.AbstractController;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.cu.request.*;
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

import io.renren.modules.cu.entity.CuSignEntity;
import io.renren.modules.cu.service.CuSignService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 客户签到
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:41:36
 */
@Slf4j
@Api(tags = "客户签到" )
@RestController
@RequestMapping("/cu/cusign" )
public class CuSignController extends AbstractController {
    @Autowired
    private CuSignService cuSignService;

    /**
     * 列表
     */
    @Login
    @SysLog("客户签到-列表" )
    @PostMapping("list" )
    @ApiOperation("列表" )
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER" ,
            authoritiesName = "cu_sign" ,
            authoritiesCnName = "客户签到权限" ,
            name = "cu_cusign_list" ,
            cnName = "客户签到-列表" ,
            describe = "客户签到-列表" ,
            authzCode = "cu:cusign:list"
    )
    @AuthzCode(authzCode = "cu:cusign:list" )
    public R list(@Valid @RequestBody CuSignPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = cuSignService.queryPage(request);
        return R.ok().put("data" , page);
    }

    /**
     * 签到
     */
    @Login
    @SysLog("客户签到-签到" )
    @PostMapping("sign" )
    @ApiOperation("签到" )
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER" ,
            authoritiesName = "cu_sign" ,
            authoritiesCnName = "客户签到权限" ,
            name = "cu_cusign_sign" ,
            cnName = "客户签到-签到" ,
            describe = "客户签到-签到" ,
            authzCode = "cu:cusign:sign"
    )
    @AuthzCode(authzCode = "cu:cusign:sign" )
    public R sign(@Valid @RequestBody CuSignSignRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            cuSignService.sign(request);
            return R.ok();
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }

    /**
     * 签到列表
     */
    @Login
    @SysLog("客户签到-签到列表" )
    @PostMapping("signList" )
    @ApiOperation("签到列表" )
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER" ,
            authoritiesName = "cu_sign" ,
            authoritiesCnName = "客户签到权限" ,
            name = "cu_cusign_signList" ,
            cnName = "客户签到-签到列表" ,
            describe = "客户签到-签到列表" ,
            authzCode = "cu:cusign:signList"
    )
    @AuthzCode(authzCode = "cu:cusign:signList" )
    public R signList(@Valid @RequestBody CuSignListRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        return R.ok().put("data" , cuSignService.signList(request));
    }


    /**
     * 信息
     */
    @Login
    @SysLog("客户签到-信息" )
    @PostMapping("info" )
    @ApiOperation("信息" )
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER" ,
            authoritiesName = "cu_sign" ,
            authoritiesCnName = "客户签到权限" ,
            name = "cu_cusign_info" ,
            cnName = "客户签到-信息" ,
            describe = "客户签到-信息" ,
            authzCode = "cu:cusign:info"
    )
    @AuthzCode(authzCode = "cu:cusign:info" )
    public R info(@Valid @RequestBody CuSignInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        CuSignEntity cuSign = cuSignService.getById(request.getId());

        return R.ok().put("data" , cuSign);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("客户签到-保存" )
    @PostMapping("save" )
    @ApiOperation("保存" )
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER" ,
            authoritiesName = "cu_sign" ,
            authoritiesCnName = "客户签到权限" ,
            name = "cu_cusign_save" ,
            cnName = "客户签到-保存" ,
            describe = "客户签到-保存" ,
            authzCode = "cu:cusign:save"
    )
    @AuthzCode(authzCode = "cu:cusign:save" )
    public R save(@Valid @RequestBody CuSignSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        CuSignEntity entity = new CuSignEntity();

        entity.setCustomerId(request.getCustomerId());
        entity.setCustomerDate(request.getCustomerDate());
        entity.setCustomerYear(request.getCustomerYear());
        entity.setCustomerMonth(request.getCustomerMonth());
        entity.setCustomerDay(request.getCustomerDay());

        cuSignService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("客户签到-修改" )
    @PostMapping("update" )
    @ApiOperation("修改" )
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER" ,
            authoritiesName = "cu_sign" ,
            authoritiesCnName = "客户签到权限" ,
            name = "cu_cusign_update" ,
            cnName = "客户签到-修改" ,
            describe = "客户签到-修改" ,
            authzCode = "cu:cusign:update"
    )
    @AuthzCode(authzCode = "cu:cusign:update" )
    public R update(@Valid @RequestBody CuSignUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        CuSignEntity entity = new CuSignEntity();


        entity.setId(request.getId());
        entity.setCustomerId(request.getCustomerId());
        entity.setCustomerDate(request.getCustomerDate());
        entity.setCustomerYear(request.getCustomerYear());
        entity.setCustomerMonth(request.getCustomerMonth());
        entity.setCustomerDay(request.getCustomerDay());

        cuSignService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("客户签到-删除" )
    @PostMapping("delete" )
    @ApiOperation("删除" )
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER" ,
            authoritiesName = "cu_sign" ,
            authoritiesCnName = "客户签到权限" ,
            name = "cu_cusign_delete" ,
            cnName = "客户签到-删除" ,
            describe = "客户签到-删除" ,
            authzCode = "cu:cusign:delete"
    )
    @AuthzCode(authzCode = "cu:cusign:delete" )
    public R delete(@Valid @RequestBody CuSignDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        cuSignService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("客户签到-发布redis" )
    @PostMapping("push" )
    @ApiOperation("发布redis" )
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER" ,
            authoritiesName = "cu_sign" ,
            authoritiesCnName = "客户签到权限" ,
            name = "cu_cusign_push" ,
            cnName = "客户签到-发布redis" ,
            describe = "客户签到-发布redis" ,
            authzCode = "cu:cusign:push"
    )
    @AuthzCode(authzCode = "cu:cusign:push" )
    public R push(@Valid @RequestBody CuSignPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        cuSignService.push(request);
        return R.ok();
    }

}
