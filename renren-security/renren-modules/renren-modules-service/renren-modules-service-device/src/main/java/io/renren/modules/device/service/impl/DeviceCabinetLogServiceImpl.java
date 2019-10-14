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

import io.renren.modules.device.dao.DeviceCabinetLogDao;
import io.renren.modules.device.entity.DeviceCabinetLogEntity;
import io.renren.modules.device.service.DeviceCabinetLogService;
import io.renren.modules.device.request.DeviceCabinetLogPageRequest;
import io.renren.modules.device.request.DeviceCabinetLogPushRequest;


@Slf4j
@Service("deviceCabinetLogService")
public class DeviceCabinetLogServiceImpl extends ServiceImpl<DeviceCabinetLogDao, DeviceCabinetLogEntity> implements DeviceCabinetLogService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map queryPage(DeviceCabinetLogPageRequest request) {
        Page<DeviceCabinetLogEntity> page = new Page<DeviceCabinetLogEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<DeviceCabinetLogEntity> queryWrapper = new QueryWrapper<DeviceCabinetLogEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<DeviceCabinetLogEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 机柜租借-发布redis
     * @param request
     */
    @Override
    public void push(DeviceCabinetLogPushRequest request) {

    }

}
