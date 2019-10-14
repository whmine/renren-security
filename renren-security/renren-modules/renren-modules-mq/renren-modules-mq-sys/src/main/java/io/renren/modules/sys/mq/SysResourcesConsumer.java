package io.renren.modules.sys.mq;

import com.google.gson.Gson;
import io.renren.modules.sys.service.SysResourcesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@Slf4j
public class SysResourcesConsumer {


    @Autowired
    private SysResourcesService sysResourcesService;

    @JmsListener(destination = "io.renren.modules.queue.sys.sysResources")
    public void receiveQueue(String text) {
        Map sysResourcesEntity = new Gson().fromJson(text, Map.class);
        log.debug("系统资源管理消息队列,开始时间：" + System.currentTimeMillis());
        sysResourcesService.createSysResources(sysResourcesEntity);
        log.debug("系统资源管理消息队列,结束时间：" + System.currentTimeMillis());
    }
}
