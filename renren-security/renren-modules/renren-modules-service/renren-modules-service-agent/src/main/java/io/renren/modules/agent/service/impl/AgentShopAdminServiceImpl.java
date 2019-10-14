package io.renren.modules.agent.service.impl;

import com.google.gson.Gson;
import io.renren.common.exception.RRException;
import io.renren.common.utils.CodeUtils;
import io.renren.common.utils.PayUtil;
import io.renren.framework.utils.Producer;
import io.renren.modules.agent.entity.AgentShopBusEntity;
import io.renren.modules.agent.entity.AgentShopEntity;
import io.renren.modules.agent.request.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.agent.dao.AgentShopAdminDao;
import io.renren.modules.agent.entity.AgentShopAdminEntity;
import io.renren.modules.agent.service.AgentShopAdminService;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service("agentShopAdminService")
public class AgentShopAdminServiceImpl extends ServiceImpl<AgentShopAdminDao, AgentShopAdminEntity> implements AgentShopAdminService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Producer producer;

    @Override
    public Map queryPage(AgentShopAdminPageRequest request) {
        Page<AgentShopAdminEntity> page = new Page<AgentShopAdminEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<AgentShopAdminEntity> queryWrapper = new QueryWrapper<AgentShopAdminEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<AgentShopAdminEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 代理商管理员-发布redis
     * @param request
     */
    @Override
    public void push(AgentShopAdminPushRequest request) {

    }

    /**
     * 创建管理员
     *
     * @param request
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void save(AgentShopAdminSaveRequest request) throws RRException {
        Gson gson = new Gson();
        String sysUserId = CodeUtils.generateID();

        QueryWrapper<AgentShopAdminEntity> qw = new QueryWrapper<>();
        qw.eq("ADMIN_PHONE", request.getAdminPhone());
        int count = baseMapper.selectCount(qw);
        if (count > 0) {
            throw new RRException("手机号已存在，请重新输入手机号");
        }

        AgentShopAdminEntity entity = new AgentShopAdminEntity();
        entity.setAdminSn(PayUtil.getKeyNo("sa"));
        entity.setAdminName(request.getAdminName());
        entity.setAdminPhone(request.getAdminPhone());
        entity.setAdminParent(request.getAdminParent());
        entity.setAdminBusParent(request.getAdminBusParent());
        entity.setAdminRatio(request.getAdminRatio());
        entity.setAdminOpen(request.getAdminOpen());
        baseMapper.insert(entity);


        //第一次登录
        Map sysOauthCodeEntity = new HashMap();
        sysOauthCodeEntity.put("openId", request.getAdminPhone());
        sysOauthCodeEntity.put("sysUserId", sysUserId);
        sysOauthCodeEntity.put("name", request.getAdminPhone());
        sysOauthCodeEntity.put("roleid", "a30ccf9275a03f9a0f8b9d1c6a6ab66c");

        //初始化客户数据
        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.sys.sysOauthCode.updte"), gson.toJson(sysOauthCodeEntity));

    }

    /***
     * 修改管理员手机号
     * @param request
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void update(AgentShopAdminUpdateRequest request) throws RRException {
        Gson gson = new Gson();

        AgentShopAdminEntity entity = baseMapper.selectById(request.getId());
        entity.setAdminSn(request.getAdminSn());
        entity.setAdminName(request.getAdminName());
        entity.setAdminPhone(request.getAdminPhone());
        entity.setAdminParent(request.getAdminParent());
        entity.setAdminBusParent(request.getAdminBusParent());
        entity.setAdminRatio(request.getAdminRatio());
        entity.setAdminOpen(request.getAdminOpen());
        baseMapper.updateById(entity);


        //第一次登录
        Map sysOauthCodeEntity = new HashMap();
        sysOauthCodeEntity.put("openId", request.getAdminPhone());
        sysOauthCodeEntity.put("sysUserId", request.getCustomerId());
        sysOauthCodeEntity.put("name", request.getAdminPhone());
        sysOauthCodeEntity.put("roleid", "a30ccf9275a03f9a0f8b9d1c6a6ab66c");

        //初始化客户数据
        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.sys.sysOauthCode.updte"), gson.toJson(sysOauthCodeEntity));

    }

    /***
     * 回写
     * @param map
     * @throws RRException
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void callback(Map map) throws RRException {
        QueryWrapper<AgentShopAdminEntity> qw = new QueryWrapper<>();
        qw.eq("ADMIN_PHONE", map.get("phone") + "");
        AgentShopAdminEntity entity = baseMapper.selectOne(qw);
        if (entity == null) {
            throw new RRException("手机号已存在，请重新输入手机号");
        }
        entity.setCustomerId(map.get("customerId") + "");
        baseMapper.updateById(entity);
    }

}
