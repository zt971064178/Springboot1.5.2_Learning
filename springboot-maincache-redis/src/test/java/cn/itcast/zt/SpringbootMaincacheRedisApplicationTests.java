package cn.itcast.zt;

import cn.itcast.zt.domain.User;
import cn.itcast.zt.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMaincacheRedisApplication.class)
public class SpringbootMaincacheRedisApplicationTests {
	@Autowired
	private UserRepository userRepository;

	@Autowired(required = false)
	private CacheManager cacheManager;

	@Before
	public void before() {
		userRepository.save(new User("AAA", 10));
	}

	@Test
	public void test() throws Exception {

		User u1 = userRepository.findByName("AAA");
		System.out.println("第一次查询：" + u1.getAge());

		User u2 = userRepository.findByName("AAA");
		System.out.println("第二次查询：" + u2.getAge());

		u1.setAge(20);
		userRepository.save(u1);
		User u3 = userRepository.findByName("AAA");
		System.out.println("第三次查询：" + u3.getAge());
	}

	@Autowired
	private StringRedisTemplate stringRedisTemplate ;
	@Test
	public void testRedis() {
		//保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		//读取字符串
		String aaa = stringRedisTemplate.opsForValue().get("aaa");
		System.out.println(aaa);
	}
}
