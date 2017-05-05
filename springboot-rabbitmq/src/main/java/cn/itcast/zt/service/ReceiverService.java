package cn.itcast.zt.service;

import cn.itcast.zt.model.Bar;
import cn.itcast.zt.model.Foo;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收
 * Created by zhangtian on 2017/5/5.
 */
@Component
public class ReceiverService {

    @RabbitListener(queues = "queue.foo")
    public void receiverFooQueue(Foo foo) {
        System.out.println("Received for <"+ foo.getName() + ">");
    }

    @RabbitListener(queues = "queue.bar")
    public void receiveBarQueue(Bar bar) {
        System.out.println("Received Bar<" + bar.getAge() + ">");
    }
}
