package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootTransactionApplication {
	// 测试查看使用的是哪个事务管理器
	/*@Bean
	public Object testBean(@Qualifier(value = "platformTransactionManager") PlatformTransactionManager platformTransactionManager) {
		System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
		return new Object();
	}*/

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTransactionApplication.class, args);
	}
}
