package cn.itcast.zt.service;

import cn.itcast.zt.domain.User;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangtian on 2017/4/12.
 */
public interface UserService {
    @Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly=true,value = "transactionManager")
    User login(String name, String password) ;
}
