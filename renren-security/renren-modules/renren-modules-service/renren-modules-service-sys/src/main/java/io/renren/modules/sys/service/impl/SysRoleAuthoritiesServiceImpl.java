package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import io.renren.common.exception.RRException;
import io.renren.modules.sys.entity.SysAuthoritiesEntity;
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

import io.renren.modules.sys.dao.SysRoleAuthoritiesDao;
import io.renren.modules.sys.entity.SysRoleAuthoritiesEntity;
import io.renren.modules.sys.service.SysRoleAuthoritiesService;

@Slf4j
@Service("sysRoleAuthoritiesService")
public class SysRoleAuthoritiesServiceImpl extends ServiceImpl<SysRoleAuthoritiesDao, SysRoleAuthoritiesEntity> implements SysRoleAuthoritiesService {

    @Override
    public Map queryPage(SysRoleAuthoritiesPageRequest request) {
        Page<SysRoleAuthoritiesEntity> page = new Page<SysRoleAuthoritiesEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysRoleAuthoritiesEntity> queryWrapper = new QueryWrapper<SysRoleAuthoritiesEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<SysRoleAuthoritiesEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 系统角色权限关系-发布redis
     * @param request
     */
    @Override
    public void push(SysRoleAuthoritiesPushRequest request) {

    }

    /***
     * 系统角色权限关系-删除
     * @param request
     * @throws RRException
     */
    @Override
    public void remove(SysRoleAuthoritiesDelRequest request) throws RRException {
        QueryWrapper<SysRoleAuthoritiesEntity> qw = new QueryWrapper<>();
        qw.eq("SYS_AUTHORITIES_ID", request.getSysAuthoritiesId());
        qw.eq("SYS_ROLE_ID", request.getSysRoleId());
        baseMapper.delete(qw);

    }

    /***
     * 系统角色权限关系-待选列表(右)
     * @param request
     * @return
     * @throws RRException
     */
    @Override
    public List<SysAuthoritiesEntity> rightList(SysRoleAuthoritiesRightPageRequest request) throws RRException {
        Map map = new HashMap();
        map.put("sysRoleId", request.getSysRoleId());
        return baseMapper.rightList(map);
    }

    /***
     * 系统角色权限关系-已选列表(左)
     * @param request
     * @return
     * @throws RRException
     */
    @Override
    public List<SysAuthoritiesEntity> leftList(SysRoleAuthoritiesLeftPageRequest request) throws RRException {
        Map map = new HashMap();
        map.put("sysRoleId", request.getSysRoleId());
        return baseMapper.leftList(map);
    }

}
