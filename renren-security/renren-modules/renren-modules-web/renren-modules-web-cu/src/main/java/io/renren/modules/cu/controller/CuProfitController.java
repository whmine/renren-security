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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import io.renren.modules.cu.entity.CuProfitEntity;
import io.renren.modules.cu.service.CuProfitService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 客户收益信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-07 10:05:23
 */
@Slf4j
@Api(tags = "客户收益信息")
@RestController
@RequestMapping("/cu/cuprofit")
public class CuProfitController extends AbstractController {
    @Autowired
    private CuProfitService cuProfitService;

    /**
     * 列表
     */
    @Login
    @SysLog("客户收益信息-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_profit",
            authoritiesCnName = "客户收益信息权限",
            name = "cu_cuprofit_list",
            cnName = "客户收益信息-列表",
            describe = "客户收益信息-列表",
            authzCode = "cu:cuprofit:list"
    )
    @AuthzCode(authzCode = "cu:cuprofit:list")
    public R list(@Valid @RequestBody CuProfitPageRequest request) {
        Map page = cuProfitService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("客户收益信息-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_profit",
            authoritiesCnName = "客户收益信息权限",
            name = "cu_cuprofit_info",
            cnName = "客户收益信息-信息",
            describe = "客户收益信息-信息",
            authzCode = "cu:cuprofit:info"
    )
    @AuthzCode(authzCode = "cu:cuprofit:info")
    public R info(@Valid @RequestBody CuProfitInfoRequest request) {
        CuProfitEntity cuProfit = cuProfitService.getById(request.getId());

        return R.ok().put("data", cuProfit);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("客户收益信息-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_profit",
            authoritiesCnName = "客户收益信息权限",
            name = "cu_cuprofit_save",
            cnName = "客户收益信息-保存",
            describe = "客户收益信息-保存",
            authzCode = "cu:cuprofit:save"
    )
    @AuthzCode(authzCode = "cu:cuprofit:save")
    public R save(@Valid @RequestBody CuProfitSaveRequest request) {

        CuProfitEntity entity = new CuProfitEntity();

        entity.setCustomerId(request.getCustomerId());
        entity.setSourceId(request.getSourceId());
        entity.setSourceType(request.getSourceType());
        entity.setProfitNum(request.getProfitNum());
        entity.setProfitType(request.getProfitType());
        entity.setProfitStatus(request.getProfitStatus());

        cuProfitService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("客户收益信息-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_profit",
            authoritiesCnName = "客户收益信息权限",
            name = "cu_cuprofit_update",
            cnName = "客户收益信息-修改",
            describe = "客户收益信息-修改",
            authzCode = "cu:cuprofit:update"
    )
    @AuthzCode(authzCode = "cu:cuprofit:update")
    public R update(@Valid @RequestBody CuProfitUpdateRequest request) {
        CuProfitEntity entity = new CuProfitEntity();


        entity.setId(request.getId());
        entity.setCustomerId(request.getCustomerId());
        entity.setSourceId(request.getSourceId());
        entity.setSourceType(request.getSourceType());
        entity.setProfitNum(request.getProfitNum());
        entity.setProfitType(request.getProfitType());
        entity.setProfitStatus(request.getProfitStatus());

        cuProfitService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("客户收益信息-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_profit",
            authoritiesCnName = "客户收益信息权限",
            name = "cu_cuprofit_delete",
            cnName = "客户收益信息-删除",
            describe = "客户收益信息-删除",
            authzCode = "cu:cuprofit:delete"
    )
    @AuthzCode(authzCode = "cu:cuprofit:delete")
    public R delete(@Valid @RequestBody CuProfitDelRequest request) {
        cuProfitService.removeById(request.getId());
        return R.ok();
    }


    /**
     * 当前月份积分数
     */
    @Login
    @SysLog("客户收益信息-当前月份积分数")
    @PostMapping("count")
    @ApiOperation("当前月份积分数")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_profit",
            authoritiesCnName = "客户收益信息权限",
            name = "cu_cuprofit_count",
            cnName = "客户收益信息-当前月份积分数",
            describe = "客户收益信息-当前月份积分数",
            authzCode = "cu:cuprofit:count"
    )
    @AuthzCode(authzCode = "cu:cuprofit:count")
    public R count(@Valid @RequestBody CuProfitCountRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            return R.ok().put("data", cuProfitService.count(request));
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }

    /**
     * 当前月份积分列表
     */
    @Login
    @SysLog("客户收益信息-当前月份积分列表")
    @PostMapping("myList")
    @ApiOperation("当前月份积分列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_profit",
            authoritiesCnName = "客户收益信息权限",
            name = "cu_cuprofit_myList",
            cnName = "客户收益信息-当前月份积分列表",
            describe = "客户收益信息-当前月份积分列表",
            authzCode = "cu:cuprofit:myList"
    )
    @AuthzCode(authzCode = "cu:cuprofit:myList")
    public R myList(@Valid @RequestBody CuProfitMyListRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            return R.ok().put("data", cuProfitService.myList(request));
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }

}
