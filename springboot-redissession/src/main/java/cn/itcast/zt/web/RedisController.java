package cn.itcast.zt.web;

import cn.itcast.zt.model.User;
import cn.itcast.zt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhangtian on 2017/4/14.
 */
@RestController
@RequestMapping("/")
public class RedisController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate ;

    @Autowired(required = false)
    private CacheManager cacheManager ;

    @Value("${server.port}")
    String port;

    @RequestMapping(value = "/session", method = RequestMethod.GET)
    public Object getSession(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("SessionId", request.getSession().getId());
        map.put("ServerPort", "服务端口号为 "+port);
        return map;
    }

    /**
     * 测试redis连接
     * @return
     */
    @GetMapping(value = "/optRedis")
    public String optRedis() {
        redisTemplate.opsForValue().set("book", "天局");
        return redisTemplate.opsForValue().get("book").toString() ;
    }

    /**
     * 测试Spring的CacheManager
     * @return
     */
    @GetMapping(value = "/optCacheManager")
    public String optCacheManager() {
        Collection<String> collections = cacheManager.getCacheNames() ;
        System.out.println(collections);
        System.out.println(cacheManager.getCache("users"));
        cacheManager.getCache("users").put("fengyun", "tech") ;
        System.out.println(cacheManager.getCache("users").getName());
        System.out.println(cacheManager.getCache("users").get("fengyun",String.class)) ;
        return cacheManager.getCache("users").get("fengyun", String.class) ;
    }

    @RequestMapping(value = "/redis", method = RequestMethod.GET)
    public String redisTest(){
        System.out.println("====== 进行 Redis 缓存试验 ======");
        User user = new User();
        //生成第一个用户的唯一标识符 UUID
        String u1_uuid = UUID.randomUUID().toString();
        //去掉 UUID 的 - 符号
        String uuid1 = u1_uuid.replace("-", "") ;
        user.setUuid(uuid1);
        user.setAge(20);
        user.setName("张三");

        try {
            userService.save(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("保存用户出现异常");
        }

        //第一次查询
        System.out.println(userService.findByUuid(user.getUuid()));
        //通过缓存查询
        System.out.println(userService.findByUuid(user.getUuid()));

        System.out.println("====== 修改 Redis 缓存数据 ======");
        //修改用户数据
        user.setAge(18);
        user.setName("李四");
        System.out.println(userService.update(user));

        System.out.println(userService.findByUuid(user.getUuid()));

        return "success";
    }
}
