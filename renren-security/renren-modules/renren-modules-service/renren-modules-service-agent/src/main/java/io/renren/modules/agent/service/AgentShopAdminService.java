package io.renren.modules.agent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.agent.entity.AgentShopAdminEntity;
import io.renren.modules.agent.request.*;

import java.util.*;

/**
 * 代理商管理员
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 09:24:45
 */
public interface AgentShopAdminService extends IService<AgentShopAdminEntity> {

    Map queryPage(AgentShopAdminPageRequest request);

    /***
     * 代理商管理员-发布redis
     * @param request
     */
    void push(AgentShopAdminPushRequest request);

    /**
     * 创建管理员
     *
     * @param request
     */
    void save(AgentShopAdminSaveRequest request) throws RRException;

    /***
     * 修改管理员手机号
     * @param request
     */
    void update(AgentShopAdminUpdateRequest request) throws RRException;

    /***
     * 回写
     * @param map
     * @throws RRException
     */
    void callback(Map map) throws RRException;
}

