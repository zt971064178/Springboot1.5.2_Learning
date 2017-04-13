package cn.itcast.zt.exception;

/**
 * Created by zhangtian on 2017/4/13.
 */
public class RequestLimitException extends  RuntimeException {
    private static final long serialVersionUID = 1364225358754654702L;

    public RequestLimitException() {
        super("HTTP请求超出设定的限制");
    }

    public RequestLimitException(String message) {
        super(message);
    }

    public RequestLimitException(String message, Throwable cause){
        super(message, cause);
    }
}
