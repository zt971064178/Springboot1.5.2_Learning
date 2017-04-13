package cn.itcast.zt.exception;

/**
 * Created by zhangtian on 2017/4/13.
 */
public class HttpServletException extends RuntimeException {
    public HttpServletException(String message){
        super(message);
    }

    public HttpServletException(String message, Throwable cause){
        super(message, cause);
    }
}
