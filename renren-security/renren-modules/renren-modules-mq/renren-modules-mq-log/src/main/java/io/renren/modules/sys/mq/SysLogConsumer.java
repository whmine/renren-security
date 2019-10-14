package io.renren.modules.sys.mq;

import com.google.gson.Gson;
import io.renren.modules.sys.entity.SysLogEntity;
import io.renren.modules.sys.service.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class SysLogConsumer {

    @Autowired
    private SysLogService sysLogService;

    @JmsListener(destination = "io.renren.modules.queue.sys.sysLog")
    public void receiveQueue(String text) {
        log.debug("系统日志消息队列,开始时间：" + System.currentTimeMillis());
        SysLogEntity sysLogEntity = new Gson().fromJson(text, SysLogEntity.class);
        sysLogService.save(sysLogEntity);
        log.debug("系统日志消息队列,结束时间：" + System.currentTimeMillis());

    }

}
