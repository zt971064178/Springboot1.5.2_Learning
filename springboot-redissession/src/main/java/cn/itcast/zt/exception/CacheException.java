package cn.itcast.zt.exception;

/**
 * Created by zhangtian on 2017/4/14.
 */
public class CacheException extends RuntimeException {
    public CacheException(String message){ super(message);}

    public CacheException(String message, Throwable cause){ super(message, cause);}
}
