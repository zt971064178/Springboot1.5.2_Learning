package cn.itcast.zt.dao;

import cn.itcast.zt.model.BlackList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangtian on 2017/4/14.
 */
@Mapper
public interface BlackListDao {

    // 根据IP来查找记录
    List<BlackList> findByIP(@Param("ip") String ip) ;

    // 添加记录
    int addBlackList(@Param("blackList") BlackList blackList) ;
}
