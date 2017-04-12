package cn.itcast.zt.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by zhangtian on 2017/4/12.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name) ;

    User findByNameAndAge(String name, Integer age) ;

    @Query(value = "select u from User u where u.name = :name")
    User findUser(@Param("name") String name) ;
}
