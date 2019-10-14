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

import io.renren.modules.shop.entity.ShopMyGoodsEntity;
import io.renren.modules.shop.service.ShopMyGoodsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 我的商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
@Slf4j
@Api(tags = "我的商品" )
@RestController
@RequestMapping("/shop/shopmygoods" )
public class ShopMyGoodsController extends AbstractController {
    @Autowired
    private ShopMyGoodsService shopMyGoodsService;

    /**
     * 列表
     */
    @Login
    @SysLog("我的商品-列表" )
    @PostMapping("list" )
    @ApiOperation("列表" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_my_goods" ,
            authoritiesCnName = "我的商品权限" ,
            name = "shop_shopmygoods_list" ,
            cnName = "我的商品-列表" ,
            describe = "我的商品-列表" ,
            authzCode = "shop:shopmygoods:list"
    )
    @AuthzCode(authzCode = "shop:shopmygoods:list" )
    public R list(@Valid @RequestBody ShopMyGoodsPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = shopMyGoodsService.queryPage(request);
        return R.ok().put("data" , page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("我的商品-信息" )
    @PostMapping("info" )
    @ApiOperation("信息" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_my_goods" ,
            authoritiesCnName = "我的商品权限" ,
            name = "shop_shopmygoods_info" ,
            cnName = "我的商品-信息" ,
            describe = "我的商品-信息" ,
            authzCode = "shop:shopmygoods:info"
    )
    @AuthzCode(authzCode = "shop:shopmygoods:info" )
    public R info(@Valid @RequestBody ShopMyGoodsInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopMyGoodsEntity shopMyGoods = shopMyGoodsService.getById(request.getId());

        return R.ok().put("data" , shopMyGoods);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("我的商品-保存" )
    @PostMapping("save" )
    @ApiOperation("保存" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_my_goods" ,
            authoritiesCnName = "我的商品权限" ,
            name = "shop_shopmygoods_save" ,
            cnName = "我的商品-保存" ,
            describe = "我的商品-保存" ,
            authzCode = "shop:shopmygoods:save"
    )
    @AuthzCode(authzCode = "shop:shopmygoods:save" )
    public R save(@Valid @RequestBody ShopMyGoodsSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopMyGoodsEntity entity = new ShopMyGoodsEntity();

        entity.setGoodsId(request.getGoodsId());
        entity.setSysUserId(request.getSysUserId());

        shopMyGoodsService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("我的商品-修改" )
    @PostMapping("update" )
    @ApiOperation("修改" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_my_goods" ,
            authoritiesCnName = "我的商品权限" ,
            name = "shop_shopmygoods_update" ,
            cnName = "我的商品-修改" ,
            describe = "我的商品-修改" ,
            authzCode = "shop:shopmygoods:update"
    )
    @AuthzCode(authzCode = "shop:shopmygoods:update" )
    public R update(@Valid @RequestBody ShopMyGoodsUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopMyGoodsEntity entity = new ShopMyGoodsEntity();


        entity.setId(request.getId());
        entity.setGoodsId(request.getGoodsId());
        entity.setSysUserId(request.getSysUserId());

        shopMyGoodsService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("我的商品-删除" )
    @PostMapping("delete" )
    @ApiOperation("删除" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_my_goods" ,
            authoritiesCnName = "我的商品权限" ,
            name = "shop_shopmygoods_delete" ,
            cnName = "我的商品-删除" ,
            describe = "我的商品-删除" ,
            authzCode = "shop:shopmygoods:delete"
    )
    @AuthzCode(authzCode = "shop:shopmygoods:delete" )
    public R delete(@Valid @RequestBody ShopMyGoodsDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        shopMyGoodsService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("我的商品-发布redis" )
    @PostMapping("push" )
    @ApiOperation("发布redis" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_my_goods" ,
            authoritiesCnName = "我的商品权限" ,
            name = "shop_shopmygoods_push" ,
            cnName = "我的商品-发布redis" ,
            describe = "我的商品-发布redis" ,
            authzCode = "shop:shopmygoods:push"
    )
    @AuthzCode(authzCode = "shop:shopmygoods:push" )
    public R push(@Valid @RequestBody ShopMyGoodsPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        shopMyGoodsService.push(request);
        return R.ok();
    }


    /**
     * 我的列表
     */
    @Login
    @SysLog("我的商品-我的列表" )
    @PostMapping("myList" )
    @ApiOperation("我的列表" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_my_goods" ,
            authoritiesCnName = "我的商品权限" ,
            name = "shop_shopmygoods_myList" ,
            cnName = "我的商品-我的列表" ,
            describe = "我的商品-我的列表" ,
            authzCode = "shop:shopmygoods:myList"
    )
    @AuthzCode(authzCode = "shop:shopmygoods:myList" )
    public R myList(@Valid @RequestBody ShopMyGoodsListRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            return R.ok().put("data" , shopMyGoodsService.myList(request));
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }

}
