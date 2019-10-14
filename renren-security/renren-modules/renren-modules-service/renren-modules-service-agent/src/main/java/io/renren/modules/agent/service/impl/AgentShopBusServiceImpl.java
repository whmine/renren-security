package io.renren.modules.agent.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.google.gson.Gson;
import io.renren.common.exception.RRException;
import io.renren.common.utils.*;
import io.renren.framework.utils.Producer;
import io.renren.modules.agent.request.AgentShopBusSaveRequest;
import io.renren.modules.agent.request.AgentShopBusUpdateRequest;
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

import io.renren.modules.agent.dao.AgentShopBusDao;
import io.renren.modules.agent.entity.AgentShopBusEntity;
import io.renren.modules.agent.service.AgentShopBusService;
import io.renren.modules.agent.request.AgentShopBusPageRequest;
import io.renren.modules.agent.request.AgentShopBusPushRequest;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service("agentShopBusService")
public class AgentShopBusServiceImpl extends ServiceImpl<AgentShopBusDao, AgentShopBusEntity> implements AgentShopBusService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Producer producer;

    @Override
    public Map queryPage(AgentShopBusPageRequest request) {
        Page<AgentShopBusEntity> page = new Page<AgentShopBusEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<AgentShopBusEntity> queryWrapper = new QueryWrapper<AgentShopBusEntity>();
        if (!StringUtil.isEmpty(request.getBusParent())) {
            queryWrapper.eq("BUS_PARENT", request.getBusParent());
        }

        queryWrapper.orderByDesc("SORT");
        IPage<AgentShopBusEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 代理商业务员-发布redis
     * @param request
     */
    @Override
    public void push(AgentShopBusPushRequest request) {

    }

    /**
     * 创建业务员
     *
     * @param request
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void save(AgentShopBusSaveRequest request) {
        Gson gson = new Gson();
        String sysUserId = CodeUtils.generateID();

        QueryWrapper<AgentShopBusEntity> qw = new QueryWrapper<>();
        qw.eq("BUS_PHONE", request.getBusPhone());
        int count = baseMapper.selectCount(qw);
        if (count > 0) {
            throw new RRException("手机号已存在，请重新输入手机号");
        }

        AgentShopBusEntity entity = new AgentShopBusEntity();
        entity.setBusSn(PayUtil.getKeyNo("sb"));
        entity.setBusName(request.getBusName());
        entity.setBusPhone(request.getBusPhone());
        entity.setBusParent(request.getBusParent());
        entity.setBusRatio(request.getBusRatio());
        entity.setBusOpen(request.getBusOpen());
        baseMapper.insert(entity);


        //第一次登录
        Map sysOauthCodeEntity = new HashMap();
        sysOauthCodeEntity.put("openId", request.getBusPhone());
        sysOauthCodeEntity.put("sysUserId", sysUserId);
        sysOauthCodeEntity.put("name", request.getBusPhone());
        sysOauthCodeEntity.put("roleid", "cebb923eeef39272e571f8911657ced8");

        //初始化客户数据
        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.sys.sysOauthCode.updte"), gson.toJson(sysOauthCodeEntity));


    }

    /***
     * 修改业务员手机号
     * @param request
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void update(AgentShopBusUpdateRequest request) {
        Gson gson = new Gson();

        AgentShopBusEntity entity = baseMapper.selectById(request.getId());
        entity.setBusSn(PayUtil.getKeyNo("sb"));
        entity.setBusName(request.getBusName());
        entity.setBusPhone(request.getBusPhone());
        entity.setBusParent(request.getBusParent());
        entity.setBusRatio(request.getBusRatio());
        entity.setBusOpen(request.getBusOpen());
        baseMapper.updateById(entity);


        //第一次登录
        Map sysOauthCodeEntity = new HashMap();
        sysOauthCodeEntity.put("openId", request.getBusPhone());
        sysOauthCodeEntity.put("sysUserId", request.getCustomerId());
        sysOauthCodeEntity.put("name", request.getBusPhone());
        sysOauthCodeEntity.put("roleid", "cebb923eeef39272e571f8911657ced8");

        //初始化客户数据
        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.sys.sysOauthCode.updte"), gson.toJson(sysOauthCodeEntity));
    }

    /***
     * 回写客户ID
     * @param map
     * @throws RRException
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void callback(Map map) throws RRException {
        QueryWrapper<AgentShopBusEntity> qw = new QueryWrapper<>();
        qw.eq("BUS_PHONE", map.get("phone") + "");
        AgentShopBusEntity entity = baseMapper.selectOne(qw);
        if (entity == null) {
            throw new RRException("手机号已存在，请重新输入手机号");
        }
        entity.setCustomerId(map.get("customerId") + "");
        baseMapper.updateById(entity);
    }

}
