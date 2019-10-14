package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.ShopPayEntity;
import io.renren.modules.shop.request.ShopPayPageRequest;
import io.renren.modules.shop.request.ShopPayPushRequest;
import java.util.*;

/**
 * 支付订单
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:44
 */
public interface ShopPayService extends IService<ShopPayEntity> {

    Map queryPage(ShopPayPageRequest request);

    /***
     * 支付订单-发布redis
     * @param request
     */
    void push(ShopPayPushRequest request);
}

