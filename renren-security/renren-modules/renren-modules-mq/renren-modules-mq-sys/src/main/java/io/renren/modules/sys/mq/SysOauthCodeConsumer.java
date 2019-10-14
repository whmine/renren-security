package io.renren.modules.sys.mq;

import com.google.gson.Gson;
import io.renren.modules.sys.service.SysOauthCodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@Slf4j
public class SysOauthCodeConsumer {


    @Autowired
    private SysOauthCodeService sysOauthCodeService;

    @JmsListener(destination = "io.renren.modules.queue.sys.sysOauthCode")
    public void receiveQueue(String text) {
        Map sysOauthCodeEntity = new Gson().fromJson(text, Map.class);
        log.debug("系统第三方授权消息队列,开始时间：" + System.currentTimeMillis());
        sysOauthCodeService.createSysOauthCode(sysOauthCodeEntity);
        log.debug("系统第三方授权消息队列,结束时间：" + System.currentTimeMillis());
    }

    @JmsListener(destination = "io.renren.modules.queue.sys.sysOauthCode.updte")
    public void receiveUpdateQueue(String text) {
        Map sysOauthCodeEntity = new Gson().fromJson(text, Map.class);
        log.debug("系统第三方授权消息队列,开始时间：" + System.currentTimeMillis());
        sysOauthCodeService.updateSysOauthCode(sysOauthCodeEntity);
        log.debug("系统第三方授权消息队列,结束时间：" + System.currentTimeMillis());
    }
}
