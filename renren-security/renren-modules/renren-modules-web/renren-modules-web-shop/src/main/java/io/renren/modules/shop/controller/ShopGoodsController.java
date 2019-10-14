package io.renren.modules.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import io.renren.modules.shop.entity.ShopGoodsEntity;
import io.renren.modules.shop.service.ShopGoodsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
@Slf4j
@Api(tags = "商品")
@RestController
@RequestMapping("/shop/shopgoods")
public class ShopGoodsController extends AbstractController {
    @Autowired
    private ShopGoodsService shopGoodsService;

    /**
     * 列表
     */
    @Login
    @SysLog("商品-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_goods",
            authoritiesCnName = "商品权限",
            name = "shop_shopgoods_list",
            cnName = "商品-列表",
            describe = "商品-列表",
            authzCode = "shop:shopgoods:list"
    )
    @AuthzCode(authzCode = "shop:shopgoods:list")
    public R list(@Valid @RequestBody ShopGoodsPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            Map page = shopGoodsService.queryPage(request);
            return R.ok().put("data", page);
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }


    /**
     * 信息
     */
    @Login
    @SysLog("商品-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_goods",
            authoritiesCnName = "商品权限",
            name = "shop_shopgoods_info",
            cnName = "商品-信息",
            describe = "商品-信息",
            authzCode = "shop:shopgoods:info"
    )
    @AuthzCode(authzCode = "shop:shopgoods:info")
    public R info(@Valid @RequestBody ShopGoodsInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map shopGoods = shopGoodsService.info(request);

        return R.ok().put("data", shopGoods);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("商品-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_goods",
            authoritiesCnName = "商品权限",
            name = "shop_shopgoods_save",
            cnName = "商品-保存",
            describe = "商品-保存",
            authzCode = "shop:shopgoods:save"
    )
    @AuthzCode(authzCode = "shop:shopgoods:save")
    public R save(@Valid @RequestBody ShopGoodsSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopGoodsEntity entity = new ShopGoodsEntity();

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

        shopGoodsService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("商品-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_goods",
            authoritiesCnName = "商品权限",
            name = "shop_shopgoods_update",
            cnName = "商品-修改",
            describe = "商品-修改",
            authzCode = "shop:shopgoods:update"
    )
    @AuthzCode(authzCode = "shop:shopgoods:update")
    public R update(@Valid @RequestBody ShopGoodsUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopGoodsEntity entity = new ShopGoodsEntity();


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

        shopGoodsService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("商品-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_goods",
            authoritiesCnName = "商品权限",
            name = "shop_shopgoods_delete",
            cnName = "商品-删除",
            describe = "商品-删除",
            authzCode = "shop:shopgoods:delete"
    )
    @AuthzCode(authzCode = "shop:shopgoods:delete")
    public R delete(@Valid @RequestBody ShopGoodsDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        shopGoodsService.removeById(request.getId());
        return R.ok();
    }


    @Login
    @SysLog("商品-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_goods",
            authoritiesCnName = "商品权限",
            name = "shop_shopgoods_push",
            cnName = "商品-发布redis",
            describe = "商品-发布redis",
            authzCode = "shop:shopgoods:push"
    )
    @AuthzCode(authzCode = "shop:shopgoods:push")
    public R push(@Valid @RequestBody ShopGoodsPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        shopGoodsService.push(request);
        return R.ok();
    }


    /**
     * 购买
     */
    @Login
//    @SysLog("商品-购买")
    @PostMapping("buy")
    @ApiOperation("购买")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_goods",
            authoritiesCnName = "商品权限",
            name = "shop_shopgoods_buy",
            cnName = "商品-购买",
            describe = "商品-购买",
            authzCode = "shop:shopgoods:buy"
    )
    @AuthzCode(authzCode = "shop:shopgoods:buy")
    public R buy(@Valid @RequestBody ShopGoodsBuyRequest request, HttpServletRequest httpRequest, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            Map<String, String> map = shopGoodsService.buy(request, httpRequest);
            log.debug("购买" + map);
            return R.ok().put("data", map);
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }


    /**
     * 购买初始化
     */
    @Login
    @SysLog("商品-购买初始化")
    @PostMapping("configInfo")
    @ApiOperation("购买初始化")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_goods",
            authoritiesCnName = "商品权限",
            name = "shop_shopgoods_configInfo",
            cnName = "商品-购买初始化",
            describe = "商品-购买初始化",
            authzCode = "shop:shopgoods:configInfo"
    )
    @AuthzCode(authzCode = "shop:shopgoods:configInfo")
    public R configInfo(@Valid @RequestBody ShopGoodsConfigInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            Map<String, String> map = shopGoodsService.configInfo(request);
            log.debug("购买初始化" + map);
            return R.ok().put("data", map);
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }

    @RequestMapping("/notify")
    public void notify(HttpServletRequest request, HttpServletResponse response) {
        try {
            String resXml = shopGoodsService.notify(request, response);
            PrintWriter out = response.getWriter();
            out.write(resXml);
        } catch (IOException e) {
        }
    }

}
