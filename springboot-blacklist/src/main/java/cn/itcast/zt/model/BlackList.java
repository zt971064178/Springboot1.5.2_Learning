package cn.itcast.zt.model;

import java.util.Date;

/**
 * Created by zhangtian on 2017/4/14.
 */
public class BlackList {
    private int id;
    private String ip ;
    private Date iptime ;

    // 构造器
    public BlackList() {

    }

    public BlackList(String ip, Date iptime) {
        this.ip = ip;
        this.iptime = iptime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getIptime() {
        return iptime;
    }

    public void setIptime(Date iptime) {
        this.iptime = iptime;
    }
}
