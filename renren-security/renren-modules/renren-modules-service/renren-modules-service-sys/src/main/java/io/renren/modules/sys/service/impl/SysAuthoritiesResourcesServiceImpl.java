package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import io.renren.common.exception.RRException;
import io.renren.modules.sys.entity.SysResourcesEntity;
import io.renren.modules.sys.request.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysAuthoritiesResourcesDao;
import io.renren.modules.sys.entity.SysAuthoritiesResourcesEntity;
import io.renren.modules.sys.service.SysAuthoritiesResourcesService;

@Slf4j
@Service("sysAuthoritiesResourcesService")
public class SysAuthoritiesResourcesServiceImpl extends ServiceImpl<SysAuthoritiesResourcesDao, SysAuthoritiesResourcesEntity> implements SysAuthoritiesResourcesService {

    @Override
    public Map queryPage(SysAuthoritiesResourcesPageRequest request) {
        Page<SysAuthoritiesResourcesEntity> page = new Page<SysAuthoritiesResourcesEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysAuthoritiesResourcesEntity> queryWrapper = new QueryWrapper<SysAuthoritiesResourcesEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<SysAuthoritiesResourcesEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 系统权限资源关系-发布redis
     * @param request
     */
    @Override
    public void push(SysAuthoritiesResourcesPushRequest request) {

    }

    /***
     * 系统权限资源关系-删除
     * @param request
     * @throws RRException
     */
    @Override
    public void remove(SysAuthoritiesResourcesDelRequest request) throws RRException {
        QueryWrapper<SysAuthoritiesResourcesEntity> qw = new QueryWrapper<>();
        qw.eq("SYS_AUTHORITIES_ID", request.getSysAuthoritiesId());
        qw.eq("SYS_RESOURCES_ID", request.getSysResourcesId());
        baseMapper.delete(qw);
    }

    /***
     * 系统权限资源关系-待选列表(右)
     * @param request
     * @return
     * @throws RRException
     */
    @Override
    public List<SysResourcesEntity> rightList(SysAuthoritiesResourcesRightPageRequest request) throws RRException {
        Map map = new HashMap();
        map.put("sysAuthoritiesId", request.getSysAuthoritiesId());
        return baseMapper.rightList(map);
    }

    /***
     * 系统权限资源关系-已选列表(左)
     * @param request
     * @return
     * @throws RRException
     */
    @Override
    public List<SysResourcesEntity> leftList(SysAuthoritiesResourcesLeftPageRequest request) throws RRException {
        Map map = new HashMap();
        map.put("sysAuthoritiesId", request.getSysAuthoritiesId());
        return baseMapper.leftList(map);
    }

}
