package cn.itcast.zt.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/4/8/008.
 */
@RestController
@RequestMapping
public class HelloWorldController {
    @RequestMapping("/hello")
    public String index() {
        return "Hello World" ;
    }
}
