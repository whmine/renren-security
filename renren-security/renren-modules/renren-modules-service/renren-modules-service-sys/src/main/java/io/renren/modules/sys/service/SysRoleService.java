package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysResourcesEntity;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.request.SysRoleCacheRequest;
import io.renren.modules.sys.request.SysRolePageRequest;
import io.renren.modules.sys.request.SysRolePushRequest;

import java.util.*;

/**
 * 系统角色
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    Map queryPage(SysRolePageRequest request);

    /***
     * 缓存用户角色信息
     * @return
     */
    List<SysRoleEntity> sysRoleCacheManager(SysRoleCacheRequest request);


    /***
     * 缓存角色资源信息
     * @return
     */
    List<SysResourcesEntity> sysRoleResourcesCacheManager(SysRoleEntity entity);

    /***
     * 系统角色-发布redis
     * @param request
     */
    void push(SysRolePushRequest request);
}

