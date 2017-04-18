package cn.itcast.zt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class SpringbootRetryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRetryApplication.class, args);
	}
}
/*
	@Retryable 几个比较重要的参数
	value : 抛出某些异常的时候才进行重试
	include: 和value的含义类似
	exclude: 排除某些异常不进行重试，这里要注意的是，如果value、include、exclude都为空的时候则任何异常都进行重试
	maxAttempts: 最大重试次数
	backoff: 重试策略，默认使用注解@Backoff 而@BackOff中value的默认为1000L，multiplier的默认值为0，表示默认情况下固定暂停1秒进行重试，
	如果我们把multiplier调整为2，那么表面第一次进行重试时间为1秒，第二次为2秒，第三次为4秒。
	注意：注解了@Recover方法的参数可以没有，如果有一定是和注解@Retryable处理的异常是一样的或者是它的子集，
	否则recover方法将不能被执行，并不是网上一些人瞎逼逼说一定要和@Retryable处理的异常要保持一致
 */