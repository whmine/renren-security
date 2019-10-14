package io.renren.modules.cu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.cu.dao.CuAccountDao;
import io.renren.modules.cu.entity.CuAccountEntity;
import io.renren.modules.cu.service.CuAccountService;
import io.renren.modules.cu.request.CuAccountPageRequest;

@Slf4j
@Service("cuAccountService")
public class CuAccountServiceImpl extends ServiceImpl<CuAccountDao, CuAccountEntity> implements CuAccountService {

    @Override
    public Map queryPage(CuAccountPageRequest request) {
        Page<CuAccountEntity> page = new Page<CuAccountEntity>(request.getPage() > 0 ? request.getPage() : 0, request.getLimit() > 1 ? request.getLimit() : 10);

        QueryWrapper<CuAccountEntity> queryWrapper = new QueryWrapper<CuAccountEntity>();
        queryWrapper.orderByDesc("SORT");
        IPage<CuAccountEntity> pageInfo = baseMapper.selectPage(page, queryWrapper);
        Map map = new HashMap();
        map.put("total", pageInfo.getTotal());
        map.put("items", pageInfo.getRecords());

        return map;
    }

}
