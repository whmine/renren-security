package io.renren.modules.oauth.mq;

import com.google.gson.Gson;
import io.renren.modules.oauth.entity.OauthTokenEntity;
import io.renren.modules.oauth.service.OauthTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class OauthTokenConsumer {

    @Autowired
    private OauthTokenService oauthTokenService;

    @JmsListener(destination = "io.renren.modules.queue.oauth.oauthToken")
    public void receiveQueue(String text) {
        log.debug("系统TOKEN消息队列,开始时间：" + System.currentTimeMillis());
        OauthTokenEntity oauthTokenEntity = new Gson().fromJson(text, OauthTokenEntity.class);
        oauthTokenService.createToken(oauthTokenEntity);
        log.debug("系统TOKEN消息队列,结束时间：" + System.currentTimeMillis());
    }


    @JmsListener(destination = "io.renren.modules.queue.oauth.outToken")
    public void outQueue(String text) {
        log.debug("系统TOKEN消息队列,开始时间：" + System.currentTimeMillis());
        OauthTokenEntity oauthTokenEntity = new Gson().fromJson(text, OauthTokenEntity.class);
        oauthTokenService.outToken(oauthTokenEntity);
        log.debug("系统TOKEN消息队列,结束时间：" + System.currentTimeMillis());
    }
}
