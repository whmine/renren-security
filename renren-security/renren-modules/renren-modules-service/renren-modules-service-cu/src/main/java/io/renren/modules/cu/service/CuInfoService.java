package io.renren.modules.cu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cu.entity.CuAccountEntity;
import io.renren.modules.cu.entity.CuInfoEntity;
import io.renren.modules.cu.entity.CuProfitEntity;
import io.renren.modules.cu.request.CuInfoPageRequest;

import java.util.*;

/**
 * 客户基础信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 10:49:16
 */
public interface CuInfoService extends IService<CuInfoEntity> {

    Map queryPage(CuInfoPageRequest request);

    void init(Map map);

    void del(Map map);

    void profit(CuProfitEntity entity);

    void account(CuAccountEntity entity);
}

