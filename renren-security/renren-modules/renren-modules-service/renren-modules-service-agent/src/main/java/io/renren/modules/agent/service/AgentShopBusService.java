package io.renren.modules.agent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.exception.RRException;
import io.renren.common.utils.PageUtils;
import io.renren.modules.agent.entity.AgentShopBusEntity;
import io.renren.modules.agent.request.AgentShopBusPageRequest;
import io.renren.modules.agent.request.AgentShopBusPushRequest;
import io.renren.modules.agent.request.AgentShopBusSaveRequest;
import io.renren.modules.agent.request.AgentShopBusUpdateRequest;

import java.util.*;

/**
 * 代理商业务员
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 09:24:45
 */
public interface AgentShopBusService extends IService<AgentShopBusEntity> {

    Map queryPage(AgentShopBusPageRequest request) throws RRException;

    /***
     * 代理商业务员-发布redis
     * @param request
     */
    void push(AgentShopBusPushRequest request) throws RRException;

    /**
     * 创建业务员
     *
     * @param request
     */
    void save(AgentShopBusSaveRequest request) throws RRException;

    /***
     * 修改业务员手机号
     * @param request
     */
    void update(AgentShopBusUpdateRequest request) throws RRException;


    /***
     * 回写客户ID
     * @param map
     * @throws RRException
     */
    void callback(Map map) throws RRException;
}

