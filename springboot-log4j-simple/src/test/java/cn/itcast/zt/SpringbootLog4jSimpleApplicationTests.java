package cn.itcast.zt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.apache.log4j.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootLog4jSimpleApplication.class)
public class SpringbootLog4jSimpleApplicationTests {
	private Logger logger = Logger.getLogger(this.getClass().getName());
	@Test
	public void testLog4j() {
		logger.info("输出info");
		logger.debug("输出debug");
		logger.error("输出error");
	}
}
