package io.renren.modules.agent.service.impl;

import lombok.extern.slf4j.Slf4j;
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

import io.renren.modules.agent.dao.AgentShopPosDao;
import io.renren.modules.agent.entity.AgentShopPosEntity;
import io.renren.modules.agent.service.AgentShopPosService;
import io.renren.modules.agent.request.AgentShopPosPageRequest;
import io.renren.modules.agent.request.AgentShopPosPushRequest;


@Slf4j
@Service("agentShopPosService")
public class AgentShopPosServiceImpl extends ServiceImpl<AgentShopPosDao, AgentShopPosEntity> implements AgentShopPosService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map queryPage(AgentShopPosPageRequest request) {
        Page<AgentShopPosEntity> page = new Page<AgentShopPosEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<AgentShopPosEntity> queryWrapper = new QueryWrapper<AgentShopPosEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<AgentShopPosEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 代理商商户-发布redis
     * @param request
     */
    @Override
    public void push(AgentShopPosPushRequest request) {

    }

}
