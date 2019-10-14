package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysAdEntity;
import io.renren.modules.sys.request.SysAdPageRequest;
import java.util.*;

/**
 * 广告轮播图
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-02 09:00:09
 */
public interface SysAdService extends IService<SysAdEntity> {

    Map queryPage(SysAdPageRequest request);
}

