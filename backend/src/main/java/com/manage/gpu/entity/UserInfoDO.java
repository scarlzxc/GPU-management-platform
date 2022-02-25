package com.manage.gpu.entity;


import java.time.LocalDateTime;
import java.util.Date;

/**
 * jwt解析信息类
 */

public class UserInfoDO {
    private String type;
    private String userName;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}


