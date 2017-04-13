package cn.itcast.zt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Created by zhangtian on 2017/4/13.
 */
@Configuration
public class MainConfig {

    @Bean
    public JavaMailSenderImpl mailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl() ;
        mailSender.setHost("smtp.163.com");
        mailSender.setUsername("zhang.tian20080808@163.com");
        mailSender.setPassword("zxcv19880714");
        mailSender.setPort(25);
        return mailSender ;
    }
}
