package cn.itcast.zt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Springboot启动加载数据
 * Created by zhangtian on 2017/4/18.
 */
@Component
@Order(value = 1)// 值越小优先级越高
public class MyRunnerLoader implements CommandLineRunner {
    @Override
    public void run(String... strings) throws Exception {
        System.out.println("服务启动执行，执行加载数据等操作!");
    }
}
