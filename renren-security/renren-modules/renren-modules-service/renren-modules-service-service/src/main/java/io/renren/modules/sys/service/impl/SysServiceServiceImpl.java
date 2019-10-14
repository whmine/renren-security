package io.renren.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import io.renren.framework.utils.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.sys.dao.SysServiceDao;
import io.renren.modules.sys.entity.SysServiceEntity;
import io.renren.modules.sys.service.SysServiceService;
import io.renren.modules.sys.request.SysServicePageRequest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("sysServiceService")
public class SysServiceServiceImpl extends ServiceImpl<SysServiceDao, SysServiceEntity> implements SysServiceService {


    @Autowired
    private Producer producer;

    @Override
    public Map queryPage(SysServicePageRequest request) {
        Page<SysServiceEntity> page = new Page<SysServiceEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<SysServiceEntity> queryWrapper = new QueryWrapper<SysServiceEntity>();
        if (!StringUtils.isBlank(request.getName())) {
            queryWrapper.like("name", request.getName());
        }
        queryWrapper.orderByDesc("SORT");
        IPage<SysServiceEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 初始化系统服务
     * @param entity
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void firstSave(Map entity) {
        QueryWrapper<SysServiceEntity> qWrapper = new QueryWrapper<>();
        qWrapper.eq("AUTHZ_CODE", entity.get("authzCode"));
        SysServiceEntity sysServiceEntity = baseMapper.selectOne(qWrapper);
        String id = "";
        if (sysServiceEntity == null) {
            sysServiceEntity = new SysServiceEntity();
            sysServiceEntity.setName(entity.get("name") + "");
            sysServiceEntity.setCnName(entity.get("cnName") + "");
            sysServiceEntity.setIcon(entity.get("icon") + "");
            sysServiceEntity.setContent(entity.get("content") + "");
            sysServiceEntity.setAuthzCode(entity.get("authzCode") + "");
            sysServiceEntity.setPerms(entity.get("perms") + "");
            sysServiceEntity.setIsShow(entity.get("isShow") + "");
            sysServiceEntity.setStartUpType(entity.get("startUpType") + "");
            sysServiceEntity.setState(entity.get("state") + "");
            sysServiceEntity.setMethod(entity.get("method") + "");
            baseMapper.insert(sysServiceEntity);
            id = sysServiceEntity.getId();
        } else {
            id = sysServiceEntity.getId();
            sysServiceEntity.setName(entity.get("name") + "");
            sysServiceEntity.setCnName(entity.get("cnName") + "");
            sysServiceEntity.setIcon(entity.get("icon") + "");
            sysServiceEntity.setContent(entity.get("content") + "");
            sysServiceEntity.setAuthzCode(entity.get("authzCode") + "");
            sysServiceEntity.setPerms(entity.get("perms") + "");
            sysServiceEntity.setIsShow(entity.get("isShow") + "");
            sysServiceEntity.setStartUpType(entity.get("startUpType") + "");
            sysServiceEntity.setState(entity.get("state") + "");
            sysServiceEntity.setMethod(entity.get("method") + "");
            baseMapper.updateById(sysServiceEntity);
        }

        Map sysResourcesEntity = new HashMap();
        sysResourcesEntity.put("name", entity.get("name") + "");
        sysResourcesEntity.put("cnName", entity.get("cnName") + "");
        sysResourcesEntity.put("value", id);
        sysResourcesEntity.put("type", "SERVICE");
        sysResourcesEntity.put("isSys", "N");

        sysResourcesEntity.put("roleName", entity.get("roleName") + "");
        sysResourcesEntity.put("roleCnName", entity.get("roleCnName") + "");

        sysResourcesEntity.put("authoritiesName", entity.get("authoritiesName") + "");
        sysResourcesEntity.put("authoritiesCnName", entity.get("authoritiesCnName") + "");

        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.sys.sysResources"), sysResourcesEntity);


    }

    /***
     * 缓存系统服务信息
     * @return
     */
    @Override
    public List<SysServiceEntity> sysServiceCacheManager() {
        QueryWrapper<SysServiceEntity> qWrapper = new QueryWrapper<>();
        return baseMapper.selectList(qWrapper);
    }

}
