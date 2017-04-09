package cn.itcast.zt.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by Administrator on 2017/4/9/009.
 */
@RestController
@RequestMapping(value = "/")
public class HelloWorldController {
    @ApiIgnore
    @RequestMapping(value = "hello",method = RequestMethod.GET)
    public String index() {
        return "Hello World" ;
    }
}
