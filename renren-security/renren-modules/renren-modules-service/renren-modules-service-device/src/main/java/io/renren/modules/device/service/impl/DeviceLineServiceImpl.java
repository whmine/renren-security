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

import io.renren.modules.device.dao.DeviceLineDao;
import io.renren.modules.device.entity.DeviceLineEntity;
import io.renren.modules.device.service.DeviceLineService;
import io.renren.modules.device.request.DeviceLinePageRequest;
import io.renren.modules.device.request.DeviceLinePushRequest;


@Slf4j
@Service("deviceLineService")
public class DeviceLineServiceImpl extends ServiceImpl<DeviceLineDao, DeviceLineEntity> implements DeviceLineService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map queryPage(DeviceLinePageRequest request) {
        Page<DeviceLineEntity> page = new Page<DeviceLineEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<DeviceLineEntity> queryWrapper = new QueryWrapper<DeviceLineEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<DeviceLineEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 密码线-发布redis
     * @param request
     */
    @Override
    public void push(DeviceLinePushRequest request) {

    }

}
