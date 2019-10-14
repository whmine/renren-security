package io.renren.modules.sys.service.impl;

import io.renren.modules.sys.request.SysAuthoritiesPushRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysAuthoritiesDao;
import io.renren.modules.sys.entity.SysAuthoritiesEntity;
import io.renren.modules.sys.service.SysAuthoritiesService;
import io.renren.modules.sys.request.SysAuthoritiesPageRequest;

@Slf4j
@Service("sysAuthoritiesService")
public class SysAuthoritiesServiceImpl extends ServiceImpl<SysAuthoritiesDao, SysAuthoritiesEntity> implements SysAuthoritiesService {

    @Override
    public Map queryPage(SysAuthoritiesPageRequest request) {
        Page<SysAuthoritiesEntity> page = new Page<SysAuthoritiesEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysAuthoritiesEntity> queryWrapper = new QueryWrapper<SysAuthoritiesEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<SysAuthoritiesEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 系统权限-发布redis
     * @param request
     */
    @Override
    public void push(SysAuthoritiesPushRequest request) {

    }

}
