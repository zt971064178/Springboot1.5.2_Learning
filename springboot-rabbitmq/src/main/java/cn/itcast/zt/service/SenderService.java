package cn.itcast.zt.service;

import cn.itcast.zt.model.Bar;
import cn.itcast.zt.model.Foo;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息收发--->发送服务
 * Created by zhangtian on 2017/5/5.
 */
@Component
public class SenderService {
    @Autowired
    private RabbitMessagingTemplate rabbitMessagingTemplate ;

    public void sendFoo2Rabbitmq(final Foo foo) {
        this.rabbitMessagingTemplate.convertAndSend("exchange", "queue.foo", foo);
    }

    public void sendBar2Rabbitmq(final Bar bar){
        this.rabbitMessagingTemplate.convertAndSend("exchange", "queue.bar", bar);
    }
}
