package io.renren.modules.shop.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.exception.RRException;
import io.renren.framework.annotation.AuthzCode;
import io.renren.framework.annotation.AuthzRegister;
import io.renren.framework.annotation.Login;
import io.renren.framework.annotation.SysLog;
import io.renren.common.utils.AbstractController;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.shop.request.*;
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

import io.renren.modules.shop.entity.ShopOrderEntity;
import io.renren.modules.shop.service.ShopOrderService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 订单
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
@Slf4j
@Api(tags = "订单")
@RestController
@RequestMapping("/shop/shoporder")
public class ShopOrderController extends AbstractController {
    @Autowired
    private ShopOrderService shopOrderService;

    /**
     * 列表
     */
    @Login
    @SysLog("订单-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_order",
            authoritiesCnName = "订单权限",
            name = "shop_shoporder_list",
            cnName = "订单-列表",
            describe = "订单-列表",
            authzCode = "shop:shoporder:list"
    )
    @AuthzCode(authzCode = "shop:shoporder:list")
    public R list(@Valid @RequestBody ShopOrderPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = shopOrderService.queryPage(request);
        return R.ok().put("data", page);
    }

    /**
     * 我的列表
     */
    @Login
    @SysLog("订单-我的列表")
    @PostMapping("myList")
    @ApiOperation("我的列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_order",
            authoritiesCnName = "订单权限",
            name = "shop_shoporder_myList",
            cnName = "订单-我的列表",
            describe = "订单-我的列表",
            authzCode = "shop:shoporder:myList"
    )
    @AuthzCode(authzCode = "shop:shoporder:myList")
    public R myList(@Valid @RequestBody ShopOrderMyListRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            return R.ok().put("data", shopOrderService.myList(request));
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }


    /**
     * 信息
     */
    @Login
    @SysLog("订单-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_order",
            authoritiesCnName = "订单权限",
            name = "shop_shoporder_info",
            cnName = "订单-信息",
            describe = "订单-信息",
            authzCode = "shop:shoporder:info"
    )
    @AuthzCode(authzCode = "shop:shoporder:info")
    public R info(@Valid @RequestBody ShopOrderInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopOrderEntity shopOrder = shopOrderService.getById(request.getId());

        return R.ok().put("data", shopOrder);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("订单-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_order",
            authoritiesCnName = "订单权限",
            name = "shop_shoporder_save",
            cnName = "订单-保存",
            describe = "订单-保存",
            authzCode = "shop:shoporder:save"
    )
    @AuthzCode(authzCode = "shop:shoporder:save")
    public R save(@Valid @RequestBody ShopOrderSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopOrderEntity entity = new ShopOrderEntity();

        entity.setSysUserId(request.getSysUserId());
        entity.setGoodsId(request.getGoodsId());
        entity.setOrderSn(request.getOrderSn());
        entity.setOrderAmount(request.getOrderAmount());
        entity.setOrderStatus(request.getOrderStatus());
        entity.setPayStatus(request.getPayStatus());
        entity.setPayCode(request.getPayCode());
        entity.setAddTime(request.getAddTime());

        shopOrderService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("订单-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_order",
            authoritiesCnName = "订单权限",
            name = "shop_shoporder_update",
            cnName = "订单-修改",
            describe = "订单-修改",
            authzCode = "shop:shoporder:update"
    )
    @AuthzCode(authzCode = "shop:shoporder:update")
    public R update(@Valid @RequestBody ShopOrderUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopOrderEntity entity = new ShopOrderEntity();


        entity.setId(request.getId());
        entity.setSysUserId(request.getSysUserId());
        entity.setGoodsId(request.getGoodsId());
        entity.setOrderSn(request.getOrderSn());
        entity.setOrderAmount(request.getOrderAmount());
        entity.setOrderStatus(request.getOrderStatus());
        entity.setPayStatus(request.getPayStatus());
        entity.setPayCode(request.getPayCode());
        entity.setAddTime(request.getAddTime());

        shopOrderService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("订单-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_order",
            authoritiesCnName = "订单权限",
            name = "shop_shoporder_delete",
            cnName = "订单-删除",
            describe = "订单-删除",
            authzCode = "shop:shoporder:delete"
    )
    @AuthzCode(authzCode = "shop:shoporder:delete")
    public R delete(@Valid @RequestBody ShopOrderDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        shopOrderService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("订单-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_order",
            authoritiesCnName = "订单权限",
            name = "shop_shoporder_push",
            cnName = "订单-发布redis",
            describe = "订单-发布redis",
            authzCode = "shop:shoporder:push"
    )
    @AuthzCode(authzCode = "shop:shoporder:push")
    public R push(@Valid @RequestBody ShopOrderPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        shopOrderService.push(request);
        return R.ok();
    }

}
