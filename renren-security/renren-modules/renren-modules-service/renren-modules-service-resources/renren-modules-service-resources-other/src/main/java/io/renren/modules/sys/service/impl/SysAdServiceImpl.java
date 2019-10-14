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

import io.renren.modules.sys.dao.SysAdDao;
import io.renren.modules.sys.entity.SysAdEntity;
import io.renren.modules.sys.service.SysAdService;
import io.renren.modules.sys.request.SysAdPageRequest;

@Slf4j
@Service("sysAdService")
public class SysAdServiceImpl extends ServiceImpl<SysAdDao, SysAdEntity> implements SysAdService {

    @Override
    public Map queryPage(SysAdPageRequest request) {
        Page<SysAdEntity> page = new Page<SysAdEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysAdEntity> queryWrapper = new QueryWrapper<SysAdEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<SysAdEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

}
