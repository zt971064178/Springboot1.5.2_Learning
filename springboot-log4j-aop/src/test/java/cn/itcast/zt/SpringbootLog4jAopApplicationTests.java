package cn.itcast.zt;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootLog4jAopApplication.class)
public class SpringbootLog4jAopApplicationTests {
	private Logger logger = Logger.getLogger(getClass());

	@Test
	public void test() throws Exception {
		logger.info("输出info");
		logger.debug("输出debug");
		logger.error("输出error");
	}
}
