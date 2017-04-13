package cn.itcast.zt.web;

import cn.itcast.zt.aop.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhangtian on 2017/4/13.
 */
@Controller
public class URLController {
    @Token(save = true)
    @RequestMapping("/savetoken")
    @ResponseBody
    public String getToken(HttpServletRequest request, HttpServletResponse response){
        return (String) request.getSession().getAttribute("token");
    }

    @Token(remove = true)
    @RequestMapping("/removetoken")
    @ResponseBody
    public String removeToken(HttpServletRequest request, HttpServletResponse response){
        return "success";
    }
}
