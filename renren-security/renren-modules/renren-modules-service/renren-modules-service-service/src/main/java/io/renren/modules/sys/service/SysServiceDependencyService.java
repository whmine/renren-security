package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysServiceDependencyEntity;
import io.renren.modules.sys.request.SysServiceDependencyPageRequest;
import java.util.*;

/**
 * 系统服务依赖
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-20 10:12:45
 */
public interface SysServiceDependencyService extends IService<SysServiceDependencyEntity> {

    Map queryPage(SysServiceDependencyPageRequest request);
}

