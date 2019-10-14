package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import io.renren.modules.sys.request.SysConfigfindNameInfoRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysConfigDao;
import io.renren.modules.sys.entity.SysConfigEntity;
import io.renren.modules.sys.service.SysConfigService;
import io.renren.modules.sys.request.SysConfigPageRequest;

@Slf4j
@Service("sysConfigService")
public class SysConfigServiceImpl extends ServiceImpl<SysConfigDao, SysConfigEntity> implements SysConfigService {

    @Override
    public Map queryPage(SysConfigPageRequest request) {
        Page<SysConfigEntity> page = new Page<SysConfigEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysConfigEntity> queryWrapper = new QueryWrapper<SysConfigEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<SysConfigEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 缓存配置信息
     * @return
     */
    @Override
    public List<SysConfigEntity> sysConfigCacheManager() {
        QueryWrapper<SysConfigEntity> queryWrapper = new QueryWrapper<SysConfigEntity>();
        return baseMapper.selectList(queryWrapper);
    }

    /***
     * 根据名称查询详情
     * @param request
     * @return
     */
    @Override
    public SysConfigEntity findNameInfo(SysConfigfindNameInfoRequest request) {
        QueryWrapper<SysConfigEntity> qw = new QueryWrapper<>();
        qw.eq("name", request.getName());
        return baseMapper.selectOne(qw);
    }

}
