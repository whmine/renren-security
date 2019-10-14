package io.renren.modules.sys.service.impl;

import io.renren.modules.sys.request.QueryDictDataRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysDictOptionDao;
import io.renren.modules.sys.entity.SysDictOptionEntity;
import io.renren.modules.sys.service.SysDictOptionService;
import io.renren.modules.sys.request.SysDictOptionPageRequest;

@Slf4j
@Service("sysDictOptionService")
public class SysDictOptionServiceImpl extends ServiceImpl<SysDictOptionDao, SysDictOptionEntity> implements SysDictOptionService {

    @Override
    public Map queryPage(SysDictOptionPageRequest request) {
        Page<SysDictOptionEntity> page = new Page<SysDictOptionEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysDictOptionEntity> queryWrapper = new QueryWrapper<SysDictOptionEntity>();
        if (!StringUtils.isBlank(request.getType())) {
            queryWrapper.eq("TYPE", request.getType());
        }
        queryWrapper.orderByDesc("SORT");
        IPage<SysDictOptionEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 数据字典-列表
     * @param request
     * @return
     */
    @Override
    public List<SysDictOptionEntity> queryDictData(QueryDictDataRequest request) {
        QueryWrapper<SysDictOptionEntity> queryWrapper = new QueryWrapper<SysDictOptionEntity>();
        queryWrapper.eq("TYPE", request.getType());
        queryWrapper.orderByDesc("SORT");
        return baseMapper.selectList(queryWrapper);
    }

}
