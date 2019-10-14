package io.renren.modules.cu.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.google.gson.Gson;
import io.renren.common.constant.Constants;
import io.renren.common.exception.RRException;
import io.renren.common.utils.DateUtils;
import io.renren.framework.utils.Producer;
import io.renren.modules.cu.request.CuSignListRequest;
import io.renren.modules.cu.request.CuSignSignRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.math.BigDecimal;
import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cu.dao.CuSignDao;
import io.renren.modules.cu.entity.CuSignEntity;
import io.renren.modules.cu.service.CuSignService;
import io.renren.modules.cu.request.CuSignPageRequest;
import io.renren.modules.cu.request.CuSignPushRequest;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service("cuSignService")
public class CuSignServiceImpl extends ServiceImpl<CuSignDao, CuSignEntity> implements CuSignService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Producer producer;

    @Override
    public Map queryPage(CuSignPageRequest request) {
        Page<CuSignEntity> page = new Page<CuSignEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<CuSignEntity> queryWrapper = new QueryWrapper<CuSignEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<CuSignEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    /***
     * 客户签到-发布redis
     * @param request
     */
    @Override
    public void push(CuSignPushRequest request) {

    }

    /***
     * 签到
     * @param request
     * @throws RRException
     */
    @Override
    @Transactional(rollbackFor = {RRException.class})
    public void sign(CuSignSignRequest request) throws RRException {

        Date date = new Date(new Long(request.getDate()));//DateUtils.stringToDate(, DateUtils.DATE_TIME_PATTERN);
        QueryWrapper<CuSignEntity> wq = new QueryWrapper<>();
        wq.eq("CUSTOMER_YEAR", date.getYear());
        wq.eq("CUSTOMER_MONTH", date.getMonth());
        wq.eq("CUSTOMER_DAY", date.getDay());
        wq.eq("CUSTOMER_DATE", request.getDate());
        wq.eq("CUSTOMER_ID", request.getUserId());
        int count = baseMapper.selectCount(wq);
        if (count > 0) {
            throw new RRException("您已签到");
        }
        CuSignEntity cuSignEntity = new CuSignEntity();
        cuSignEntity.setCustomerId(request.getUserId());
        cuSignEntity.setCustomerDate(request.getDate());
        cuSignEntity.setCustomerYear(date.getYear() + "");
        cuSignEntity.setCustomerMonth(date.getMonth() + "");
        cuSignEntity.setCustomerDay(date.getDay() + "");
        baseMapper.insert(cuSignEntity);

        //积分数
        String customerGradeJson = stringRedisTemplate.opsForValue().get(Constants.SYS_CONFIG + Constants.CU_SIGN_NUM);
        Map customerGradeMap = new Gson().fromJson(customerGradeJson, Map.class);


        String cuAccountJson = stringRedisTemplate.opsForValue().get(Constants.CU_ACCOUNT + request.getUserId());
        Map cuAccountMap = new Gson().fromJson(cuAccountJson, Map.class);


        BigDecimal big = new BigDecimal(cuAccountMap.get("customerGrade") + "").add(new BigDecimal(customerGradeMap.get("value") + ""));
        cuAccountMap.put("customerGrade", big);
        stringRedisTemplate.opsForValue().set(Constants.CU_ACCOUNT + request.getUserId(), new Gson().toJson(cuAccountMap));

        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.cu.customer.account"), new Gson().toJson(cuAccountMap));


        Map<Object, Object> cuProfitEntity = new HashMap<>();
        cuProfitEntity.put("customerId", request.getUserId());
        cuProfitEntity.put("sourceId", cuSignEntity.getId());
        cuProfitEntity.put("sourceType", "签到获取积分");
        cuProfitEntity.put("profitNum", customerGradeMap.get("value"));
        cuProfitEntity.put("profitType", "0");
        cuProfitEntity.put("profitStatus", "1");
        producer.sendMessage(new ActiveMQQueue("io.renren.modules.queue.cu.customer.profit"), new Gson().toJson(cuProfitEntity));
    }

    /***
     * 签到列表
     * @param request
     * @return
     * @throws RRException
     */
    @Override
    public List<CuSignEntity> signList(CuSignListRequest request) throws RRException {
        QueryWrapper<CuSignEntity> qw = new QueryWrapper<>();
        qw.eq("CUSTOMER_ID", request.getUserId());
        return baseMapper.selectList(qw);
    }

}
