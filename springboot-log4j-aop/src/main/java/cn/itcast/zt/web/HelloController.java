package cn.itcast.zt.web;

import org.springframework.web.bind.annotation.*;

/**
 * Created by zhangtian on 2017/4/12.
 */
@RestController
public class HelloController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }
}
