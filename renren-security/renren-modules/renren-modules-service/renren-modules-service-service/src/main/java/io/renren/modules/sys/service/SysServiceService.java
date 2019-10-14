package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysServiceEntity;
import io.renren.modules.sys.request.SysServicePageRequest;

import java.util.*;

/**
 * 系统服务
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-20 10:12:44
 */
public interface SysServiceService extends IService<SysServiceEntity> {

    Map queryPage(SysServicePageRequest request);

    /***
     * 初始化系统服务
     * @param entity
     */
    void firstSave(Map entity);


    /***
     * 缓存系统服务信息
     * @return
     */
    List<SysServiceEntity> sysServiceCacheManager();

}

