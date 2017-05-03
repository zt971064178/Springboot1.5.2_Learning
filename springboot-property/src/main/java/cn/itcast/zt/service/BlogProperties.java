package cn.itcast.zt.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * 测试参数注入
 * Created by Administrator on 2017/4/8/008.
 */
@Component
public class BlogProperties {
    @Value("${com.didispace.blog.name}")
    private String name;

    @Value("${com.didispace.list.book}")
    private List<String> bookname ;
    @Value("${com.didispace.set.play}")
    private Set<String> play ;

    @Value("${com.didispace.blog.title}")
    private String title ;
    @Value("${com.didispace.blog.desc}")
    private String desc ;

    @Value("${com.didispace.blog.value}")
    private String value ;
    @Value("${com.didispace.blog.number}")
    private Integer number ;
    @Value("${com.didispace.blog.bignumber}")
    private Long bigNumber ;
    @Value("${com.didispace.blog.test1}")
    private Integer test1 ;
    @Value("${com.didispace.blog.test2}")
    private Integer test2 ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getBookname() {
        return bookname;
    }

    public void setBookname(List<String> bookname) {
        this.bookname = bookname;
    }

    public Set<String> getPlay() {
        return play;
    }

    public void setPlay(Set<String> play) {
        this.play = play;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getBigNumber() {
        return bigNumber;
    }

    public void setBigNumber(Long bigNumber) {
        this.bigNumber = bigNumber;
    }

    public Integer getTest1() {
        return test1;
    }

    public void setTest1(Integer test1) {
        this.test1 = test1;
    }

    public Integer getTest2() {
        return test2;
    }

    public void setTest2(Integer test2) {
        this.test2 = test2;
    }
}
