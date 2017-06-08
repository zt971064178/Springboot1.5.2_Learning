package cn.itcast.zt;

import cn.itcast.zt.launcher.MyJobLauncher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbatchBatchReadwriteApplication.class)
@EnableBatchProcessing
public class SpringbatchBatchReadwriteApplicationTests {

	@Autowired
	private MyJobLauncher jobLauncher ;

	@Test
	public void contextLoads() throws Exception {
		JobExecution handle = jobLauncher.handle() ;
		BatchStatus status = handle.getStatus() ;
		Assert.assertEquals(status.getBatchStatus(), javax.batch.runtime.BatchStatus.COMPLETED);
	}
}
