package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.ShopGradeMyGoodsEntity;
import io.renren.modules.shop.request.ShopGradeMyGoodsListRequest;
import io.renren.modules.shop.request.ShopGradeMyGoodsPageRequest;
import io.renren.modules.shop.request.ShopGradeMyGoodsPushRequest;
import io.renren.modules.shop.request.ShopGradeMyGoodsSendRequest;

import java.util.*;

/**
 * 我的积分商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
public interface ShopGradeMyGoodsService extends IService<ShopGradeMyGoodsEntity> {

    Map queryPage(ShopGradeMyGoodsPageRequest request);

    /***
     * 我的积分商品-发布redis
     * @param request
     */
    void push(ShopGradeMyGoodsPushRequest request);

    /**
     * 我的列表
     *
     * @throws RRException
     */
    List<Map> myList(ShopGradeMyGoodsListRequest request) throws RRException;


    /***
     * 发货
     * @param request
     * @throws RRException
     */
    void send(ShopGradeMyGoodsSendRequest request) throws RRException;
}

