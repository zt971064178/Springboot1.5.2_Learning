package cn.itcast.zt.exception;

/**
 * Created by zhangtian on 2017/4/13.
 */
public class FormRepeatException extends  RuntimeException{
    public FormRepeatException(String message){ super(message);}

    public FormRepeatException(String message, Throwable cause){ super(message, cause);}
}
