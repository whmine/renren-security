package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.ShopGradeGoodsEntity;
import io.renren.modules.shop.request.ShopGradeGoodsChangeRequest;
import io.renren.modules.shop.request.ShopGradeGoodsPageRequest;
import io.renren.modules.shop.request.ShopGradeGoodsPushRequest;

import java.util.*;

/**
 * 积分商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
public interface ShopGradeGoodsService extends IService<ShopGradeGoodsEntity> {

    Map queryPage(ShopGradeGoodsPageRequest request);

    /***
     * 积分商品-发布redis
     * @param request
     */
    void push(ShopGradeGoodsPushRequest request);

    /**
     * 兑换
     *
     * @throws RRException
     */
    void change(ShopGradeGoodsChangeRequest request) throws RRException;
}

