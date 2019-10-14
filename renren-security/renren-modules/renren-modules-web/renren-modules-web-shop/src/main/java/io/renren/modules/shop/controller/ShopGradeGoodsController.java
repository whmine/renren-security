package io.renren.modules.shop.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.utils.PayUtil;
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

import io.renren.modules.shop.entity.ShopGradeGoodsEntity;
import io.renren.modules.shop.service.ShopGradeGoodsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 积分商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
@Slf4j
@Api(tags = "积分商品" )
@RestController
@RequestMapping("/shop/shopgradegoods" )
public class ShopGradeGoodsController extends AbstractController {
    @Autowired
    private ShopGradeGoodsService shopGradeGoodsService;

    /**
     * 列表
     */
    @Login
    @SysLog("积分商品-列表" )
    @PostMapping("list" )
    @ApiOperation("列表" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_grade_goods" ,
            authoritiesCnName = "积分商品权限" ,
            name = "shop_shopgradegoods_list" ,
            cnName = "积分商品-列表" ,
            describe = "积分商品-列表" ,
            authzCode = "shop:shopgradegoods:list"
    )
    @AuthzCode(authzCode = "shop:shopgradegoods:list" )
    public R list(@Valid @RequestBody ShopGradeGoodsPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = shopGradeGoodsService.queryPage(request);
        return R.ok().put("data" , page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("积分商品-信息" )
    @PostMapping("info" )
    @ApiOperation("信息" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_grade_goods" ,
            authoritiesCnName = "积分商品权限" ,
            name = "shop_shopgradegoods_info" ,
            cnName = "积分商品-信息" ,
            describe = "积分商品-信息" ,
            authzCode = "shop:shopgradegoods:info"
    )
    @AuthzCode(authzCode = "shop:shopgradegoods:info" )
    public R info(@Valid @RequestBody ShopGradeGoodsInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopGradeGoodsEntity shopGradeGoods = shopGradeGoodsService.getById(request.getId());

        return R.ok().put("data" , shopGradeGoods);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("积分商品-保存" )
    @PostMapping("save" )
    @ApiOperation("保存" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_grade_goods" ,
            authoritiesCnName = "积分商品权限" ,
            name = "shop_shopgradegoods_save" ,
            cnName = "积分商品-保存" ,
            describe = "积分商品-保存" ,
            authzCode = "shop:shopgradegoods:save"
    )
    @AuthzCode(authzCode = "shop:shopgradegoods:save" )
    public R save(@Valid @RequestBody ShopGradeGoodsSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopGradeGoodsEntity entity = new ShopGradeGoodsEntity();

        entity.setCatId(request.getCatId());
        entity.setGoodsSn(PayUtil.getGoodsNo());
        entity.setGoodsName(request.getGoodsName());
        entity.setClickCount(request.getClickCount());
        entity.setShopPrice(request.getShopPrice());
        entity.setGoodsContent(request.getGoodsContent());
        entity.setOriginalImg(request.getOriginalImg());
        entity.setOriginalVideo(request.getOriginalVideo());
        entity.setIsCharge(request.getIsCharge());
        entity.setIsOnSale(request.getIsOnSale());
        entity.setOnTime(request.getOnTime());
        entity.setSalesSum(request.getSalesSum());
        entity.setCollectNum(request.getCollectNum());

        shopGradeGoodsService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("积分商品-修改" )
    @PostMapping("update" )
    @ApiOperation("修改" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_grade_goods" ,
            authoritiesCnName = "积分商品权限" ,
            name = "shop_shopgradegoods_update" ,
            cnName = "积分商品-修改" ,
            describe = "积分商品-修改" ,
            authzCode = "shop:shopgradegoods:update"
    )
    @AuthzCode(authzCode = "shop:shopgradegoods:update" )
    public R update(@Valid @RequestBody ShopGradeGoodsUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopGradeGoodsEntity entity = new ShopGradeGoodsEntity();


        entity.setId(request.getId());
        entity.setCatId(request.getCatId());
        entity.setGoodsSn(request.getGoodsSn());
        entity.setGoodsName(request.getGoodsName());
        entity.setClickCount(request.getClickCount());
        entity.setShopPrice(request.getShopPrice());
        entity.setGoodsContent(request.getGoodsContent());
        entity.setOriginalImg(request.getOriginalImg());
        entity.setOriginalVideo(request.getOriginalVideo());
        entity.setIsCharge(request.getIsCharge());
        entity.setIsOnSale(request.getIsOnSale());
        entity.setOnTime(request.getOnTime());
        entity.setSalesSum(request.getSalesSum());
        entity.setCollectNum(request.getCollectNum());

        shopGradeGoodsService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("积分商品-删除" )
    @PostMapping("delete" )
    @ApiOperation("删除" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_grade_goods" ,
            authoritiesCnName = "积分商品权限" ,
            name = "shop_shopgradegoods_delete" ,
            cnName = "积分商品-删除" ,
            describe = "积分商品-删除" ,
            authzCode = "shop:shopgradegoods:delete"
    )
    @AuthzCode(authzCode = "shop:shopgradegoods:delete" )
    public R delete(@Valid @RequestBody ShopGradeGoodsDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        shopGradeGoodsService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("积分商品-发布redis" )
    @PostMapping("push" )
    @ApiOperation("发布redis" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_grade_goods" ,
            authoritiesCnName = "积分商品权限" ,
            name = "shop_shopgradegoods_push" ,
            cnName = "积分商品-发布redis" ,
            describe = "积分商品-发布redis" ,
            authzCode = "shop:shopgradegoods:push"
    )
    @AuthzCode(authzCode = "shop:shopgradegoods:push" )
    public R push(@Valid @RequestBody ShopGradeGoodsPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        shopGradeGoodsService.push(request);
        return R.ok();
    }

    /**
     * 兑换
     */
    @Login
    @SysLog("积分商品-兑换" )
    @PostMapping("change" )
    @ApiOperation("兑换" )
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP" ,
            authoritiesName = "shop_grade_goods" ,
            authoritiesCnName = "积分商品权限" ,
            name = "shop_shopgradegoods_change" ,
            cnName = "积分商品-兑换" ,
            describe = "积分商品-兑换" ,
            authzCode = "shop:shopgradegoods:change"
    )
    @AuthzCode(authzCode = "shop:shopgradegoods:change" )
    public R change(@Valid @RequestBody ShopGradeGoodsChangeRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        shopGradeGoodsService.change(request);
        return R.ok();
    }


}
