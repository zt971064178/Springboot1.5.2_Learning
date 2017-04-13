package cn.itcast.zt;

import cn.itcast.zt.domain.User;
import cn.itcast.zt.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootEhcacheApplication.class)
public class SpringbootEhcacheApplicationTests {
	@Autowired
	private UserRepository userRepository;

	//@Autowired
	//private CacheManager cacheManager;

	@Before
	public void setUp() {
		userRepository.save(new User("AAA", 10)) ;
	}

	@Test
	public void testEhcache() throws  Exception {
		User u1 = userRepository.findByName("AAA");
		System.out.println("第一次查询：" + u1.getAge());

		User u2 = userRepository.findByName("AAA");
		System.out.println("第二次查询：" + u2.getAge());

		u1.setAge(20);
		userRepository.save(u1);
		User u3 = userRepository.findByName("AAA");
		System.out.println("第三次查询：" + u3.getAge());
	}
}
