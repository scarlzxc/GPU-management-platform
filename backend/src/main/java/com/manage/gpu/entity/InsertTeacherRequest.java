package com.manage.gpu.entity;

public class InsertTeacherRequest {
    private String teacher_name;
    private String password;
    private String email;
    private String accounnt;
    private String phone;

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
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

    public String getAccounnt() {
        return accounnt;
    }

    public void setAccounnt(String accounnt) {
        this.accounnt = accounnt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
