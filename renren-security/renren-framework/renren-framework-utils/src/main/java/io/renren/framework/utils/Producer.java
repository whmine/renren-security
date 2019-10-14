package io.renren.framework.utils;

import javax.jms.Destination;

import com.google.gson.Gson;
import io.renren.modules.mq.dao.SysActivemqRequestDao;
import io.renren.modules.mq.entity.SysActivemqRequestEntity;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Autowired
    private SysActivemqRequestDao sysActivemqRequestDao;

    public void sendMessage(Destination destination, final String message) {
        ActiveMQQueue activeMQQueue =(ActiveMQQueue)destination;
        SysActivemqRequestEntity sysActivemqRequestEntity = new SysActivemqRequestEntity();
        sysActivemqRequestEntity.setActivemqType(activeMQQueue.getPhysicalName());
        sysActivemqRequestEntity.setActivemqContext(message);
        sysActivemqRequestEntity.setSysSystem("system");
        sysActivemqRequestDao.insert(sysActivemqRequestEntity);
        jmsTemplate.convertAndSend(destination, message);
    }

    public void sendMessage(Destination destination, Map message) {
        ActiveMQQueue activeMQQueue =(ActiveMQQueue)destination;
        String id = UUID.randomUUID().toString().replace("-", "");
        message.put("mqid",id);
        String mg = new Gson().toJson(message);

        SysActivemqRequestEntity sysActivemqRequestEntity = new SysActivemqRequestEntity();

        sysActivemqRequestEntity.setId(id);
        sysActivemqRequestEntity.setActivemqType(activeMQQueue.getPhysicalName());
        sysActivemqRequestEntity.setActivemqContext(mg);
        sysActivemqRequestEntity.setSysSystem("system");
        sysActivemqRequestDao.insert(sysActivemqRequestEntity);

        jmsTemplate.convertAndSend(destination, mg);
    }

}
