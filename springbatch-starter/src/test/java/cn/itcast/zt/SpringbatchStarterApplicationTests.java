package cn.itcast.zt;

import cn.itcast.zt.config.BatchConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.logging.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbatchStarterApplication.class)
@EnableBatchProcessing
public class SpringbatchStarterApplicationTests {
	private Logger logger = Logger.getLogger(SpringbatchStarterApplicationTests.class.getName()) ;
	private BatchConfiguration batchConfiguration ;

	@Test
	public void contextLoads() {
		logger.info("cn.itcast.zt.SpringbatchLearn1ApplicationTests.contextLoads");
	}
}
