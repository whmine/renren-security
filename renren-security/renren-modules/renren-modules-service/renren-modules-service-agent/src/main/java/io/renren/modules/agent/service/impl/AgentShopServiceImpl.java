package io.renren.modules.agent.service.impl;

import com.google.gson.Gson;
import io.renren.common.exception.RRException;
import io.renren.common.utils.CodeUtils;
import io.renren.common.utils.PayUtil;
import io.renren.framework.utils.Producer;
import io.renren.modules.agent.entity.AgentShopAdminEntity;
import io.renren.modules.agent.entity.AgentShopBusEntity;
import io.renren.modules.agent.request.AgentShopSaveRequest;
import io.renren.modules.agent.request.AgentShopUpdateRequest;
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

import io.renren.modules.agent.dao.AgentShopDao;
import io.renren.modules.agent.entity.AgentShopEntity;
import io.renren.modules.agent.service.AgentShopService;
import io.renren.modules.agent.request.AgentShopPageRequest;
import io.renren.modules.agent.request.AgentShopPushRequest;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service("agentShopService")
public class AgentShopServiceImpl extends ServiceImpl<AgentShopDao, AgentShopEntity> implements AgentShopService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Producer producer;

    @Override
    public Map queryPage(AgentShopPageRequest request) {
        Page<AgentShopEntity> page = new Page<AgentShopEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<AgentShopEntity> queryWrapper = new QueryWrapper<AgentShopEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<AgentShopEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 代理商-发布redis
     * @param request
     */
    @Override
    public void push(AgentShopPushRequest request) {

    }

    /**
     * 创建代理商
     *
     * @param request
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void save(AgentShopSaveRequest request) throws RRException {
        Gson gson = new Gson();
        String sysUserId = CodeUtils.generateID();

        QueryWrapper<AgentShopEntity> qw = new QueryWrapper<>();
        qw.eq("SHOP_PHONE", request.getShopPhone());
        int count = baseMapper.selectCount(qw);
        if (count > 0) {
            throw new RRException("手机号已存在，请重新输入手机号");
        }

        AgentShopEntity entity = new AgentShopEntity();
        entity.setShopSn(PayUtil.getKeyNo("sd"));
        entity.setShopName(request.getShopName());
        entity.setShopPhone(request.getShopPhone());
        entity.setShopType(request.getShopType());
        entity.setShopParent(request.getShopParent());
        entity.setShopRatio(request.getShopRatio());
        entity.setShopOpen(request.getShopOpen());

        baseMapper.insert(entity);


        //第一次登录
        Map sysOauthCodeEntity = new HashMap();
        sysOauthCodeEntity.put("openId", request.getShopPhone());
        sysOauthCodeEntity.put("sysUserId", sysUserId);
        sysOauthCodeEntity.put("name", request.getShopPhone());
        sysOauthCodeEntity.put("roleid", "6dc2c8887b8b482430566716da4502bb");

        //初始化客户数据
        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.sys.sysOauthCode.updte"), gson.toJson(sysOauthCodeEntity));

    }

    /***
     * 修改代理商手机号
     * @param request
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void update(AgentShopUpdateRequest request) throws RRException {
        Gson gson = new Gson();

        AgentShopEntity entity = baseMapper.selectById(request.getId());
        entity.setShopSn(request.getShopSn());
        entity.setShopName(request.getShopName());
        entity.setShopPhone(request.getShopPhone());
        entity.setShopType(request.getShopType());
        entity.setShopParent(request.getShopParent());
        entity.setShopRatio(request.getShopRatio());
        entity.setShopOpen(request.getShopOpen());

        baseMapper.updateById(entity);


        //第一次登录
        Map sysOauthCodeEntity = new HashMap();
        sysOauthCodeEntity.put("openId", request.getShopPhone());
        sysOauthCodeEntity.put("sysUserId", request.getCustomerId());
        sysOauthCodeEntity.put("name", request.getShopPhone());
        sysOauthCodeEntity.put("roleid", "6dc2c8887b8b482430566716da4502bb");

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
        QueryWrapper<AgentShopEntity> qw = new QueryWrapper<>();
        qw.eq("SHOP_PHONE", map.get("phone") + "");
        AgentShopEntity entity = baseMapper.selectOne(qw);
        if (entity == null) {
            throw new RRException("手机号已存在，请重新输入手机号");
        }
        entity.setCustomerId(map.get("customerId") + "");
        baseMapper.updateById(entity);
    }

}
