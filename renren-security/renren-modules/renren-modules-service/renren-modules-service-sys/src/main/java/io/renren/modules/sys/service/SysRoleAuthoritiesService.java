package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysAuthoritiesEntity;
import io.renren.modules.sys.entity.SysRoleAuthoritiesEntity;
import io.renren.modules.sys.request.*;

import java.util.*;

/**
 * 系统角色权限关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */
public interface SysRoleAuthoritiesService extends IService<SysRoleAuthoritiesEntity> {

    Map queryPage(SysRoleAuthoritiesPageRequest request);

    /***
     * 系统角色权限关系-发布redis
     * @param request
     */
    void push(SysRoleAuthoritiesPushRequest request);

    /***
     * 系统角色权限关系-删除
     * @param request
     * @throws RRException
     */
    void remove(SysRoleAuthoritiesDelRequest request) throws RRException;

    /***
     * 系统角色权限关系-待选列表(右)
     * @param request
     * @return
     * @throws RRException
     */
    List<SysAuthoritiesEntity> rightList(SysRoleAuthoritiesRightPageRequest request) throws RRException;

    /***
     * 系统角色权限关系-已选列表(左)
     * @param request
     * @return
     * @throws RRException
     */
    List<SysAuthoritiesEntity> leftList(SysRoleAuthoritiesLeftPageRequest request) throws RRException;
}

