package io.renren.modules.agent.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.agent.entity.AgentShopPosEntity;
import io.renren.modules.agent.request.AgentShopPosPageRequest;
import io.renren.modules.agent.request.AgentShopPosPushRequest;
import java.util.*;

/**
 * 代理商商户
 *
 * @author Shark
 * @email shark@126.com
 * @date 2019-09-29 09:24:45
 */
public interface AgentShopPosService extends IService<AgentShopPosEntity> {

    Map queryPage(AgentShopPosPageRequest request);

    /***
     * 代理商商户-发布redis
     * @param request
     */
    void push(AgentShopPosPushRequest request);
}

