package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysDictDataEntity;
import io.renren.modules.sys.request.SysDictDataPageRequest;
import java.util.*;

/**
 * 系统用户表
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
public interface SysDictDataService extends IService<SysDictDataEntity> {

    Map queryPage(SysDictDataPageRequest request);
}

