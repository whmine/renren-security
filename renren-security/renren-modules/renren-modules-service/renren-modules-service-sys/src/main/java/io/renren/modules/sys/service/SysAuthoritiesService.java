package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysAuthoritiesEntity;
import io.renren.modules.sys.request.SysAuthoritiesPageRequest;
import io.renren.modules.sys.request.SysAuthoritiesPushRequest;

import java.util.*;

/**
 * 系统权限
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */
public interface SysAuthoritiesService extends IService<SysAuthoritiesEntity> {

    Map queryPage(SysAuthoritiesPageRequest request);

    /***
     * 系统权限-发布redis
     * @param request
     */
    void push(SysAuthoritiesPushRequest request);
}

