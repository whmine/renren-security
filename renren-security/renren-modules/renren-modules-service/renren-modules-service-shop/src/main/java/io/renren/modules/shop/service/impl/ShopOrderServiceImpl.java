package io.renren.modules.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import io.renren.common.exception.RRException;
import io.renren.modules.shop.entity.ShopGoodsEntity;
import io.renren.modules.shop.request.ShopOrderMyListRequest;
import io.renren.modules.sys.dao.SysAttachmentDao;
import io.renren.modules.sys.entity.SysAttachmentEntity;
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

import io.renren.modules.shop.dao.ShopOrderDao;
import io.renren.modules.shop.entity.ShopOrderEntity;
import io.renren.modules.shop.service.ShopOrderService;
import io.renren.modules.shop.request.ShopOrderPageRequest;
import io.renren.modules.shop.request.ShopOrderPushRequest;


@Slf4j
@Service("shopOrderService")
public class ShopOrderServiceImpl extends ServiceImpl<ShopOrderDao, ShopOrderEntity> implements ShopOrderService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SysAttachmentDao sysAttachmentDao;

    @Override
    public Map queryPage(ShopOrderPageRequest request) {
        Page<ShopOrderEntity> page = new Page<ShopOrderEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<ShopOrderEntity> queryWrapper = new QueryWrapper<ShopOrderEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<ShopOrderEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    @Override
    public List<Map> myList(ShopOrderMyListRequest request) throws RRException {
        Map map = new HashMap();
        map.put("sysUserId", request.getUserId());
        map.put("type", request.getType());
        List<Map> mapList = new ArrayList<>();
        List<ShopGoodsEntity> list = baseMapper.myList(map);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ShopGoodsEntity shopGoodsEntity = list.get(i);
                SysAttachmentEntity entity = sysAttachmentDao.selectById(shopGoodsEntity.getOriginalImg());
                Map mapTemp = new HashMap();
                mapTemp.put("ShopGoodsEntity", shopGoodsEntity);
                mapTemp.put("SysAttachmentEntity", entity);
                mapList.add(mapTemp);
            }

        }
        return mapList;
    }

    /***
     * 订单-发布redis
     * @param request
     */
    @Override
    public void push(ShopOrderPushRequest request) {

    }

}
