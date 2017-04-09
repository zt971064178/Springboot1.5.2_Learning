package cn.itcast.zt.exception;

import cn.itcast.zt.dto.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/4/9/009.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView modelAndView = new ModelAndView() ;
        modelAndView.addObject("exception", e) ;
        modelAndView.addObject("url",req.getRequestURL()) ;
        modelAndView.setViewName("error");

        return modelAndView ;
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ErrorInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
        ErrorInfo<String> errorInfo = new ErrorInfo() ;
        errorInfo.setMessage(e.getMessage());
        errorInfo.setCode(ErrorInfo.ERROR);
        errorInfo.setUrl(req.getRequestURL().toString());
        errorInfo.setData("Some Date");

        return  errorInfo ;
    }
}
