package io.renren.modules.app.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class AppConsumer {
    @JmsListener(destination = "io.renren.modules.app.queue")
    public void receiveQueue(String text) {
    }
}
