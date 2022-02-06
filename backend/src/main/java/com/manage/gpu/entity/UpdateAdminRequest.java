package com.manage.gpu.entity;

public class UpdateAdminRequest {
    private String accounnt;
    private String password;
    private String email;
    private String admin_name;

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
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
