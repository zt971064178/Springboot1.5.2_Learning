package cn.itcast.zt.aop;

import cn.itcast.zt.dao.BlackListDao;
import cn.itcast.zt.exception.RequestLimitException;
import cn.itcast.zt.model.BlackList;
import cn.itcast.zt.utils.IPAddressUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.*;

/**
 * URL 拦截器
 * Created by zhangtian on 2017/4/14.
 */
public class URLInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private BlackListDao blackListDao ;
    private Map<String, Integer> redisTemplate = new HashMap<String, Integer>();
    private static final Logger logger = LoggerFactory.getLogger(URLInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ip = IPAddressUtils.getClientIpAddress(httpServletRequest) ;
        List<BlackList> baBlackLists = blackListDao.findByIP(ip) ;
        if(baBlackLists == null || baBlackLists.isEmpty()) {
            urlHandle(httpServletRequest, 5000, 10);
            return true;
        }else {
            httpServletResponse.setCharacterEncoding("UTF-8");
            PrintWriter out = httpServletResponse.getWriter() ;
            out.print("您已经被列入黑名单中，请及时和管理员联系，解除黑名单限制");
            return false ;
        }
    }

    public void urlHandle(HttpServletRequest request, long limitTime,int limitCount) throws RequestLimitException {
        try {
            System.out.println("拦截器！！！");
            String ip = IPAddressUtils.getClientIpAddress(request);
            String url = request.getRequestURL().toString();
            final String key = "req_limit_".concat(url).concat(ip);

            if(redisTemplate.get(key)==null || redisTemplate.get(key)==0){
                redisTemplate.put(key,1);
            }else{
                redisTemplate.put(key,redisTemplate.get(key)+1);
            }
            int count = redisTemplate.get(key);
            if (count > 0) {
                Timer timer= new Timer();
                TimerTask task  = new TimerTask(){
                    @Override
                    public void run() {
                        redisTemplate.remove(key);
                    }
                };
                timer.schedule(task, limitTime);
            }
            if (count > limitCount){
                Calendar calendar = Calendar.getInstance();
                Date iptime=calendar.getTime();
                BlackList blackList = new BlackList(ip, iptime);
                blackListDao.addBlackList(blackList);
                throw new RequestLimitException();
            }
        } catch (RequestLimitException e) {
            throw e;
        } catch (Exception e) {
            logger.error("发生异常: ", e);
        }
    }
}
