package cn.itcast.zt.service;

import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by zhangtian on 2017/4/18.
 */
@Service
public class RetryService {
    private Random random = new Random();

    @Retryable(include = RuntimeException.class, maxAttempts = 3)
    public String readingList() {
        int randomInt= random.nextInt(10) ;
        if(randomInt < 8){  //模拟调用失败情况
            System.out.println("调用失败发生异常");
            throw new RuntimeException("call dependency service fail.");
        }else{
            System.out.println("调用成功");
            return "调用成功 ;number:"+randomInt;
        }
    }

    @Recover
    public String reliable() {
        return "Cloud Native Java (O'Reilly)";
    }
}
