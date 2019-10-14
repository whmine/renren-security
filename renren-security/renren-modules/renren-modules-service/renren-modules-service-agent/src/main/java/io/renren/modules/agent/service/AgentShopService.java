package io.renren.modules.agent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.modules.agent.entity.AgentShopEntity;
import io.renren.modules.agent.request.*;

import java.util.*;

/**
 * 代理商
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 09:24:45
 */
public interface AgentShopService extends IService<AgentShopEntity> {

    Map queryPage(AgentShopPageRequest request);

    /***
     * 代理商-发布redis
     * @param request
     */
    void push(AgentShopPushRequest request);


    /**
     * 创建业务员
     *
     * @param request
     */
    void save(AgentShopSaveRequest request) throws RRException;

    /***
     * 修改业务员手机号
     * @param request
     */
    void update(AgentShopUpdateRequest request) throws RRException;


    /***
     * 回写
     * @param map
     * @throws RRException
     */
    void callback(Map map) throws RRException;
}

