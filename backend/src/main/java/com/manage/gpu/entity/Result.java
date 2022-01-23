package com.manage.gpu.entity;

/**
 * @author zxc
 * @Packagename:com.manage.gpu.entity
 * @date 2022/1/16
 * @Description
 */

public class Result<T>{
    public Result(String msg, boolean success, T detail) {
        this.msg = msg;
        this.success = success;
        this.detail = detail;
    }

    public Result() {

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDetail() {
        return detail;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private String msg;
    private T detail;
    private boolean success;


}
