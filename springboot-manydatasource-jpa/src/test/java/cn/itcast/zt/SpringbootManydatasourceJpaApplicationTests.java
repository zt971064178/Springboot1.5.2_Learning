package cn.itcast.zt;

import cn.itcast.zt.domain.p.User;
import cn.itcast.zt.domain.p.UserRepository;
import cn.itcast.zt.domain.s.Message;
import cn.itcast.zt.domain.s.MessageRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootManydatasourceJpaApplication.class)
public class SpringbootManydatasourceJpaApplicationTests {
	@Autowired
	private UserRepository userRepository ;
	/* 注入em对象，通过name区分双数据源
	@Autowired
	@Qualifier(value = "entityManagerPrimary")
	private EntityManager entityManager1 ;

	@Autowired
	@Qualifier(value = "entityManagerSecondary")
	private EntityManager entityManager2 ;*/

	@Autowired
	private MessageRepository messageRepository ;

	@Before
	public void setUp() {

	}

	/**
	 * 双数据源切换测试
	 */
	@Test
	public void testJpa() {
		userRepository.save(new User("aaa", 10));
		userRepository.save(new User("bbb", 20));
		userRepository.save(new User("ccc", 30));
		userRepository.save(new User("ddd", 40));
		userRepository.save(new User("eee", 50));

		Assert.assertEquals(5, userRepository.findAll().size());

		messageRepository.save(new Message("o1", "aaaaaaaaaa"));
		messageRepository.save(new Message("o2", "bbbbbbbbbb"));
		messageRepository.save(new Message("o3", "cccccccccc"));

		Assert.assertEquals(3, messageRepository.findAll().size());
	}
}
