package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // 开启异步调用支持
public class SpringbootAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAsyncApplication.class, args);
	}
}
