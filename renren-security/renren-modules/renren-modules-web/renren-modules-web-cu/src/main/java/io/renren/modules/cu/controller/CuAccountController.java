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

import io.renren.modules.cu.request.CuAccountPageRequest;
import io.renren.modules.cu.request.CuAccountInfoRequest;
import io.renren.modules.cu.request.CuAccountSaveRequest;
import io.renren.modules.cu.request.CuAccountUpdateRequest;
import io.renren.modules.cu.request.CuAccountDelRequest;
import io.renren.modules.cu.entity.CuAccountEntity;
import io.renren.modules.cu.service.CuAccountService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 客户账目信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-07 10:05:23
 */
@Slf4j
@Api(tags = "客户账目信息")
@RestController
@RequestMapping("/cu/cuaccount")
public class CuAccountController extends AbstractController {
    @Autowired
    private CuAccountService cuAccountService;

    /**
     * 列表
     */
    @Login
    @SysLog("客户账目信息-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_account",
            authoritiesCnName = "客户账目信息权限",
            name = "cu_cuaccount_list",
            cnName = "客户账目信息-列表",
            describe = "客户账目信息-列表",
            authzCode = "cu:cuaccount:list"
    )
    @AuthzCode(authzCode = "cu:cuaccount:list")
    public R list(@Valid @RequestBody CuAccountPageRequest request) {
        Map page = cuAccountService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("客户账目信息-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_account",
            authoritiesCnName = "客户账目信息权限",
            name = "cu_cuaccount_info",
            cnName = "客户账目信息-信息",
            describe = "客户账目信息-信息",
            authzCode = "cu:cuaccount:info"
    )
    @AuthzCode(authzCode = "cu:cuaccount:info")
    public R info(@Valid @RequestBody CuAccountInfoRequest request) {
            CuAccountEntity cuAccount = cuAccountService.getById(request.getId());

        return R.ok().put("data", cuAccount);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("客户账目信息-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_account",
            authoritiesCnName = "客户账目信息权限",
            name = "cu_cuaccount_save",
            cnName = "客户账目信息-保存",
            describe = "客户账目信息-保存",
            authzCode = "cu:cuaccount:save"
    )
    @AuthzCode(authzCode = "cu:cuaccount:save")
    public R save(@Valid @RequestBody CuAccountSaveRequest request) {

            CuAccountEntity entity = new CuAccountEntity();

                                                        entity.setCustomerMoney(request.getCustomerMoney());
                                                                                                                                                                                                        
            cuAccountService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("客户账目信息-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_account",
            authoritiesCnName = "客户账目信息权限",
            name = "cu_cuaccount_update",
            cnName = "客户账目信息-修改",
            describe = "客户账目信息-修改",
            authzCode = "cu:cuaccount:update"
    )
    @AuthzCode(authzCode = "cu:cuaccount:update")
    public R update(@Valid @RequestBody CuAccountUpdateRequest request) {
            CuAccountEntity entity = new CuAccountEntity();



                                    entity.setId(request.getId());
                                                entity.setCustomerMoney(request.getCustomerMoney());
                                                                                                                                                                                                        
            cuAccountService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("客户账目信息-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "CUSTOMER",
            authoritiesName = "cu_account",
            authoritiesCnName = "客户账目信息权限",
            name = "cu_cuaccount_delete",
            cnName = "客户账目信息-删除",
            describe = "客户账目信息-删除",
            authzCode = "cu:cuaccount:delete"
    )
    @AuthzCode(authzCode = "cu:cuaccount:delete")
    public R delete(@Valid @RequestBody CuAccountDelRequest request) {
            cuAccountService.removeById(request.getId());
        return R.ok();
    }

}
