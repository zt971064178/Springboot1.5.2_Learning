package cn.itcast.zt;

import cn.itcast.zt.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by zhangtian on 2017/4/11.
 */
@Configuration
public class RedisConfig {

    @Bean(name = "jedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory(){
     return new JedisConnectionFactory() ;
    }

    /**
     * 注入bean
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, User> redisTemplate(@Qualifier(value = "jedisConnectionFactory") RedisConnectionFactory factory) {
        RedisTemplate<String, User> template = new RedisTemplate<String ,User>() ;
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());

        return template ;
    }
}
