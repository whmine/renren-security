package io.renren.modules.sys.service.impl;

import io.renren.modules.sys.entity.SysResourcesEntity;
import io.renren.modules.sys.request.SysRoleCacheRequest;
import io.renren.modules.sys.request.SysRolePushRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysRoleDao;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.request.SysRolePageRequest;

@Slf4j
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleDao, SysRoleEntity> implements SysRoleService {

    @Override
    public Map queryPage(SysRolePageRequest request) {
        Page<SysRoleEntity> page = new Page<SysRoleEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysRoleEntity> queryWrapper = new QueryWrapper<SysRoleEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<SysRoleEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 缓存用户角色信息
     * @return
     * @param request
     */
    @Override
    public List<SysRoleEntity> sysRoleCacheManager(SysRoleCacheRequest request) {
        Map map = new HashMap();
        map.put("sysUserId", request.getUserId());
        return baseMapper.sysRoleCacheManager(map);
    }

    /***
     * 缓存角色资源信息
     * @return
     */
    @Override
    public List<SysResourcesEntity> sysRoleResourcesCacheManager(SysRoleEntity entity) {
        Map map = new HashMap();
        map.put("sysRoleId", entity.getId());
        return baseMapper.sysRoleResourcesCacheManager(map);
    }

    /***
     * 系统角色-发布redis
     * @param request
     */
    @Override
    public void push(SysRolePushRequest request) {

    }

}
