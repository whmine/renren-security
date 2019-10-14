package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysResourcesEntity;
import io.renren.modules.sys.request.SysResourcesPageRequest;
import io.renren.modules.sys.request.SysResourcesPushRequest;

import java.util.*;

/**
 * 系统资源
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */
public interface SysResourcesService extends IService<SysResourcesEntity> {

    Map queryPage(SysResourcesPageRequest request);


    /***
     *  缓存用户所有资源
     * @param userId
     * @return
     */
    List<Map> sysResourcesCacheManager(String userId);


    /***
     * 创建资源信息
     * @param entity
     */
    void createSysResources(Map entity);

    /***
     * 系统资源-发布redis
     * @param request
     */
    void push(SysResourcesPushRequest request);


}

