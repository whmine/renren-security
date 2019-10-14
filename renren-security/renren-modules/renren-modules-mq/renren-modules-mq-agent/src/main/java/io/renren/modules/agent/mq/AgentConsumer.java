package io.renren.modules.agent.mq;

import com.google.gson.Gson;
import io.renren.modules.agent.service.AgentShopAdminService;
import io.renren.modules.agent.service.AgentShopBusService;
import io.renren.modules.agent.service.AgentShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@Slf4j
public class AgentConsumer {


    @Autowired
    private AgentShopBusService agentShopBusService;

    @Autowired
    private AgentShopService agentShopService;

    @Autowired
    private AgentShopAdminService agentShopAdminService;

    @JmsListener(destination = "io.renren.modules.queue.agent.bus.customer")
    public void receiveBusQueue(String text) {
        log.debug("系统回写客户ID初始化消息队列,开始时间：" + System.currentTimeMillis());
        Map map = new Gson().fromJson(text, Map.class);
        agentShopBusService.callback(map);
        log.debug("系统回写客户ID初始化消息队列,结束时间：" + System.currentTimeMillis());
    }

    @JmsListener(destination = "io.renren.modules.queue.agent.admin.customer")
    public void receiveAdminQueue(String text) {
        log.debug("系统回写客户ID初始化消息队列,开始时间：" + System.currentTimeMillis());
        Map map = new Gson().fromJson(text, Map.class);
        agentShopAdminService.callback(map);
        log.debug("系统回写客户ID初始化消息队列,结束时间：" + System.currentTimeMillis());
    }

    @JmsListener(destination = "io.renren.modules.queue.agent.customer")
    public void receiveQueue(String text) {
        log.debug("系统回写客户ID初始化消息队列,开始时间：" + System.currentTimeMillis());
        Map map = new Gson().fromJson(text, Map.class);
        agentShopService.callback(map);
        log.debug("系统回写客户ID初始化消息队列,结束时间：" + System.currentTimeMillis());
    }

}
