package cn.itcast.zt.web;

import cn.itcast.zt.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangtian on 2017/4/18.
 */
@RestController
public class RetryController {
    @Autowired
    private RetryService retryService ;

    @GetMapping(value = "/retry")
    public String retry() {
        return retryService.readingList() ;
    }
}
