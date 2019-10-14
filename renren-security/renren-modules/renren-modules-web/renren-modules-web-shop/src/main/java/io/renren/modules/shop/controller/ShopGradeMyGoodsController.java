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

import io.renren.modules.shop.entity.ShopGradeMyGoodsEntity;
import io.renren.modules.shop.service.ShopGradeMyGoodsService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;


/**
 * 我的积分商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
@Slf4j
@Api(tags = "我的积分商品")
@RestController
@RequestMapping("/shop/shopgrademygoods")
public class ShopGradeMyGoodsController extends AbstractController {
    @Autowired
    private ShopGradeMyGoodsService shopGradeMyGoodsService;

    /**
     * 列表
     */
    @Login
    @SysLog("我的积分商品-列表")
    @PostMapping("list")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_grade_my_goods",
            authoritiesCnName = "我的积分商品权限",
            name = "shop_shopgrademygoods_list",
            cnName = "我的积分商品-列表",
            describe = "我的积分商品-列表",
            authzCode = "shop:shopgrademygoods:list"
    )
    @AuthzCode(authzCode = "shop:shopgrademygoods:list")
    public R list(@Valid @RequestBody ShopGradeMyGoodsPageRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        Map page = shopGradeMyGoodsService.queryPage(request);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @Login
    @SysLog("我的积分商品-信息")
    @PostMapping("info")
    @ApiOperation("信息")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_grade_my_goods",
            authoritiesCnName = "我的积分商品权限",
            name = "shop_shopgrademygoods_info",
            cnName = "我的积分商品-信息",
            describe = "我的积分商品-信息",
            authzCode = "shop:shopgrademygoods:info"
    )
    @AuthzCode(authzCode = "shop:shopgrademygoods:info")
    public R info(@Valid @RequestBody ShopGradeMyGoodsInfoRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopGradeMyGoodsEntity shopGradeMyGoods = shopGradeMyGoodsService.getById(request.getId());

        return R.ok().put("data", shopGradeMyGoods);
    }

    /**
     * 保存
     */
    @Login
    @SysLog("我的积分商品-保存")
    @PostMapping("save")
    @ApiOperation("保存")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_grade_my_goods",
            authoritiesCnName = "我的积分商品权限",
            name = "shop_shopgrademygoods_save",
            cnName = "我的积分商品-保存",
            describe = "我的积分商品-保存",
            authzCode = "shop:shopgrademygoods:save"
    )
    @AuthzCode(authzCode = "shop:shopgrademygoods:save")
    public R save(@Valid @RequestBody ShopGradeMyGoodsSaveRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopGradeMyGoodsEntity entity = new ShopGradeMyGoodsEntity();

        entity.setGoodsId(request.getGoodsId());
        entity.setSysUserId(request.getSysUserId());

        shopGradeMyGoodsService.save(entity);

        return R.ok();
    }

    /**
     * 修改
     */
    @Login
    @SysLog("我的积分商品-修改")
    @PostMapping("update")
    @ApiOperation("修改")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_grade_my_goods",
            authoritiesCnName = "我的积分商品权限",
            name = "shop_shopgrademygoods_update",
            cnName = "我的积分商品-修改",
            describe = "我的积分商品-修改",
            authzCode = "shop:shopgrademygoods:update"
    )
    @AuthzCode(authzCode = "shop:shopgrademygoods:update")
    public R update(@Valid @RequestBody ShopGradeMyGoodsUpdateRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        ShopGradeMyGoodsEntity entity = new ShopGradeMyGoodsEntity();


        entity.setId(request.getId());
        entity.setGoodsId(request.getGoodsId());
        entity.setSysUserId(request.getSysUserId());

        shopGradeMyGoodsService.updateById(entity);

        return R.ok();
    }

    /**
     * 删除
     */
    @Login
    @SysLog("我的积分商品-删除")
    @PostMapping("delete")
    @ApiOperation("删除")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_grade_my_goods",
            authoritiesCnName = "我的积分商品权限",
            name = "shop_shopgrademygoods_delete",
            cnName = "我的积分商品-删除",
            describe = "我的积分商品-删除",
            authzCode = "shop:shopgrademygoods:delete"
    )
    @AuthzCode(authzCode = "shop:shopgrademygoods:delete")
    public R delete(@Valid @RequestBody ShopGradeMyGoodsDelRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        shopGradeMyGoodsService.removeById(request.getId());
        return R.ok();
    }

    /**
     * 发货
     */
    @Login
    @SysLog("我的积分商品-发货")
    @PostMapping("send")
    @ApiOperation("发货")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_grade_my_goods",
            authoritiesCnName = "我的积分商品权限",
            name = "shop_shopgrademygoods_send",
            cnName = "我的积分商品-发货",
            describe = "我的积分商品-发货",
            authzCode = "shop:shopgrademygoods:send"
    )
    @AuthzCode(authzCode = "shop:shopgrademygoods:send")
    public R send(@Valid @RequestBody ShopGradeMyGoodsSendRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            shopGradeMyGoodsService.send(request);
            return R.ok();
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }


    @Login
    @SysLog("我的积分商品-发布redis")
    @PostMapping("push")
    @ApiOperation("发布redis")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_grade_my_goods",
            authoritiesCnName = "我的积分商品权限",
            name = "shop_shopgrademygoods_push",
            cnName = "我的积分商品-发布redis",
            describe = "我的积分商品-发布redis",
            authzCode = "shop:shopgrademygoods:push"
    )
    @AuthzCode(authzCode = "shop:shopgrademygoods:push")
    public R push(@Valid @RequestBody ShopGradeMyGoodsPushRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        shopGradeMyGoodsService.push(request);
        return R.ok();
    }


    /**
     * 列表
     */
    @Login
    @SysLog("我的积分商品-列表")
    @PostMapping("myList")
    @ApiOperation("列表")
    @ResponseBody
    @AuthzRegister(
            roleName = "SHOP",
            authoritiesName = "shop_grade_my_goods",
            authoritiesCnName = "我的积分商品权限",
            name = "shop_shopgrademygoods_myList",
            cnName = "我的积分商品-列表",
            describe = "我的积分商品-列表",
            authzCode = "shop:shopgrademygoods:myList"
    )
    @AuthzCode(authzCode = "shop:shopgrademygoods:myList")
    public R myList(@Valid @RequestBody ShopGradeMyGoodsListRequest request, BindingResult result) {
        if (result.hasErrors()) {
            return R.onBindingError(result.getAllErrors());
        }
        try {
            return R.ok().put("data", shopGradeMyGoodsService.myList(request));
        } catch (RRException e) {
            return R.error(e.getCode(), e.getMsg());
        }
    }

}
