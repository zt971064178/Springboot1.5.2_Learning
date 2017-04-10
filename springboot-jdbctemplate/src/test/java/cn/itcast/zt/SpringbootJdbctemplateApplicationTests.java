package cn.itcast.zt;

import cn.itcast.zt.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootJdbctemplateApplication.class)
public class SpringbootJdbctemplateApplicationTests {

	@Autowired
	private UserService userService ;

	@Before
	public void setUp() {
		userService.deleteAllUsers();
	}

	@Test
	public void testJdbcTemplate() throws  Exception {
		// 插入5个用户
		userService.create("张三",10);
		userService.create("李四",20);
		userService.create("王五",30);
		userService.create("赵六",40);
		userService.create("徐七",50);

		// 查询数据库应该有5个用户
		Assert.assertEquals(5,  userService.countAllUsers().intValue());

		// 删除两个用户
		userService.deleteByName("徐七");
		userService.deleteByName("张三");

		// 查询数据库应该有3个用户
		Assert.assertEquals(3, userService.countAllUsers().intValue());
	}
}
