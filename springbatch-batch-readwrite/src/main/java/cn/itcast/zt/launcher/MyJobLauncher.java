package cn.itcast.zt.launcher;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

/**
 * Created by zhangtian on 2017/6/8.
 */
@Service
public class MyJobLauncher {
    private static final Logger LOGGER = Logger.getLogger(MyJobLauncher.class.getName()) ;
    @Autowired
    private JobLauncher jobLauncher ;
    @Autowired
    private Job job ;

    public MyJobLauncher(JobLauncher jobLauncher, Job job){
        this.jobLauncher = jobLauncher ;
        this.job = job ;
    }

    public JobExecution handle() throws Exception{
        JobExecution execution = jobLauncher.run(job, new JobParameters()) ;
        LOGGER.info("cn.itcast.zt.launcher.MyJobLauncher.handle:{}"+execution);
        return execution ;
    }

}
