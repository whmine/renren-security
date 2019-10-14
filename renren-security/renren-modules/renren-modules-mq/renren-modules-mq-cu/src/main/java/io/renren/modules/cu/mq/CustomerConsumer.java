package io.renren.modules.cu.mq;

import com.google.gson.Gson;
import io.renren.modules.cu.entity.CuAccountEntity;
import io.renren.modules.cu.entity.CuProfitEntity;
import io.renren.modules.cu.service.CuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@Slf4j
public class CustomerConsumer {


    @Autowired
    private CuInfoService cuInfoService;

    @JmsListener(destination = "io.renren.modules.queue.cu.customer" )
    public void receiveQueue(String text) {
        log.debug("系统客户信息初始化消息队列,开始时间：" + System.currentTimeMillis());
        Map map = new Gson().fromJson(text, Map.class);
        cuInfoService.init(map);
        log.debug("系统客户信息初始化消息队列,结束时间：" + System.currentTimeMillis());
    }

    @JmsListener(destination = "io.renren.modules.queue.cu.customer.del" )
    public void receiveQueueDel(String text) {
        log.debug("系统客户信息初始化消息队列,开始时间：" + System.currentTimeMillis());
        Map map = new Gson().fromJson(text, Map.class);
        cuInfoService.del(map);
        log.debug("系统客户信息初始化消息队列,结束时间：" + System.currentTimeMillis());
    }

    @JmsListener(destination = "io.renren.modules.queue.cu.customer.profit" )
    public void receiveQueueProfit(String text) {
        log.debug("系统收益流水记录消息队列,开始时间：" + System.currentTimeMillis());
        CuProfitEntity map = new Gson().fromJson(text, CuProfitEntity.class);
        cuInfoService.profit(map);
        log.debug("系统收益流水记录消息队列,结束时间：" + System.currentTimeMillis());
    }

    @JmsListener(destination = "io.renren.modules.queue.cu.customer.account" )
    public void receiveQueueAccount(String text) {
        log.debug("系统积分变更消息队列,开始时间：" + System.currentTimeMillis());
        CuAccountEntity map = new Gson().fromJson(text, CuAccountEntity.class);
        cuInfoService.account(map);
        log.debug("系统积分变更消息队列,结束时间：" + System.currentTimeMillis());
    }
}
