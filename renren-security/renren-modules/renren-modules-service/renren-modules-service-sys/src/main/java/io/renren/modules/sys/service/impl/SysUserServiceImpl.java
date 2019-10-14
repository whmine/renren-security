package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.exception.RRException;
import io.renren.framework.utils.Producer;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.entity.SysUserRoleEntity;
import io.renren.modules.sys.request.*;
import io.renren.modules.sys.service.SysResourcesService;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysUserDao;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private SysResourcesService sysResourcesService;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private Producer producer;

    @Override
    public Map queryPage(SysUserPageRequest request) {
        Page<SysUserEntity> page = new Page<SysUserEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<SysUserEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<SysUserEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 缓存用户信息
     * @return
     */
    @Override
    public List<SysUserEntity> sysUserCacheManager() {
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<>();
        return baseMapper.selectList(queryWrapper);
    }

    /***
     * 权限发布redis
     * @param entity
     */
    @Override
    public void authCacheManager(SysUserEntity entity) {
        stringRedisTemplate.opsForValue().set(Constants.SYS_USER_NAME + entity.getName(), new Gson().toJson(entity));
        stringRedisTemplate.opsForValue().set(Constants.SYS_USER_ID + entity.getId(), new Gson().toJson(entity));

        log.debug("缓存管理-->缓存初始化:初始化用户所属所有角色信息!UserId=" + entity.getId());
        SysRoleCacheRequest request = new SysRoleCacheRequest();
        request.setUserId(entity.getId());
        List<SysRoleEntity> sysRoleEntityList = sysRoleService.sysRoleCacheManager(request);
        stringRedisTemplate.opsForValue().set(Constants.SYS_ROLE_LIST + entity.getId(), new Gson().toJson(sysRoleEntityList));

        List<Map> sysResourcesEntityList = sysResourcesService.sysResourcesCacheManager(entity.getId());
        log.debug("缓存管理-->缓存初始化:初始化资源信息!KEY:" + entity.getId() + "VALUE:" + new Gson().toJson(sysResourcesEntityList));
        stringRedisTemplate.opsForValue().set(Constants.SYS_RESOURCES + entity.getId(), new Gson().toJson(sysResourcesEntityList));
    }

    /***
     * 系统用户表-发布redis
     * @param request
     */
    @Override
    public void push(SysUserPushRequest request) {
        SysUserEntity entity = baseMapper.selectById(request.getId());
        authCacheManager(entity);

    }

    /***
     * 系统用户表-删除数据并取消发布redis
     * @param request
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void remove(SysUserDelRequest request) {
        SysUserEntity entity = baseMapper.selectById(request.getId());
        baseMapper.deleteById(entity.getId());

        QueryWrapper<SysUserRoleEntity> qw = new QueryWrapper<>();
        qw.eq("SYS_USER_ID", entity.getId());
        sysUserRoleService.remove(qw);
//        取消用户信息
        stringRedisTemplate.delete(Constants.SYS_USER_NAME + entity.getName());
        stringRedisTemplate.delete(Constants.SYS_USER_ID + entity.getId());

//      取消角色信息
        stringRedisTemplate.delete(Constants.SYS_ROLE_LIST + entity.getId());
//      取消资源信息
        stringRedisTemplate.delete(Constants.SYS_RESOURCES + entity.getId());
//        TOKEN
        String tokenMapJson = stringRedisTemplate.opsForValue().get(Constants.X_OAUTH_TOKEN_USERID + entity.getId());
        if (!StringUtils.isBlank(tokenMapJson)) {
            stringRedisTemplate.delete(Constants.X_OAUTH_TOKEN_USERID + entity.getId());
            Map tokenMap = new Gson().fromJson(tokenMapJson, Map.class);
            stringRedisTemplate.delete(Constants.X_OAUTH_TOKEN_TOKEN + tokenMap.get("token"));
            stringRedisTemplate.delete(Constants.X_OAUTH_TOKEN_USERID + entity.getId());
            stringRedisTemplate.delete(Constants.X_AUTHZ_CODE + entity.getId());
        }


        Map cuInfo = new HashMap();
        cuInfo.put("sysUserId", entity.getId());
        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.cu.customer.del"), new Gson().toJson(cuInfo));
    }

    /***
     * 新增用户信息及客户信息
     * @param request
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void save(SysUserSaveRequest request) {
        SysUserEntity entity = new SysUserEntity();

        entity.setName(request.getName());
        entity.setCnName(request.getCnName());
        if (!StringUtils.isBlank(request.getSysPasswd())) {
            String sysPasswd = DigestUtils.sha256Hex(request.getSysPasswd());
            entity.setSysPasswd(sysPasswd);
        }

        baseMapper.insert(entity);

        Map sysOauthCodeEntity = new HashMap();
        sysOauthCodeEntity.put("sysUserId", entity.getId());
        sysOauthCodeEntity.put("name", entity.getName());
        sysOauthCodeEntity.put("url", "e42b36d7c1164432a0f87b8b027e5efb.jpg");
        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.cu.customer"), sysOauthCodeEntity);
    }

}
