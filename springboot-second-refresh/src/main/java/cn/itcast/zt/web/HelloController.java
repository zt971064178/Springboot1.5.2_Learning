package cn.itcast.zt.web;

import cn.itcast.zt.aop.RequestLimit;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangtian on 2017/4/13.
 */
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping("/hello")
    @RequestLimit(count = 10)
    public String hello(HttpServletRequest request) {
        return "Hello World";
    }
}
