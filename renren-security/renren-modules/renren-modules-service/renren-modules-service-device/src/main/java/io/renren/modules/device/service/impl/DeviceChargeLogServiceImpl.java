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

import io.renren.modules.device.dao.DeviceChargeLogDao;
import io.renren.modules.device.entity.DeviceChargeLogEntity;
import io.renren.modules.device.service.DeviceChargeLogService;
import io.renren.modules.device.request.DeviceChargeLogPageRequest;
import io.renren.modules.device.request.DeviceChargeLogPushRequest;


@Slf4j
@Service("deviceChargeLogService")
public class DeviceChargeLogServiceImpl extends ServiceImpl<DeviceChargeLogDao, DeviceChargeLogEntity> implements DeviceChargeLogService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map queryPage(DeviceChargeLogPageRequest request) {
        Page<DeviceChargeLogEntity> page = new Page<DeviceChargeLogEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<DeviceChargeLogEntity> queryWrapper = new QueryWrapper<DeviceChargeLogEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<DeviceChargeLogEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 充电宝租借-发布redis
     * @param request
     */
    @Override
    public void push(DeviceChargeLogPushRequest request) {

    }

}
