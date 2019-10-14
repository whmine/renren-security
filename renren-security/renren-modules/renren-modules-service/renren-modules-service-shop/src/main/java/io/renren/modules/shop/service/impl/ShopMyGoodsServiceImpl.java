package io.renren.modules.shop.service.impl;

import io.renren.common.exception.RRException;
import io.renren.modules.shop.entity.ShopGoodsEntity;
import io.renren.modules.shop.entity.ShopGradeGoodsEntity;
import io.renren.modules.shop.request.ShopMyGoodsListRequest;
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

import io.renren.modules.shop.dao.ShopMyGoodsDao;
import io.renren.modules.shop.entity.ShopMyGoodsEntity;
import io.renren.modules.shop.service.ShopMyGoodsService;
import io.renren.modules.shop.request.ShopMyGoodsPageRequest;
import io.renren.modules.shop.request.ShopMyGoodsPushRequest;


@Slf4j
@Service("shopMyGoodsService")
public class ShopMyGoodsServiceImpl extends ServiceImpl<ShopMyGoodsDao, ShopMyGoodsEntity> implements ShopMyGoodsService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SysAttachmentDao sysAttachmentDao;

    @Override
    public Map queryPage(ShopMyGoodsPageRequest request) {
        Page<ShopMyGoodsEntity> page = new Page<ShopMyGoodsEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<ShopMyGoodsEntity> queryWrapper = new QueryWrapper<ShopMyGoodsEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<ShopMyGoodsEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 我的商品-发布redis
     * @param request
     */
    @Override
    public void push(ShopMyGoodsPushRequest request) {

    }

    /***
     * 我的列表
     * @param request
     * @return
     * @throws RRException
     */
    @Override
    public List<Map> myList(ShopMyGoodsListRequest request) throws RRException {
        Map map = new HashMap();
        map.put("sysUserId", request.getUserId());

        List<ShopGoodsEntity> list = baseMapper.myList(map);
        List<Map> mapList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ShopGoodsEntity shopGoodsEntity = list.get(i);
                Map maptemp = new HashMap();
                maptemp.put("ShopGoodsEntity", shopGoodsEntity);

                SysAttachmentEntity sysAttachmentEntity = sysAttachmentDao.selectById(shopGoodsEntity.getOriginalImg());
                maptemp.put("SysAttachmentEntity", sysAttachmentEntity);
                mapList.add(maptemp);
            }

        }

        return mapList;
    }

}
