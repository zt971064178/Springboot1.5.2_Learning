package cn.itcast.zt.web;

import cn.itcast.zt.exception.MyException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/4/9/009.
 */
@RestController
@RequestMapping(value = "/")
public class HelloController {
    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String index() throws  Exception{
        throw  new Exception("发生错误") ;
    }

    @RequestMapping(value = "json", method = RequestMethod.GET)
    public String json() throws  MyException{
        throw new MyException("发生错误2") ;
    }

    @RequestMapping("/")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("host","http://blog.didispace.com") ;
        return "index" ;
    }
}
