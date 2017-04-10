package cn.itcast.zt.service.impl;

import cn.itcast.zt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by zhangtian on 2017/4/10.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate ;

    @Override
    public void create(String name, Integer age) {
        jdbcTemplate.update("insert into USER(NAME, AGE) values(?,?)", name, age) ;
    }

    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("delete FROM  USER where Name = ?", name) ;
    }

    @Override
    public Integer countAllUsers() {
        return jdbcTemplate.queryForObject("select count(1) from USER", Integer.class);
    }

    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("delete from USER") ;
    }
}
