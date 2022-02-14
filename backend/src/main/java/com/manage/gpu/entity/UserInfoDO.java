package com.manage.gpu.entity;


import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author 小明
 * @date 2021/10/19
 * @description
 */

public class UserInfoDO {
    private int id;
    private String userId;
    private String userName;
    private String passWord;
    private String email;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


