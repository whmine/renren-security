package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.ShopOrderEntity;
import io.renren.modules.shop.request.ShopOrderMyListRequest;
import io.renren.modules.shop.request.ShopOrderPageRequest;
import io.renren.modules.shop.request.ShopOrderPushRequest;

import java.util.*;

/**
 * 订单
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
public interface ShopOrderService extends IService<ShopOrderEntity> {

    Map queryPage(ShopOrderPageRequest request);

    List<Map> myList(ShopOrderMyListRequest request) throws RRException;

    /***
     * 订单-发布redis
     * @param request
     */
    void push(ShopOrderPushRequest request);
}

