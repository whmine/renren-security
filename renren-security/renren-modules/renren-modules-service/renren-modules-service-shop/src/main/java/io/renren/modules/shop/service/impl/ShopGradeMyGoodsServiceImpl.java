package io.renren.modules.shop.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import io.renren.common.exception.RRException;
import io.renren.modules.shop.dao.ShopGradeGoodsDao;
import io.renren.modules.shop.entity.ShopGradeGoodsEntity;
import io.renren.modules.shop.request.ShopGradeMyGoodsListRequest;
import io.renren.modules.shop.request.ShopGradeMyGoodsSendRequest;
import io.renren.modules.shop.service.ShopGradeGoodsService;
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

import io.renren.modules.shop.dao.ShopGradeMyGoodsDao;
import io.renren.modules.shop.entity.ShopGradeMyGoodsEntity;
import io.renren.modules.shop.service.ShopGradeMyGoodsService;
import io.renren.modules.shop.request.ShopGradeMyGoodsPageRequest;
import io.renren.modules.shop.request.ShopGradeMyGoodsPushRequest;


@Slf4j
@Service("shopGradeMyGoodsService")
public class ShopGradeMyGoodsServiceImpl extends ServiceImpl<ShopGradeMyGoodsDao, ShopGradeMyGoodsEntity> implements ShopGradeMyGoodsService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SysAttachmentDao sysAttachmentDao;
    @Autowired
    private ShopGradeGoodsDao shopGradeGoodsDao;

    @Override
    public Map queryPage(ShopGradeMyGoodsPageRequest request) {
        Page<ShopGradeMyGoodsEntity> page = new Page<ShopGradeMyGoodsEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<ShopGradeMyGoodsEntity> queryWrapper = new QueryWrapper<ShopGradeMyGoodsEntity>();
        queryWrapper.eq("GRADE_STATUS", request.getType());
        queryWrapper.orderByDesc("SORT");
        IPage<ShopGradeMyGoodsEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        List<ShopGradeMyGoodsEntity> list = pageInfo.getRecords();
        List<Map> mapList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ShopGradeMyGoodsEntity shopGradeMyGoodsEntity = list.get(i);
                Map maptemp = new HashMap();
                ShopGradeGoodsEntity shopGradeGoodsEntity = shopGradeGoodsDao.selectById(shopGradeMyGoodsEntity.getGoodsId());
                maptemp.put("ShopGradeMyGoodsEntity", shopGradeMyGoodsEntity);
                maptemp.put("ShopGradeGoodsEntity", shopGradeGoodsEntity);

                SysAttachmentEntity sysAttachmentEntity = sysAttachmentDao.selectById(shopGradeGoodsEntity.getOriginalImg());
                maptemp.put("SysAttachmentEntity", sysAttachmentEntity);
                mapList.add(maptemp);
            }

        }

        map.put("items", mapList);

        return map;
    }

    /***
     * 我的积分商品-发布redis
     * @param request
     */
    @Override
    public void push(ShopGradeMyGoodsPushRequest request) {

    }

    /**
     * 我的列表
     *
     * @param request
     * @throws RRException
     */
    @Override
    public List<Map> myList(ShopGradeMyGoodsListRequest request) throws RRException {

        Map map = new HashMap();
        map.put("sysUserId", request.getUserId());

//        List<ShopGradeGoodsEntity> list = baseMapper.myList(map);
        QueryWrapper<ShopGradeMyGoodsEntity> qw = new QueryWrapper<>();
        qw.eq("SYS_USER_ID", request.getUserId());
        List<ShopGradeMyGoodsEntity> list = baseMapper.selectList(qw);
        List<Map> mapList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ShopGradeMyGoodsEntity shopGradeMyGoodsEntity = list.get(i);
                Map maptemp = new HashMap();
                ShopGradeGoodsEntity shopGradeGoodsEntity = shopGradeGoodsDao.selectById(shopGradeMyGoodsEntity.getGoodsId());
                maptemp.put("ShopGradeMyGoodsEntity", shopGradeMyGoodsEntity);
                maptemp.put("ShopGradeGoodsEntity", shopGradeGoodsEntity);

                SysAttachmentEntity sysAttachmentEntity = sysAttachmentDao.selectById(shopGradeGoodsEntity.getOriginalImg());
                maptemp.put("SysAttachmentEntity", sysAttachmentEntity);
                mapList.add(maptemp);
            }

        }

        return mapList;
    }

    /***
     * 发货
     * @param request
     * @throws RRException
     */
    @Override
    public void send(ShopGradeMyGoodsSendRequest request) throws RRException {
        ShopGradeMyGoodsEntity entity = baseMapper.selectById(request.getId());
        entity.setGradeStatus("1");
        baseMapper.updateById(entity);
    }

}
