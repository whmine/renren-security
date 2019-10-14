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

import io.renren.modules.device.dao.DeviceCabinetDao;
import io.renren.modules.device.entity.DeviceCabinetEntity;
import io.renren.modules.device.service.DeviceCabinetService;
import io.renren.modules.device.request.DeviceCabinetPageRequest;
import io.renren.modules.device.request.DeviceCabinetPushRequest;


@Slf4j
@Service("deviceCabinetService")
public class DeviceCabinetServiceImpl extends ServiceImpl<DeviceCabinetDao, DeviceCabinetEntity> implements DeviceCabinetService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map queryPage(DeviceCabinetPageRequest request) {
        Page<DeviceCabinetEntity> page = new Page<DeviceCabinetEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<DeviceCabinetEntity> queryWrapper = new QueryWrapper<DeviceCabinetEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<DeviceCabinetEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 机柜-发布redis
     * @param request
     */
    @Override
    public void push(DeviceCabinetPushRequest request) {

    }

}
