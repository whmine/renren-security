package io.renren.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.sys.entity.SysOauthCodeEntity;
import io.renren.modules.sys.request.SysOauthCodePageRequest;
import io.renren.modules.sys.request.SysOauthCodePushRequest;

import java.util.*;

/**
 * 授权CODE
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-07-19 12:54:22
 */
public interface SysOauthCodeService extends IService<SysOauthCodeEntity> {

    Map queryPage(SysOauthCodePageRequest request);


    /***
     * 初次登录保存数据
     * @param entity
     */
    void createSysOauthCode(Map entity);

    /***
     *修改数据
     * @param entity
     */
    void updateSysOauthCode(Map entity);

    /***
     * 授权CODE-发布redis
     * @param request
     */
    void push(SysOauthCodePushRequest request);


}

