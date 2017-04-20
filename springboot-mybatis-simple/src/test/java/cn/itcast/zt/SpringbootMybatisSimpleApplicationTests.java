package cn.itcast.zt;

import cn.itcast.zt.domain.User;
import cn.itcast.zt.domain.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMybatisSimpleApplication.class)
@Transactional
public class SpringbootMybatisSimpleApplicationTests {

	@Autowired(required = false)
	private UserMapper userMapper ;

	@Test
	@Rollback
	public void findByName() {
		userMapper.insert("AAA",20) ;
		User u = userMapper.findByName("AAA") ;
		Assert.assertEquals(20, u.getAge().intValue());
	}
}
