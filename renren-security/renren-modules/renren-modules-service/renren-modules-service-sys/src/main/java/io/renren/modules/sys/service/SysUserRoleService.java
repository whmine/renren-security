package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.entity.SysUserRoleEntity;
import io.renren.modules.sys.request.*;

import java.util.*;

/**
 * 系统用户角色关系
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */
public interface SysUserRoleService extends IService<SysUserRoleEntity> {

    Map queryPage(SysUserRolePageRequest request);

    /***
     * 系统用户角色关系-发布redis
     * @param request
     */
    void push(SysUserRolePushRequest request);

    /***
     * 系统用户角色关系-删除
     * @param request
     */
    void remove(SysUserRoleDelRequest request) throws RRException;

    /***
     *系统用户角色关系-待选列表(右)
     * @param request
     * @return
     * @throws RRException
     */
    List<SysRoleEntity> rightList(SysUserRoleRightPageRequest request) throws RRException;

    /***
     * 系统用户角色关系-已选列表(左)
     * @param request
     * @return
     * @throws RRException
     */
    List<SysRoleEntity> leftList(SysUserRoleLeftPageRequest request) throws RRException;
}

