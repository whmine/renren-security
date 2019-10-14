package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import io.renren.modules.sys.dao.*;
import io.renren.modules.sys.entity.*;
import io.renren.modules.sys.request.SysResourcesPushRequest;
import io.renren.modules.sys.service.SysRoleAuthoritiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.service.SysResourcesService;
import io.renren.modules.sys.request.SysResourcesPageRequest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("sysResourcesService")
public class SysResourcesServiceImpl extends ServiceImpl<SysResourcesDao, SysResourcesEntity> implements SysResourcesService {


    @Autowired
    SysAuthoritiesResourcesDao sysAuthoritiesResourcesDao;
    @Autowired
    SysAuthoritiesDao sysAuthoritiesDao;
    @Autowired
    SysRoleAuthoritiesDao sysRoleAuthoritiesDao;
    @Autowired
    SysRoleDao sysRoleDao;

    @Override
    public Map queryPage(SysResourcesPageRequest request) {
        Page<SysResourcesEntity> page = new Page<SysResourcesEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysResourcesEntity> queryWrapper = new QueryWrapper<SysResourcesEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<SysResourcesEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     *  缓存用户所有资源
     * @param userId
     * @return
     */
    @Override
    public List<Map> sysResourcesCacheManager(String userId) {
        Map map = new HashMap();
        map.put("sysUserId", userId);
        return baseMapper.selectByUserId(map);
    }

    /***
     * 创建资源信息
     * @param entity
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void createSysResources(Map entity) {
        QueryWrapper<SysResourcesEntity> qw = new QueryWrapper<>();
        qw.eq("VALUE", entity.get("value"));
        SysResourcesEntity sysResourcesEntity = baseMapper.selectOne(qw);
        String id = "";
        if (sysResourcesEntity == null) {
            sysResourcesEntity = new SysResourcesEntity();
            sysResourcesEntity.setName(entity.get("name") + "");
            sysResourcesEntity.setCnName(entity.get("cnName") + "");
            sysResourcesEntity.setValue(entity.get("value") + "");
            sysResourcesEntity.setType(entity.get("type") + "");
            sysResourcesEntity.setIsSys(entity.get("isSys") + "");
            baseMapper.insert(sysResourcesEntity);
            id = sysResourcesEntity.getId();
        } else {
            id = sysResourcesEntity.getId();
            sysResourcesEntity.setName(entity.get("name") + "");
            sysResourcesEntity.setCnName(entity.get("cnName") + "");
            sysResourcesEntity.setValue(entity.get("value") + "");
            sysResourcesEntity.setType(entity.get("type") + "");
            sysResourcesEntity.setIsSys(entity.get("isSys") + "");
            baseMapper.updateById(sysResourcesEntity);
        }

        //添加权限
        QueryWrapper<SysAuthoritiesEntity> aqw = new QueryWrapper<>();
        aqw.eq("name", entity.get("authoritiesName") + "");
        SysAuthoritiesEntity sysAuthoritiesEntity = sysAuthoritiesDao.selectOne(aqw);

        String sysAuthoritiesEntityId = "";
        if (sysAuthoritiesEntity == null) {
            sysAuthoritiesEntity = new SysAuthoritiesEntity();
            sysAuthoritiesEntity.setName(entity.get("authoritiesName") + "");
            sysAuthoritiesEntity.setCnName(entity.get("authoritiesCnName") + "");
            sysAuthoritiesEntity.setAuthorities(entity.get("authoritiesName") + "");
            sysAuthoritiesEntity.setIsSys("N");
            sysAuthoritiesDao.insert(sysAuthoritiesEntity);
            sysAuthoritiesEntityId = sysAuthoritiesEntity.getId();
        } else {
            sysAuthoritiesEntityId = sysAuthoritiesEntity.getId();
        }

        QueryWrapper<SysAuthoritiesResourcesEntity> aQueryWrapper = new QueryWrapper<>();
        aQueryWrapper.eq("SYS_AUTHORITIES_ID", sysAuthoritiesEntityId);
        aQueryWrapper.eq("SYS_RESOURCES_ID", id);
        SysAuthoritiesResourcesEntity sysAuthoritiesResourcesEntity = sysAuthoritiesResourcesDao.selectOne(aQueryWrapper);

        if (sysAuthoritiesResourcesEntity == null) {
            //绑定权限资源
            sysAuthoritiesResourcesEntity = new SysAuthoritiesResourcesEntity();
            sysAuthoritiesResourcesEntity.setSysAuthoritiesId(sysAuthoritiesEntityId);
            sysAuthoritiesResourcesEntity.setSysResourcesId(id);
            sysAuthoritiesResourcesDao.insert(sysAuthoritiesResourcesEntity);
        }

        //添加角色
        QueryWrapper<SysRoleEntity> rqw = new QueryWrapper<>();
        rqw.eq("name", entity.get("roleName") + "");
        SysRoleEntity sysRoleEntity = sysRoleDao.selectOne(rqw);
        String sysRoleEntityId = "";
        if (sysRoleEntity == null) {
            sysRoleEntity = new SysRoleEntity();
            sysRoleEntity.setName(entity.get("roleName") + "");
            sysRoleEntity.setCnName(entity.get("roleCnName") + "");
            sysRoleEntity.setIsSys("N");
            sysRoleDao.insert(sysRoleEntity);
            sysRoleEntityId = sysRoleEntity.getId();
        } else {
            sysRoleEntityId = sysRoleEntity.getId();
        }

        //绑定角色权限
        QueryWrapper<SysRoleAuthoritiesEntity> rqqw = new QueryWrapper<>();
        rqqw.eq("SYS_ROLE_ID", sysRoleEntityId);
        rqqw.eq("SYS_AUTHORITIES_ID", sysAuthoritiesEntityId);
        SysRoleAuthoritiesEntity sysRoleAuthoritiesEntity = sysRoleAuthoritiesDao.selectOne(rqqw);

        if (sysRoleAuthoritiesEntity == null) {
            sysRoleAuthoritiesEntity = new SysRoleAuthoritiesEntity();
            sysRoleAuthoritiesEntity.setSysAuthoritiesId(sysAuthoritiesEntityId);
            sysRoleAuthoritiesEntity.setSysRoleId(sysRoleEntityId);
            sysRoleAuthoritiesDao.insert(sysRoleAuthoritiesEntity);
        }


    }

    /***
     * 系统资源-发布redis
     * @param request
     */
    @Override
    public void push(SysResourcesPushRequest request) {

    }

}
