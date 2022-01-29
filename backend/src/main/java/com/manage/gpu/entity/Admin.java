package com.manage.gpu.entity;

public class Admin {
    private Long id;
    private String accounnt;
    private String password;
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccounnt() {
        return accounnt;
    }

    public void setAccounnt(String accounnt) {
        this.accounnt = accounnt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
