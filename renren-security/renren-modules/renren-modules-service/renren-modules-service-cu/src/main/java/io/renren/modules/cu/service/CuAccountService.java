package io.renren.modules.cu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.cu.entity.CuAccountEntity;
import io.renren.modules.cu.request.CuAccountPageRequest;
import java.util.*;

/**
 * 客户账目信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 10:49:16
 */
public interface CuAccountService extends IService<CuAccountEntity> {

    Map queryPage(CuAccountPageRequest request);
}

