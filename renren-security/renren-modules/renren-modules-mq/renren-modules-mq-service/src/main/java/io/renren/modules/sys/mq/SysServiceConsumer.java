package io.renren.modules.sys.mq;

import com.google.gson.Gson;
import io.renren.modules.sys.service.SysServiceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class SysServiceConsumer {

    @Autowired
    private SysServiceService sysServiceService;

    @JmsListener(destination = "io.renren.modules.queue.sys.sysService")
    public void receiveQueue(String text) {
        log.debug("系统服务消息队列,开始时间：" + System.currentTimeMillis());
        Map sysServiceEntity = new Gson().fromJson(text, Map.class);
        sysServiceService.firstSave(sysServiceEntity);
        log.debug("系统服务消息队列,结束时间：" + System.currentTimeMillis());

    }

}
