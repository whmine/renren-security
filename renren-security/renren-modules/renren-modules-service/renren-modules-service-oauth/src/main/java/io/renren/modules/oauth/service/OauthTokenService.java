package io.renren.modules.oauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.oauth.entity.OauthTokenEntity;
import io.renren.modules.oauth.request.OauthTokenPageRequest;

import java.util.*;

/**
 * 授权TOKEN
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 08:49:27
 */
public interface OauthTokenService extends IService<OauthTokenEntity> {

    /***
     * 分页查询
     * @param request
     * @return
     */
    Map queryPage(OauthTokenPageRequest request);

    /***
     * 更新token
     * @param oauthTokenEntity
     */
    void createToken(OauthTokenEntity oauthTokenEntity);


    /***
     * 注销token
     * @param oauthTokenEntity
     */
    void outToken(OauthTokenEntity oauthTokenEntity);


    /***
     * 缓存
     * @return
     */
    List<OauthTokenEntity> oauthTokenCacheManager();

}

