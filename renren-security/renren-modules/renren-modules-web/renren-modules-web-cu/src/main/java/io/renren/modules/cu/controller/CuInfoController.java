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

import io.renren.modules.cu.request.CuInfoPageRequest;
import io.renren.modules.cu.request.CuInfoInfoRequest;
import io.renren.modules.cu.request.CuInfoSaveRequest;
import io.renren.modules.cu.request.CuInfoUpdateRequest;
import io.renren.modules.cu.request.CuInfoDelRequest;
import io.renren.modules.cu.entity.CuInfoEntity;
import io.renren.modules.cu.service.CuInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 客户基础信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-07 10:05:23
 */
@Slf4j
@Api(tags = "客户基础信息")
@RestController
@RequestMapping("/cu/cuinfo")
public class CuInfoController extends AbstractController {
    @Autowired
    private CuInfoService cuInfoService;

    /**
     * 列表
     */
    @Login
    @SysLog("客户基础信息-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_info",
            authoritiesCnName = "客户基础信息权限",
            name = "cu_cuinfo_list",
            cnName = "客户基础信息-列表",
            describe = "客户基础信息-列表",
            authzCode = "cu:cuinfo:list"
    )
    @AuthzCode(authzCode = "cu:cuinfo:list")
    public R list(@Valid @RequestBody CuInfoPageRequest request) {
        Map page = cuInfoService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("客户基础信息-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_info",
            authoritiesCnName = "客户基础信息权限",
            name = "cu_cuinfo_info",
            cnName = "客户基础信息-信息",
            describe = "客户基础信息-信息",
            authzCode = "cu:cuinfo:info"
    )
    @AuthzCode(authzCode = "cu:cuinfo:info")
    public R info(@Valid @RequestBody CuInfoInfoRequest request) {
            CuInfoEntity cuInfo = cuInfoService.getById(request.getId());

        return R.ok().put("data", cuInfo);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("客户基础信息-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_info",
            authoritiesCnName = "客户基础信息权限",
            name = "cu_cuinfo_save",
            cnName = "客户基础信息-保存",
            describe = "客户基础信息-保存",
            authzCode = "cu:cuinfo:save"
    )
    @AuthzCode(authzCode = "cu:cuinfo:save")
    public R save(@Valid @RequestBody CuInfoSaveRequest request) {

            CuInfoEntity entity = new CuInfoEntity();

                                                        entity.setCustomerName(request.getCustomerName());
                                                entity.setCustomerHead(request.getCustomerHead());
                                                entity.setCustomerPhone(request.getCustomerPhone());
                                                entity.setCustomerCard(request.getCustomerCard());
                                                entity.setCustomerCardName(request.getCustomerCardName());
                                                entity.setCustomerAcronym(request.getCustomerAcronym());
                                                                                                                                                                                                        
            cuInfoService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("客户基础信息-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_info",
            authoritiesCnName = "客户基础信息权限",
            name = "cu_cuinfo_update",
            cnName = "客户基础信息-修改",
            describe = "客户基础信息-修改",
            authzCode = "cu:cuinfo:update"
    )
    @AuthzCode(authzCode = "cu:cuinfo:update")
    public R update(@Valid @RequestBody CuInfoUpdateRequest request) {
            CuInfoEntity entity = new CuInfoEntity();



                                    entity.setId(request.getId());
                                                entity.setCustomerName(request.getCustomerName());
                                                entity.setCustomerHead(request.getCustomerHead());
                                                entity.setCustomerPhone(request.getCustomerPhone());
                                                entity.setCustomerCard(request.getCustomerCard());
                                                entity.setCustomerCardName(request.getCustomerCardName());
                                                entity.setCustomerAcronym(request.getCustomerAcronym());
                                                                                                                                                                                                        
            cuInfoService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("客户基础信息-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_info",
            authoritiesCnName = "客户基础信息权限",
            name = "cu_cuinfo_delete",
            cnName = "客户基础信息-删除",
            describe = "客户基础信息-删除",
            authzCode = "cu:cuinfo:delete"
    )
    @AuthzCode(authzCode = "cu:cuinfo:delete")
    public R delete(@Valid @RequestBody CuInfoDelRequest request) {
            cuInfoService.removeById(request.getId());
        return R.ok();
    }

}
