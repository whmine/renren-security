package io.renren.modules.cu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cu.entity.CuCountEntity;
import io.renren.modules.cu.request.CuCountPageRequest;
import java.util.*;

/**
 * 客户统计信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 10:49:16
 */
public interface CuCountService extends IService<CuCountEntity> {

    Map queryPage(CuCountPageRequest request);
}

