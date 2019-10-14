package io.renren.modules.shop.controller;

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

import io.renren.modules.shop.request.ShopPayPageRequest;
import io.renren.modules.shop.request.ShopPayInfoRequest;
import io.renren.modules.shop.request.ShopPaySaveRequest;
import io.renren.modules.shop.request.ShopPayUpdateRequest;
import io.renren.modules.shop.request.ShopPayDelRequest;
import io.renren.modules.shop.request.ShopPayPushRequest;
import io.renren.modules.shop.entity.ShopPayEntity;
import io.renren.modules.shop.service.ShopPayService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 支付订单
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:44
 */
@Slf4j
@Api(tags = "支付订单")
@RestController
@RequestMapping("/shop/shoppay")
public class ShopPayController extends AbstractController {
    @Autowired
    private ShopPayService shopPayService;

    /**
     * 列表
     */
    @Login
    @SysLog("支付订单-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_pay",
            authoritiesCnName = "支付订单权限",
            name = "shop_shoppay_list",
            cnName = "支付订单-列表",
            describe = "支付订单-列表",
            authzCode = "shop:shoppay:list"
    )
    @AuthzCode(authzCode = "shop:shoppay:list")
    public R list(@Valid @RequestBody ShopPayPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = shopPayService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("支付订单-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_pay",
            authoritiesCnName = "支付订单权限",
            name = "shop_shoppay_info",
            cnName = "支付订单-信息",
            describe = "支付订单-信息",
            authzCode = "shop:shoppay:info"
    )
    @AuthzCode(authzCode = "shop:shoppay:info")
    public R info(@Valid @RequestBody ShopPayInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            ShopPayEntity shopPay = shopPayService.getById(request.getId());

        return R.ok().put("data", shopPay);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("支付订单-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_pay",
            authoritiesCnName = "支付订单权限",
            name = "shop_shoppay_save",
            cnName = "支付订单-保存",
            describe = "支付订单-保存",
            authzCode = "shop:shoppay:save"
    )
    @AuthzCode(authzCode = "shop:shoppay:save")
    public R save(@Valid @RequestBody ShopPaySaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            ShopPayEntity entity = new ShopPayEntity();

                                                        entity.setSysUserId(request.getSysUserId());
                                                entity.setGoodsId(request.getGoodsId());
                                                entity.setPayCode(request.getPayCode());
                                                entity.setPrice(request.getPrice());
                                                entity.setPayStatus(request.getPayStatus());
                                                                                                                                                                                                        
            shopPayService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("支付订单-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_pay",
            authoritiesCnName = "支付订单权限",
            name = "shop_shoppay_update",
            cnName = "支付订单-修改",
            describe = "支付订单-修改",
            authzCode = "shop:shoppay:update"
    )
    @AuthzCode(authzCode = "shop:shoppay:update")
    public R update(@Valid @RequestBody ShopPayUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
            ShopPayEntity entity = new ShopPayEntity();



                                    entity.setId(request.getId());
                                                entity.setSysUserId(request.getSysUserId());
                                                entity.setGoodsId(request.getGoodsId());
                                                entity.setPayCode(request.getPayCode());
                                                entity.setPrice(request.getPrice());
                                                entity.setPayStatus(request.getPayStatus());
                                                                                                                                                                                                        
            shopPayService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("支付订单-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_pay",
            authoritiesCnName = "支付订单权限",
            name = "shop_shoppay_delete",
            cnName = "支付订单-删除",
            describe = "支付订单-删除",
            authzCode = "shop:shoppay:delete"
    )
    @AuthzCode(authzCode = "shop:shoppay:delete")
    public R delete(@Valid @RequestBody ShopPayDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        shopPayService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("支付订单-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_pay",
            authoritiesCnName = "支付订单权限",
            name = "shop_shoppay_push",
            cnName = "支付订单-发布redis",
            describe = "支付订单-发布redis",
            authzCode = "shop:shoppay:push"
    )
    @AuthzCode(authzCode = "shop:shoppay:push")
    public R push(@Valid @RequestBody ShopPayPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        shopPayService.push(request);
        return R.ok();
    }

}
