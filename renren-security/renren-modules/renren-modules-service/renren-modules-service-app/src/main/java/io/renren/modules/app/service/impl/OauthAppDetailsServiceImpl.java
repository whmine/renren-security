package io.renren.modules.app.service.impl;

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

import io.renren.modules.app.dao.OauthAppDetailsDao;
import io.renren.modules.app.entity.OauthAppDetailsEntity;
import io.renren.modules.app.service.OauthAppDetailsService;
import io.renren.modules.app.request.OauthAppDetailsPageRequest;
import io.renren.modules.app.request.OauthAppDetailsPushRequest;


@Slf4j
@Service("oauthAppDetailsService")
public class OauthAppDetailsServiceImpl extends ServiceImpl<OauthAppDetailsDao, OauthAppDetailsEntity> implements OauthAppDetailsService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map queryPage(OauthAppDetailsPageRequest request) {
        Page<OauthAppDetailsEntity> page = new Page<OauthAppDetailsEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<OauthAppDetailsEntity> queryWrapper = new QueryWrapper<OauthAppDetailsEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<OauthAppDetailsEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 系统信息-发布redis
     * @param request
     */
    @Override
    public void push(OauthAppDetailsPushRequest request) {

    }

}
