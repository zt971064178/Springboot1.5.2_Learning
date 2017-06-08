package cn.itcast.zt.config;

import cn.itcast.zt.bean.Person;
import cn.itcast.zt.processor.PersonItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhangtian on 2017/6/8.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {
    // 执行流程：cvs->txt->xml

    @Bean
    public FlatFileItemReader<Person> csvItemReader(){
        System.out.println("===================== csvItemReader ========================");
        FlatFileItemReader<Person> csvItemReader = new FlatFileItemReader<Person>() ;
        csvItemReader.setResource(new ClassPathResource("data/sample-data.csv"));
        csvItemReader.setLineMapper(new DefaultLineMapper<Person>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames(new String[]{"name","age"});
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){{
                setTargetType(Person.class);
            }});
        }});
        return csvItemReader ;
    }

    @Bean
    public FlatFileItemReader<Person> txtItemReader() {
        System.out.println("===================== txtItemReader ========================");
        FlatFileItemReader<Person> txtItemReader = new FlatFileItemReader<Person>() ;
        txtItemReader.setResource(new ClassPathResource("data/sample-data.txt"));
        txtItemReader.setLineMapper(new DefaultLineMapper<Person>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames(new String[]{"name", "age"});
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){{
                setTargetType(Person.class);
            }});
        }});
        return txtItemReader ;
    }

    @Bean
    public FlatFileItemWriter<Person> txtItemWriter() {
        System.out.println("===================== txtItemWriter ========================");
        FlatFileItemWriter<Person> txtItemWriter = new FlatFileItemWriter<Person>() ;
        txtItemWriter.setAppendAllowed(true);
        txtItemWriter.setEncoding("UTF-8");
        txtItemWriter.setResource(new ClassPathResource("data/sample-data.txt"));
        txtItemWriter.setLineAggregator(new DelimitedLineAggregator<Person>(){{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<Person>(){{
                setNames(new String[]{"name", "age"});
            }});
        }});
        return txtItemWriter ;
    }

    @Bean
    public StaxEventItemWriter<Person> xmlItemWriter() {
        System.out.println("===================== xmlItemWriter ========================");
        StaxEventItemWriter<Person> xmlItemWriter = new StaxEventItemWriter<Person>() ;
        xmlItemWriter.setRootTagName("root");
        xmlItemWriter.setSaveState(true);
        xmlItemWriter.setEncoding("UTF-8");
        xmlItemWriter.setResource(new ClassPathResource("data/sample-data.xml"));
        xmlItemWriter.setMarshaller(new XStreamMarshaller(){{
            Map<String, Class<Person>> map = new HashMap<>() ;
            map.put("person", Person.class) ;
            setAliases(map);
        }});
        return xmlItemWriter ;
    }

    @Bean
    public Step stepCsv2Txt(StepBuilderFactory stepBuilderFactory, PersonItemProcessor processor,
                            ItemReader csvItemReader, ItemWriter txtItemWriter) {
        System.out.println("===================== stepCsv2Txt ========================");
        TaskletStep stepCsv2Txt = stepBuilderFactory.get("stepCsv2Txt")
                .<Person, Person>chunk(10)
                .reader(csvItemReader)
                .processor(processor)
                .writer(txtItemWriter)
                .build() ;
        return stepCsv2Txt ;
    }

    @Bean
    public Step stepTxt2Xml(StepBuilderFactory stepBuilderFactory, PersonItemProcessor processor,
                            ItemReader txtItemReader, ItemWriter xmlItemWriter) {
        System.out.println("===================== stepTxt2Xml ========================");
        TaskletStep stepTxt2Xls = stepBuilderFactory.get("stepTxt2Xml")
                .<Person, Person>chunk(10)
                .reader(txtItemReader)
                .processor(processor)
                .writer(xmlItemWriter)
                .build();
        return stepTxt2Xls;
    }

    @Bean
    public Job flatFileJob(JobBuilderFactory jobBuilderFactory, Step stepCsv2Txt, Step stepTxt2Xml) {
        System.out.println("===================== flatFileJob ========================");
        return jobBuilderFactory.get("flatFileJob")
                .incrementer(parameters -> {
                    Map<String, JobParameter> parameterMap = parameters.getParameters();
                    parameterMap.put("key", new JobParameter(UUID.randomUUID().toString()));
                    return parameters;
                })
                .start(stepCsv2Txt)
                .next(stepTxt2Xml)
                .build();
    }
}
