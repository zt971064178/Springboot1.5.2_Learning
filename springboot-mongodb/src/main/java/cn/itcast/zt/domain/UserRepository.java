package cn.itcast.zt.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by zhangtian on 2017/4/11.
 */
public interface UserRepository extends MongoRepository<User, Long> {

    User findByUsername(String username) ;

}
