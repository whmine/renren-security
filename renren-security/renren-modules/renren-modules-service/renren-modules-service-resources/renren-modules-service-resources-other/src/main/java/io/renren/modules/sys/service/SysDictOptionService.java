package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysDictOptionEntity;
import io.renren.modules.sys.request.QueryDictDataRequest;
import io.renren.modules.sys.request.SysDictOptionPageRequest;
import java.util.*;

/**
 * 数据字典
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
public interface SysDictOptionService extends IService<SysDictOptionEntity> {

    Map queryPage(SysDictOptionPageRequest request);

    /***
     * 数据字典-列表
     * @param request
     * @return
     */
    List<SysDictOptionEntity> queryDictData(QueryDictDataRequest request);
}

