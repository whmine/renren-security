package io.renren.modules.cu.service.impl;

import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.exception.RRException;
import io.renren.common.utils.ChinesePinyinUtil;
import io.renren.modules.cu.dao.CuAccountDao;
import io.renren.modules.cu.dao.CuCountDao;
import io.renren.modules.cu.dao.CuProfitDao;
import io.renren.modules.cu.entity.CuAccountEntity;
import io.renren.modules.cu.entity.CuCountEntity;
import io.renren.modules.cu.entity.CuProfitEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cu.dao.CuInfoDao;
import io.renren.modules.cu.entity.CuInfoEntity;
import io.renren.modules.cu.service.CuInfoService;
import io.renren.modules.cu.request.CuInfoPageRequest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service("cuInfoService" )
public class CuInfoServiceImpl extends ServiceImpl<CuInfoDao, CuInfoEntity> implements CuInfoService {

    @Autowired
    CuAccountDao cuAccountDao;

    @Autowired
    CuCountDao cuCountDao;

    @Autowired
    CuProfitDao cuProfitDao;


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Map queryPage(CuInfoPageRequest request) {
        Page<CuInfoEntity> page = new Page<CuInfoEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<CuInfoEntity> queryWrapper = new QueryWrapper<CuInfoEntity>();
        queryWrapper.orderByDesc("SORT" );
        IPage<CuInfoEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total" , pageInfo.getTotal());
        map.put("items" , pageInfo.getRecords());

        return map;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void init(Map map) {
        String sysUserId = (String) map.get("sysUserId" );
        String url = (String) map.get("url" );
        String name = (String) map.get("name" );
        Gson gson = new Gson();

        CuInfoEntity cuInfoEntity = baseMapper.selectById(sysUserId);
        if (cuInfoEntity == null) {
            //初始化客户基础信息
            cuInfoEntity = new CuInfoEntity();
            cuInfoEntity.setId(sysUserId);
            cuInfoEntity.setCustomerName(name);
            cuInfoEntity.setCustomerHead(url);
            cuInfoEntity.setCustomerAcronym(ChinesePinyinUtil.getPinYinHeadChar(name.substring(0, 1)).toUpperCase());
            baseMapper.insert(cuInfoEntity);
            stringRedisTemplate.opsForValue().set(Constants.CU_INFO + sysUserId, gson.toJson(cuInfoEntity));
            //初始化客户账户信息
            CuAccountEntity cuAccountEntity = new CuAccountEntity();
            cuAccountEntity.setId(sysUserId);
            cuAccountDao.insert(cuAccountEntity);
            stringRedisTemplate.opsForValue().set(Constants.CU_ACCOUNT + sysUserId, gson.toJson(cuAccountEntity));

            //初始化客户统计信息
            CuCountEntity cuCountEntity = new CuCountEntity();
            cuCountEntity.setId(sysUserId);
            cuCountDao.insert(cuCountEntity);
            stringRedisTemplate.opsForValue().set(Constants.CU_COUNT + sysUserId, gson.toJson(cuCountEntity));
        } else {
            stringRedisTemplate.opsForValue().set(Constants.CU_INFO + sysUserId, gson.toJson(cuInfoEntity));
            stringRedisTemplate.opsForValue().set(Constants.CU_ACCOUNT + sysUserId, gson.toJson(cuAccountDao.selectById(sysUserId)));
            stringRedisTemplate.opsForValue().set(Constants.CU_COUNT + sysUserId, gson.toJson(cuCountDao.selectById(sysUserId)));
        }


    }

    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void del(Map map) {
        String sysUserId = (String) map.get("sysUserId" );
        stringRedisTemplate.delete(Constants.CU_INFO + sysUserId);
        stringRedisTemplate.delete(Constants.CU_ACCOUNT + sysUserId);
        stringRedisTemplate.delete(Constants.CU_COUNT + sysUserId);
        baseMapper.deleteById(sysUserId);
        cuAccountDao.deleteById(sysUserId);
        cuCountDao.deleteById(sysUserId);

    }

    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void profit(CuProfitEntity entity) {
        cuProfitDao.insert(entity);
    }

    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void account(CuAccountEntity entity) {
        CuAccountEntity cuAccountEntity = cuAccountDao.selectById(entity.getId());
        cuAccountEntity.setCustomerMoney(entity.getCustomerMoney());
        cuAccountDao.updateById(cuAccountEntity);
    }

}
