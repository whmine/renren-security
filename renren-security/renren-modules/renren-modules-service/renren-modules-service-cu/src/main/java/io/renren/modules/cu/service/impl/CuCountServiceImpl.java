package io.renren.modules.cu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cu.dao.CuCountDao;
import io.renren.modules.cu.entity.CuCountEntity;
import io.renren.modules.cu.service.CuCountService;
import io.renren.modules.cu.request.CuCountPageRequest;

@Slf4j
@Service("cuCountService")
public class CuCountServiceImpl extends ServiceImpl<CuCountDao, CuCountEntity> implements CuCountService {

    @Override
    public Map queryPage(CuCountPageRequest request) {
        Page<CuCountEntity> page = new Page<CuCountEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<CuCountEntity> queryWrapper = new QueryWrapper<CuCountEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<CuCountEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

}
