package cn.itcast.zt.service;

/**
 * springboot通过jdbcTemplate访问数据库
 * Created by zhangtian on 2017/4/10.
 */
public interface UserService {

    /**
     * 新增用户
     * @param name
     * @param age
     */
    public void create(String name, Integer age) ;

    /**
     * 根据名称删除用户
     * @param name
     */
    public void deleteByName(String name) ;

    /**
     * 统计用户数量
     * @return
     */
    public Integer countAllUsers() ;

    /**
     * 删除所有用户
     */
    public void  deleteAllUsers() ;
}
