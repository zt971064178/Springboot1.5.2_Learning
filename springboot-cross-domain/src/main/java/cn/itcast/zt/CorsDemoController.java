package cn.itcast.zt;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangtian on 2017/5/10.
 */
@RestController
@RequestMapping("corsdemo")
public class CorsDemoController {
    @RequestMapping("/getData")
    public Map getData(HttpServletRequest req) {
        Enumeration headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = req.getHeader(key);
            System.out.println(headerNames.nextElement());
        }
        System.out.println(headerNames);
        Map map = new HashMap();
        map.put("name","hello");
        return map;
    }
}
