package cn.itcast.zt.domain;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhangtian on 2017/4/13.
 */
@CacheConfig(cacheNames = "users")
public interface UserRepository extends JpaRepository<User, Long> {
    @Cacheable(key = "#p0")
    User findByName(String name) ;

    @CachePut(key = "#p0.name")
    User save(User user);
}
