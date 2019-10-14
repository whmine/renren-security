package io.renren.modules.device.service.impl;

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

import io.renren.modules.device.dao.DeviceChargeDao;
import io.renren.modules.device.entity.DeviceChargeEntity;
import io.renren.modules.device.service.DeviceChargeService;
import io.renren.modules.device.request.DeviceChargePageRequest;
import io.renren.modules.device.request.DeviceChargePushRequest;


@Slf4j
@Service("deviceChargeService")
public class DeviceChargeServiceImpl extends ServiceImpl<DeviceChargeDao, DeviceChargeEntity> implements DeviceChargeService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map queryPage(DeviceChargePageRequest request) {
        Page<DeviceChargeEntity> page = new Page<DeviceChargeEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<DeviceChargeEntity> queryWrapper = new QueryWrapper<DeviceChargeEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<DeviceChargeEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 充电宝-发布redis
     * @param request
     */
    @Override
    public void push(DeviceChargePushRequest request) {

    }

}
