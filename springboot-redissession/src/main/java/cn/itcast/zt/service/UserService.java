package cn.itcast.zt.service;

import cn.itcast.zt.dao.UserDao;
import cn.itcast.zt.exception.CacheException;
import cn.itcast.zt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Redis缓存测试：注意没有缓存注解都需要指定cacheNames，否则报错
 * key与keyGenerator冲突，key生成策略只能有一个
 * Created by zhangtian on 2017/4/14.
 */
@Cacheable(cacheNames = "users"/*, keyGenerator = "wiselyKeyGenerator"*/)
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    // key与keyGenerator冲突，key生成策略只能有一个
    @CacheEvict(cacheNames ="users" /*,key="'user'"*/, beforeInvocation = false, allEntries = false, keyGenerator = "wiselyKeyGenerator")
    public int save(User user) throws Exception {
        System.out.println("================");
        return userDao.save(user);
    }

    @CachePut(cacheNames ="users" /*,key = "'user_'+#user.getUuid()"*/, keyGenerator = "wiselyKeyGenerator")
    public User update(User user) throws CacheException{
        User user1 = userDao.findByUuid(user.getUuid());
        if (null == user1){
            throw new CacheException("Not Find");
        }
        user1.setAge(user.getAge());
        user1.setName(user.getName());
        userDao.update(user1);
        return user1;
    }

    @Cacheable(cacheNames ="users" /*,key="'user_'+#uuid"*/, keyGenerator = "wiselyKeyGenerator")
    public User findByUuid(String uuid){
        System.err.println("没有走缓存！"+uuid);
        return userDao.findByUuid(uuid);
    }

   @CacheEvict(cacheNames ="users" /*,key = "'user_'+#uuid"*/, allEntries = false, beforeInvocation = false, keyGenerator = "wiselyKeyGenerator")//这是清除缓存
    public void delete(String uuid){
        userDao.delete(uuid);
    }
}
