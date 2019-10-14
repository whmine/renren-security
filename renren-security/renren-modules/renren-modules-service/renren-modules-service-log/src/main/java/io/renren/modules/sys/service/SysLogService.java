package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysLogEntity;
import io.renren.modules.sys.request.SysLogPageRequest;
import java.util.*;

/**
 * 系统日志
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:04:07
 */
public interface SysLogService extends IService<SysLogEntity> {

    Map queryPage(SysLogPageRequest request);
}

