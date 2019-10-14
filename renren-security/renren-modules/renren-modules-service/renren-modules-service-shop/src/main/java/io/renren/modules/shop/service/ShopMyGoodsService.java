package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.ShopMyGoodsEntity;
import io.renren.modules.shop.request.ShopMyGoodsListRequest;
import io.renren.modules.shop.request.ShopMyGoodsPageRequest;
import io.renren.modules.shop.request.ShopMyGoodsPushRequest;

import java.util.*;

/**
 * 我的商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
public interface ShopMyGoodsService extends IService<ShopMyGoodsEntity> {

    Map queryPage(ShopMyGoodsPageRequest request);

    /***
     * 我的商品-发布redis
     * @param request
     */
    void push(ShopMyGoodsPushRequest request);

    /***
     * 我的列表
     * @param request
     * @return
     * @throws RRException
     */
    List<Map> myList(ShopMyGoodsListRequest request) throws RRException;
}

