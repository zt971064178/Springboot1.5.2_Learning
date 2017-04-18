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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootMybatisComplexApplication.class)
@Transactional
public class SpringbootMybatisComplexApplicationTests {

	@Autowired(required = false)
	private UserMapper userMapper ;

	@Test
	@Rollback
	public void testUserMapper() throws Exception {
		// insert一条数据，并select出来验证
		userMapper.insert("MMM", 20);
		User u = userMapper.findByName("MMM");

		// update一条数据，并select出来验证
		u.setAge(30);
		userMapper.update(u);
		u = userMapper.findByName("MMM");
		Assert.assertEquals(30, u.getAge().intValue());

		// 删除这条数据，并select验证
		userMapper.delete(u.getId());
		u = userMapper.findByName("MMM");
		Assert.assertEquals(null, u);

		u = new User("BBB", 30);
		userMapper.insertByUser(u);
		Assert.assertEquals(30, userMapper.findByName("BBB").getAge().intValue());

		Map<String, Object> map = new HashMap<>();
		map.put("name", "CCC");
		map.put("age", 40);
		userMapper.insertByMap(map);
		Assert.assertEquals(40, userMapper.findByName("CCC").getAge().intValue());

		List<User> userList = userMapper.findAll();
		for(User user : userList) {
			Assert.assertEquals(null, user.getId());
			Assert.assertNotEquals(null, user.getName());
		}
	}
}
