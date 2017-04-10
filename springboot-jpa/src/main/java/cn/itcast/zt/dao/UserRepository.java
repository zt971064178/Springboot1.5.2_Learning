package cn.itcast.zt.dao;

import cn.itcast.zt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by zhangtian on 2017/4/10.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name) ;

    User findByNameAndAge(String name, Integer age) ;

//    @Query(value = "select * from USER u where u.name = ?1", nativeQuery = true)
    @Query(value = "select u from User u where u.name = :name")
    User findUser(@Param("name") String name) ;
}
