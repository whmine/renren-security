package io.renren.modules.shop.service.impl;

import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.exception.RRException;
import io.renren.framework.utils.Producer;
import io.renren.modules.shop.dao.ShopGradeMyGoodsDao;
import io.renren.modules.shop.entity.ShopGoodsEntity;
import io.renren.modules.shop.entity.ShopGradeMyGoodsEntity;
import io.renren.modules.shop.request.ShopGradeGoodsChangeRequest;
import io.renren.modules.sys.dao.SysAttachmentDao;
import io.renren.modules.sys.entity.SysAttachmentEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.math.BigDecimal;
import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.shop.dao.ShopGradeGoodsDao;
import io.renren.modules.shop.entity.ShopGradeGoodsEntity;
import io.renren.modules.shop.service.ShopGradeGoodsService;
import io.renren.modules.shop.request.ShopGradeGoodsPageRequest;
import io.renren.modules.shop.request.ShopGradeGoodsPushRequest;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service("shopGradeGoodsService")
public class ShopGradeGoodsServiceImpl extends ServiceImpl<ShopGradeGoodsDao, ShopGradeGoodsEntity> implements ShopGradeGoodsService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SysAttachmentDao sysAttachmentDao;

    @Autowired
    private ShopGradeMyGoodsDao shopGradeMyGoodsDao;

    @Autowired
    private Producer producer;

    @Override
    public Map queryPage(ShopGradeGoodsPageRequest request) {
        Page<ShopGradeGoodsEntity> page = new Page<ShopGradeGoodsEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<ShopGradeGoodsEntity> queryWrapper = new QueryWrapper<ShopGradeGoodsEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<ShopGradeGoodsEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);

        List<ShopGradeGoodsEntity> list = pageInfo.getRecords();
        List<Map> mapList = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ShopGradeGoodsEntity shopGradeGoodsEntity = list.get(i);
                Map maptemp = new HashMap();
                maptemp.put("ShopGradeGoodsEntity", shopGradeGoodsEntity);

                SysAttachmentEntity sysAttachmentEntity = sysAttachmentDao.selectById(shopGradeGoodsEntity.getOriginalImg());
                maptemp.put("SysAttachmentEntity", sysAttachmentEntity);

                mapList.add(maptemp);
            }
        }
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", mapList);

        return map;
    }

    /***
     * 积分商品-发布redis
     * @param request
     */
    @Override
    public void push(ShopGradeGoodsPushRequest request) {

    }

    /**
     * 兑换
     *
     * @param request
     * @throws RRException
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void change(ShopGradeGoodsChangeRequest request) throws RRException {
        String cuAccountJson = stringRedisTemplate.opsForValue().get(Constants.CU_ACCOUNT + request.getUserId());
        Map cuAccountMap = new Gson().fromJson(cuAccountJson, Map.class);
        ShopGradeGoodsEntity shopGradeGoodsEntity = baseMapper.selectById(request.getId());
        if (shopGradeGoodsEntity == null) {
            throw new RRException("商品不存在");
        }
        shopGradeGoodsEntity.setSalesSum(shopGradeGoodsEntity.getSalesSum() + 1);
        baseMapper.updateById(shopGradeGoodsEntity);

        BigDecimal big = new BigDecimal(cuAccountMap.get("customerGrade") + "").subtract(shopGradeGoodsEntity.getShopPrice());
        if (big.longValue() < 0) {
            throw new RRException("积分不足");
        }
        cuAccountMap.put("customerGrade", big);
        stringRedisTemplate.opsForValue().set(Constants.CU_ACCOUNT + request.getUserId(), new Gson().toJson(cuAccountMap));
        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.cu.customer.account"), new Gson().toJson(cuAccountMap));

        ShopGradeMyGoodsEntity shopGradeMyGoodsEntity = new ShopGradeMyGoodsEntity();
        shopGradeMyGoodsEntity.setGoodsId(request.getId());
        shopGradeMyGoodsEntity.setSysUserId(request.getUserId());
        shopGradeMyGoodsEntity.setAddress(request.getAddress());
        shopGradeMyGoodsEntity.setPhone(request.getPhone());
        shopGradeMyGoodsDao.insert(shopGradeMyGoodsEntity);


        Map<Object, Object> cuProfitEntity = new HashMap<>();
        cuProfitEntity.put("customerId", request.getUserId());
        cuProfitEntity.put("sourceId", shopGradeMyGoodsEntity.getId());
        cuProfitEntity.put("sourceType", "积分兑换");
        cuProfitEntity.put("profitNum", shopGradeGoodsEntity.getShopPrice());
        cuProfitEntity.put("profitType", "0");
        cuProfitEntity.put("profitStatus", "0");
        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.cu.customer.profit"), new Gson().toJson(cuProfitEntity));
    }

}
