package io.renren.modules.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.app.entity.OauthAppDetailsEntity;
import io.renren.modules.app.request.OauthAppDetailsPageRequest;
import io.renren.modules.app.request.OauthAppDetailsPushRequest;
import java.util.*;

/**
 * 系统信息
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-08-16 10:49:56
 */
public interface OauthAppDetailsService extends IService<OauthAppDetailsEntity> {

    Map queryPage(OauthAppDetailsPageRequest request);

    /***
     * 系统信息-发布redis
     * @param request
     */
    void push(OauthAppDetailsPushRequest request);
}

