package io.renren.modules.shop.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.shop.dao.ShopPayDao;
import io.renren.modules.shop.entity.ShopPayEntity;
import io.renren.modules.shop.service.ShopPayService;
import io.renren.modules.shop.request.ShopPayPageRequest;
import io.renren.modules.shop.request.ShopPayPushRequest;


@Slf4j
@Service("shopPayService")
public class ShopPayServiceImpl extends ServiceImpl<ShopPayDao, ShopPayEntity> implements ShopPayService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map queryPage(ShopPayPageRequest request) {
        Page<ShopPayEntity> page = new Page<ShopPayEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<ShopPayEntity> queryWrapper = new QueryWrapper<ShopPayEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<ShopPayEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 支付订单-发布redis
     * @param request
     */
    @Override
    public void push(ShopPayPushRequest request) {

    }

}
