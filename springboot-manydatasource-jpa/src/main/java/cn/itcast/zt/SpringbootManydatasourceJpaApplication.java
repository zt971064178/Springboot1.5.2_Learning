package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(value = {JpaProperties.class})
public class SpringbootManydatasourceJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootManydatasourceJpaApplication.class, args);
	}
}
