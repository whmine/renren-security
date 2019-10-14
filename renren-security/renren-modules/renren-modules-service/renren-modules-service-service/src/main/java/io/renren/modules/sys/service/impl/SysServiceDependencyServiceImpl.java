package io.renren.modules.sys.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysServiceDependencyDao;
import io.renren.modules.sys.entity.SysServiceDependencyEntity;
import io.renren.modules.sys.service.SysServiceDependencyService;
import io.renren.modules.sys.request.SysServiceDependencyPageRequest;

@Slf4j
@Service("sysServiceDependencyService")
public class SysServiceDependencyServiceImpl extends ServiceImpl<SysServiceDependencyDao, SysServiceDependencyEntity> implements SysServiceDependencyService {

    @Override
    public Map queryPage(SysServiceDependencyPageRequest request) {
        Page<SysServiceDependencyEntity> page = new Page<SysServiceDependencyEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysServiceDependencyEntity> queryWrapper = new QueryWrapper<SysServiceDependencyEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<SysServiceDependencyEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

}
