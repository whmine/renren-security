package io.renren.modules.shop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.shop.entity.ShopGoodsEntity;
import io.renren.modules.shop.request.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 商品
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-28 19:54:45
 */
public interface ShopGoodsService extends IService<ShopGoodsEntity> {

    Map queryPage(ShopGoodsPageRequest request) throws RRException;

    Map info(ShopGoodsInfoRequest request) throws RRException;

    /***
     * 商品-发布redis
     * @param request
     */
    void push(ShopGoodsPushRequest request);

    /**
     * 购买
     *
     * @param request
     * @throws RRException
     */
    Map<String, String> buy(ShopGoodsBuyRequest request, HttpServletRequest httpRequest) throws RRException;


    /***
     * 购买初始化
     * @param request
     * @return
     * @throws RRException
     */
    Map<String, String> configInfo(ShopGoodsConfigInfoRequest request) throws RRException;

    /***
     * 回调
     * @param request
     * @param response
     * @return
     */
    String notify(HttpServletRequest request, HttpServletResponse response);

}

