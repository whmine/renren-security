package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.request.SysUserDelRequest;
import io.renren.modules.sys.request.SysUserPageRequest;
import io.renren.modules.sys.request.SysUserPushRequest;
import io.renren.modules.sys.request.SysUserSaveRequest;

import java.util.*;

/**
 * 系统用户表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */
public interface SysUserService extends IService<SysUserEntity> {

    Map queryPage(SysUserPageRequest request);

    /***
     * 缓存用户信息
     * @return
     */
    List<SysUserEntity> sysUserCacheManager();

    /***
     * 权限发布redis
     * @param entity
     */
    void authCacheManager(SysUserEntity entity);

    /***
     * 系统用户表-发布redis
     * @param request
     */
    void push(SysUserPushRequest request);

    /***
     * 系统用户表-删除数据并取消发布redis
     * @param request
     */
    void remove(SysUserDelRequest request);

    /***
     * 新增用户信息及客户信息
     * @param request
     */
    void save(SysUserSaveRequest request);

}

