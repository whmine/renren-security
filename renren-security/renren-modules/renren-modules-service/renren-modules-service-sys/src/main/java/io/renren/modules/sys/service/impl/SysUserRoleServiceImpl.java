package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import io.renren.common.exception.RRException;
import io.renren.modules.sys.entity.SysRoleEntity;
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

import io.renren.modules.sys.dao.SysUserRoleDao;
import io.renren.modules.sys.entity.SysUserRoleEntity;
import io.renren.modules.sys.service.SysUserRoleService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleDao, SysUserRoleEntity> implements SysUserRoleService {

    @Override
    public Map queryPage(SysUserRolePageRequest request) {
        Page<SysUserRoleEntity> page = new Page<SysUserRoleEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysUserRoleEntity> queryWrapper = new QueryWrapper<SysUserRoleEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<SysUserRoleEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 系统用户角色关系-发布redis
     * @param request
     */
    @Override
    public void push(SysUserRolePushRequest request) {

    }

    /***
     * 系统用户角色关系-删除
     * @param request
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void remove(SysUserRoleDelRequest request) throws RRException {
        QueryWrapper<SysUserRoleEntity> qw = new QueryWrapper<>();
        qw.eq("SYS_ROLE_ID", request.getSysRoleId());
        qw.eq("SYS_USER_ID", request.getSysUserId());
        baseMapper.delete(qw);

    }

    /***
     *系统用户角色关系-待选列表(右)
     * @param request
     * @return
     * @throws RRException
     */
    @Override
    public List<SysRoleEntity> rightList(SysUserRoleRightPageRequest request) throws RRException {
        Map map = new HashMap();
        map.put("sysUserId", request.getSysUserId());
        return baseMapper.rightList(map);
    }

    /***
     * 系统用户角色关系-已选列表(左)
     * @param request
     * @return
     * @throws RRException
     */
    @Override
    public List<SysRoleEntity> leftList(SysUserRoleLeftPageRequest request) throws RRException {
        Map map = new HashMap();
        map.put("sysUserId", request.getSysUserId());
        return baseMapper.leftList(map);
    }

}
