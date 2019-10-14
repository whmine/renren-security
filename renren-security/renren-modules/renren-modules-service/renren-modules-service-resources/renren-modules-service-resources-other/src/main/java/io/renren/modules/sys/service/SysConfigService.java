package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysConfigEntity;
import io.renren.modules.sys.request.SysConfigPageRequest;
import io.renren.modules.sys.request.SysConfigfindNameInfoRequest;

import java.util.*;

/**
 * 系统常量
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 13:22:27
 */
public interface SysConfigService extends IService<SysConfigEntity> {

    Map queryPage(SysConfigPageRequest request);

    /***
     * 缓存配置信息
     * @return
     */
    List<SysConfigEntity> sysConfigCacheManager();

    /***
     * 根据名称查询详情
     * @param request
     * @return
     */
    SysConfigEntity findNameInfo(SysConfigfindNameInfoRequest request);

}

