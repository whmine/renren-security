package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysAuthoritiesResourcesEntity;
import io.renren.modules.sys.entity.SysResourcesEntity;
import io.renren.modules.sys.request.*;

import java.util.*;

/**
 * 系统权限资源关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */
public interface SysAuthoritiesResourcesService extends IService<SysAuthoritiesResourcesEntity> {

    Map queryPage(SysAuthoritiesResourcesPageRequest request);

    /***
     * 系统权限资源关系-发布redis
     * @param request
     */
    void push(SysAuthoritiesResourcesPushRequest request);

    /***
     * 系统权限资源关系-删除
     * @param request
     * @throws RRException
     */
    void remove(SysAuthoritiesResourcesDelRequest request) throws RRException;

    /***
     * 系统权限资源关系-待选列表(右)
     * @param request
     * @return
     * @throws RRException
     */
    List<SysResourcesEntity> rightList(SysAuthoritiesResourcesRightPageRequest request) throws RRException;

    /***
     * 系统权限资源关系-已选列表(左)
     * @param request
     * @return
     * @throws RRException
     */
    List<SysResourcesEntity> leftList(SysAuthoritiesResourcesLeftPageRequest request) throws RRException;
}

