package cn.itcast.zt.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhangtian on 2017/4/14.
 */
@RestController
@RequestMapping("/url/")
public class URLController {
    @RequestMapping("test")
    public String URLtest() {
        return "success";
    }
}
