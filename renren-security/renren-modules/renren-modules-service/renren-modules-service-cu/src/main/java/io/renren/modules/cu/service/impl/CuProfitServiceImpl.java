package io.renren.modules.cu.service.impl;

import io.renren.common.exception.RRException;
import io.renren.common.utils.DateUtils;
import io.renren.common.utils.StringUtil;
import io.renren.modules.cu.request.CuProfitCountRequest;
import io.renren.modules.cu.request.CuProfitMyListRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cu.dao.CuProfitDao;
import io.renren.modules.cu.entity.CuProfitEntity;
import io.renren.modules.cu.service.CuProfitService;
import io.renren.modules.cu.request.CuProfitPageRequest;

@Slf4j
@Service("cuProfitService")
public class CuProfitServiceImpl extends ServiceImpl<CuProfitDao, CuProfitEntity> implements CuProfitService {

    @Override
    public Map queryPage(CuProfitPageRequest request) {
        Page<CuProfitEntity> page = new Page<CuProfitEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<CuProfitEntity> queryWrapper = new QueryWrapper<CuProfitEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<CuProfitEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

    @Override
    public Map count(CuProfitCountRequest request) throws RRException {
        String time = DateUtils.format(DateUtils.stringToDate(request.getDate(), "yyyy-MM"), "yyyy-MM");
        Map map = new HashMap();
        map.put("time", time);
        map.put("sysUserId", request.getUserId());
        Map sumPay = baseMapper.sumPay(map);
        if (sumPay == null) {
            sumPay = new HashMap();
            sumPay.put("nums", "0");
        }
        Map sumProfit = baseMapper.sumProfit(map);
        if (sumProfit == null) {
            sumProfit = new HashMap();
            sumProfit.put("nums", "0");
        }
        String sumPayStr = sumPay.get("nums") + "";
        if (StringUtil.isEmpty(sumPayStr)) {
            sumPayStr = "0";
        }

        String sumProfitStr = sumProfit.get("nums") + "";
        if (StringUtil.isEmpty(sumProfitStr)) {
            sumProfitStr = "0";
        }

        BigDecimal big = new BigDecimal(sumProfitStr).subtract(new BigDecimal(sumPayStr));

        Map maptemp = new HashMap();
        maptemp.put("value", big.intValue());
        return maptemp;
    }

    @Override
    public List<CuProfitEntity> myList(CuProfitMyListRequest request) throws RRException {
        String time = DateUtils.format(DateUtils.stringToDate(request.getDate(), "yyyy-MM"), "yyyy-MM");
        Map map = new HashMap();
        map.put("time", time);
        map.put("sysUserId", request.getUserId());
        return baseMapper.myList(map);
    }

}
