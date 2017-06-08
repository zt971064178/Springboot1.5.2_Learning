## Springboot1.5.2_Learning-springbatch学习
https://git.oschina.net/huicode/springbatch-learn

## SpringBatch 入门篇
示例代码地址：https://git.oschina.net/huicode/springbatch-learn
#### 一、SpringBatch介绍：
-  SpringBatch 是一个大数据量的并行处理框架。通常用于数据的离线迁移，和数据处理，⽀持事务、并发、流程、监控、纵向和横向扩展，提供统⼀的接⼝管理和任务管理;SpringBatch是SpringSource和埃森哲为了统一业界并行处理标准为广大开发者提供方便开发的一套框架。
官方地址：[github.com/spring-projects/spring-batch](https://github.com/spring-projects/spring-batch)

----
- SpringBatch 本身提供了*重试，异常处理，跳过，重启、任务处理统计，资源管理*等特性，这些特性开发者看重他的主要原因;
- SpringBatch 是一个轻量级的批处理框架;
- SpringBatch 结构分层，业务与处理策略、结构分离;
- 任务的运行的实例状态，执行数据，参数都会落地到数据库;

----
#### 二、快速入门：
 1、 pom.xml 添加
```
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-batch</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
```

>为什么在这里需要引入一个数据库，不引入数据库包为什么会报错？
问题的原因在这里： `AbstractBatchConfiguration` 源码如下： `@BatchConfiguration` 是springbatch启用的标识，`AbstractBatchConfiguration`是该注解的具体逻辑处理，AbstractBatchConfiguration 中需要注入`dataSources`,在没database 为空的时候注入失败，程序启动报错！
>为什么需要注入datasouce? 任务的实例状态等数据会落地到到数据库中，详细流程见本文 #4 执行流程图

```java
@Configuration
@Import(ScopeConfiguration.class)
public abstract class AbstractBatchConfiguration implements ImportAware {

	@Autowired(required = false)
	private Collection<DataSource> dataSources;

	private BatchConfigurer configurer;

	@Bean
	public JobBuilderFactory jobBuilders() throws Exception {
		return new JobBuilderFactory(jobRepository());
	}
	
	//。。。
}
```


2、创建BatchConfiguration（也可以是其他类名）
```java

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@EnableAutoConfiguration
public class BatchConfiguration {


    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet((contribution, chunkContext) -> null)
                .build();
    }

    @Bean
    public Job job(Step step1) throws Exception {
        return jobBuilderFactory.get("job1")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .build();
    }
}
```
#### 三、SpringBatch 的分层架构
- Insfrastructure 策略管理：包括任务的失败重试，异常处理,事务，skip,以及数据的输入输出（文本文件，DB，Message）
- Core: springBatch 的核心，包括JobLauch,job,step等等
-  Application: 业务处理，创建任务，决定任务的执行方式（定时任务，手动触发等）

![image.png](http://upload-images.jianshu.io/upload_images/2178607-8eea5f178a993578.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 四、SpringBatch 执行流程

![image.png](http://upload-images.jianshu.io/upload_images/2178607-e5efdc1ea4bf2db2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)