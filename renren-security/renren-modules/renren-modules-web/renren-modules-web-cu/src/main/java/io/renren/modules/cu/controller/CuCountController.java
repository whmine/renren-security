package io.renren.modules.cu.controller;

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

import javax.validation.Valid;

import io.renren.modules.cu.request.CuCountPageRequest;
import io.renren.modules.cu.request.CuCountInfoRequest;
import io.renren.modules.cu.request.CuCountSaveRequest;
import io.renren.modules.cu.request.CuCountUpdateRequest;
import io.renren.modules.cu.request.CuCountDelRequest;
import io.renren.modules.cu.entity.CuCountEntity;
import io.renren.modules.cu.service.CuCountService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 客户统计信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-07 10:05:23
 */
@Slf4j
@Api(tags = "客户统计信息")
@RestController
@RequestMapping("/cu/cucount")
public class CuCountController extends AbstractController {
    @Autowired
    private CuCountService cuCountService;

    /**
     * 列表
     */
    @Login
    @SysLog("客户统计信息-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_count",
            authoritiesCnName = "客户统计信息权限",
            name = "cu_cucount_list",
            cnName = "客户统计信息-列表",
            describe = "客户统计信息-列表",
            authzCode = "cu:cucount:list"
    )
    @AuthzCode(authzCode = "cu:cucount:list")
    public R list(@Valid @RequestBody CuCountPageRequest request) {
        Map page = cuCountService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("客户统计信息-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_count",
            authoritiesCnName = "客户统计信息权限",
            name = "cu_cucount_info",
            cnName = "客户统计信息-信息",
            describe = "客户统计信息-信息",
            authzCode = "cu:cucount:info"
    )
    @AuthzCode(authzCode = "cu:cucount:info")
    public R info(@Valid @RequestBody CuCountInfoRequest request) {
        CuCountEntity cuCount = cuCountService.getById(request.getId());

        return R.ok().put("data", cuCount);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("客户统计信息-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_count",
            authoritiesCnName = "客户统计信息权限",
            name = "cu_cucount_save",
            cnName = "客户统计信息-保存",
            describe = "客户统计信息-保存",
            authzCode = "cu:cucount:save"
    )
    @AuthzCode(authzCode = "cu:cucount:save")
    public R save(@Valid @RequestBody CuCountSaveRequest request) {

        CuCountEntity entity = new CuCountEntity();

        entity.setCustomerLevel(request.getCustomerLevel());

        cuCountService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("客户统计信息-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_count",
            authoritiesCnName = "客户统计信息权限",
            name = "cu_cucount_update",
            cnName = "客户统计信息-修改",
            describe = "客户统计信息-修改",
            authzCode = "cu:cucount:update"
    )
    @AuthzCode(authzCode = "cu:cucount:update")
    public R update(@Valid @RequestBody CuCountUpdateRequest request) {
        CuCountEntity entity = new CuCountEntity();


        entity.setId(request.getId());
        entity.setCustomerLevel(request.getCustomerLevel());

        cuCountService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("客户统计信息-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_count",
            authoritiesCnName = "客户统计信息权限",
            name = "cu_cucount_delete",
            cnName = "客户统计信息-删除",
            describe = "客户统计信息-删除",
            authzCode = "cu:cucount:delete"
    )
    @AuthzCode(authzCode = "cu:cucount:delete")
    public R delete(@Valid @RequestBody CuCountDelRequest request) {
        cuCountService.removeById(request.getId());
        return R.ok();
    }

}
