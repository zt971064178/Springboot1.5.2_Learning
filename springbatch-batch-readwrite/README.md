## Springboot1.5.2_Learning-springbatch学习
https://git.oschina.net/huicode/springbatch-learn

## SpringBatch Flatfile(XML,CSV,TXT) 文件的批量读写

> 该系列课程中的示例代码使用springBatch版本为3.0.7;讲解可能会讲一些4.0.X的特性


示例代码地址:https://git.oschina.net/huicode/springbatch-learn

在这里说到FlatFile的时候，其实XML,CSV,TXT三种文件格式中XML是不属于FlatFile 的，XML在Batch中是属于StaxEvent，但是本章主要讲述SpringBatch对于文件的读写，所以放到一起说明。

> 本文主要讲解通过SpringBatch来处理文本格式的文件，在实际的业务中也许文本文件转DB data或者DB data转文本文件的情形更多

> 说明：在spring官方文档中的说明都是基于xml配置的方式来实现`ItemReader`、`ItemWriter`、`Job`、`Step`的配置的，为了符合springBoot的配置方式，示例代码都是配置代码实现的

####一、pom.xml 配置
> 使用springBatch对于 xml 文件进行读写 操作时需要引入`spring-oxm` 包

```java
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-batch</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-oxm</artifactId>
    <version>4.3.8.RELEASE</version>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <scope>runtime</scope>
</dependency>
```

#### 二、项目结构说明
![image.png](http://upload-images.jianshu.io/upload_images/2178607-146b00ca7d9e485e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

为了更好的管理代码，根据类对应的职责创建了不同的包

 - launcher: 执行，调用job
 - processor: 负责数据的转换与处理
 

#### 三、文件读写
使用 FlatFileItemReader,FlatFileItemWriter 帮我们做了什么？
1、FlatFileItem 能够以固定长度进行读写（对于大文件尤为重要），开发者不用关注文件的读写流问题
2、对文件读写时能够保证事物

##### 详解 FlatFileItemReader
FlatFileItemReader 是对文件读取的类，一般是对表格数据，或者文本文件数据的处理。该类的以下两个属性是必须要set的

- setResource 指定文件资源的位置：通过ClassPathResource（类所在路径）或者FileSystemResource（文件系统所在路径）来指定要读取的文件
- setLineMapper 行映射：指定行与实体对象之间的映射关系，示例代码使用了`DefaultLineMapper`
- seEncoding 读取编码格式，默认为‘iso-8859-1’
- setStrict 严格模式，输入文件不存在会抛出异常，阻断当前job;默认为true

```java
@Bean
    public FlatFileItemReader<Person> csvItemReader() {
        FlatFileItemReader<Person> csvItemReader = new FlatFileItemReader<>();
        csvItemReader.setResource(new ClassPathResource("data/sample-data.csv"));
        csvItemReader.setLineMapper(new DefaultLineMapper<Person>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"name", "age"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {{
                setTargetType(Person.class);
            }});
        }});
        return csvItemReader;
    }
```

##### 详解 FlatFileItemWriter 

FlatFileItemWriter 是对文件的写入类，将批量数据流写入文件，该类使用必须了解下面几个方法的用法：
- setLineAggregator 和 FlatFileItemReader 的setLineMapper方法有着相似之处，setLineAggregator方法是将对象属性聚合为字符串，聚合时根据需要设置分隔符（setDelimiter），以及对象属性对应的字符名称（setFieldExtractor）
  > - LineAggregator 接口是创建对象属性聚合字符串
  - ExtractorLineAggregator 是抽象类实现LineAggregator接口。使用 FieldExtractor将对象属性转换为数组，该类的扩展类负责将数组转换字符串（doAggregate）
    - DelimitedLineAggregator 继承 ExtractorLineAggregator。是一种更常使用的聚合方式、将数组用指定符号分割，默认使用逗号
    - FormatterLineAggregator 继承 ExtractorLineAggregator。对数组字符串的最大长度，最小长度的校验，以及格式化操作
  - PassThroughLineAggregator 实现LineAggregator接口，是一种简单的聚合方式使用对象的`.toString()`返回值，作为聚合字符串
 - RecursiveCollectionLineAggregator 实现LineAggregator接口，将Collection<T> 集合遍历，集合的聚合通过系统行分割符分割，对象字段的聚合使用LineAggregator接口对应的聚合方法是可选择的。

- setResource 是指定输出文件的位置，同样也是必须的，示例代码中使用了`new ClassPathResource("/data/sample-data.txt")` 实际开发中更多的是 `new FilePathResource()`
- setEncoding 设置编码，默认也是 `iso-8859-1`
![image.png](http://upload-images.jianshu.io/upload_images/2178607-425c2c8c3be2d5df.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> 示例代码：
```java
@Bean
public FlatFileItemWriter<Person> txtItemWriter() {
    FlatFileItemWriter<Person> txtItemWriter = new FlatFileItemWriter<>();
    txtItemWriter.setAppendAllowed(true);
    txtItemWriter.setEncoding("UTF-8");
    txtItemWriter.setResource(new ClassPathResource("/data/sample-data.txt"));
    txtItemWriter.setLineAggregator(new DelimitedLineAggregator<Person>() {{
        setDelimiter(",");
        setFieldExtractor(new BeanWrapperFieldExtractor<Person>() {{
            setNames(new String[]{"name", "age"});
        }});
    }});
    return txtItemWriter;
}
```

##### XML文件处理
> 对xml文件的处理需要引入`spring-oxm`包，仅对xml的输出进行详解，XML读取类似
对xml写入操作的对象为StaxEventItemWriter，与FlatFileItemWriter的使用类似，StaxEventItemWriter 与 FlatFileItemWriter都有着setResource方法，StaxEventItemWriter默认编码为utf-8
- setRootTagName 设置根节点标签名称
- setMarshaller 指定对象与节点 映射关系

示例代码：
```
@Bean
    public StaxEventItemWriter<Person> xmlItemWriter() {
        StaxEventItemWriter<Person> xmlItemWriter = new StaxEventItemWriter<>();
        xmlItemWriter.setRootTagName("root")
        xmlItemWriter.setEncoding("UTF-8");
        xmlItemWriter.setResource(new ClassPathResource("/data/sample-data.xml"));
        xmlItemWriter.setMarshaller(new XStreamMarshaller() {{
            Map<String, Class<Person>> map = new HashMap<>();
            map.put("person",Person.class);
            setAliases(map);
        }});
        return xmlItemWriter;
    }
```
-------------------
#### 四、自定义处理器ItemProcessor
> ItemProcessor主要负责数据的转换与处理，将读取到的文件 转换为输出文件的对象，所以`temProcessor<Person, Person>`这里不一定都是Person,实现process方法，实现数据的转换与处理。
```
public class PersonItemProcessor implements ItemProcessor<Person, Person> {
    @Override
    public Person process(Person person) throws Exception {
        person.setAge(person.getAge() + 1);
        person.setName(person.getName() + "-_-");
        return person;
    }
}
```

#### 五、整个Job 的处理流程

![image.png](http://upload-images.jianshu.io/upload_images/2178607-68258844b79761af.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

读取csv文件 -> 数据处理,转换 -> 输出txt文件 -> 读取txt文件 ->数据处理,转换 -> 输出XML文件