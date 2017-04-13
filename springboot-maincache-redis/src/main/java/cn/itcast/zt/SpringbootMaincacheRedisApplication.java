package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootMaincacheRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMaincacheRedisApplication.class, args);
	}
}
