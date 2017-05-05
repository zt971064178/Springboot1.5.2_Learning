package cn.itcast.zt;

import cn.itcast.zt.model.Bar;
import cn.itcast.zt.model.Foo;
import cn.itcast.zt.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Random;
import java.util.UUID;

/**
 * RabbitMQ集成springboot
 * https://segmentfault.com/a/1190000004401870
 * docker搭建rabbitmq集群
 * https://segmentfault.com/a/1190000004394741
 * 调用
 */
@SpringBootApplication
@ComponentScan(basePackages = "cn.itcast")
public class SpringbootRabbitmqApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRabbitmqApplication.class, args);
	}

	@Autowired
	private SenderService senderService;

	@Override
	public void run(String... strings) throws Exception {
		Random random = new Random() ;
		while (true){
			senderService.sendBar2Rabbitmq(new Bar(random.nextInt()));
			senderService.sendFoo2Rabbitmq(new Foo(UUID.randomUUID().toString()));
		}
	}
}
