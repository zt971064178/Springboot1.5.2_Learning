package cn.itcast.zt.dao;

import cn.itcast.zt.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * User Dao 接口
 * Created by zhangtian on 2017/4/14.
 */
@Mapper
@Repository
public interface UserDao {
    void delete(String uuid);

    int update(@Param(value = "ruser") User user);

    User findByUuid(String uuid);

    int save(@Param(value = "ruser") User user) throws Exception;
}
