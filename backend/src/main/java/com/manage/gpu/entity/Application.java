package com.manage.gpu.entity;

/**
 * @author zxc
 * @Packagename:com.manage.gpu.entity
 * @date 2022/2/10
 * @Description
 */

public class Application {
    private Long id;
    private String name;
    private String reason;
    private Long start_time;
    private Long end_time;
    private Long status;//开始0，老师审批完1，管理员审批完2
    private Long gpu_id;//分配的gpu的id

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getGpu_id() {
        return gpu_id;
    }

    public void setGpu_id(Long gpu_id) {
        this.gpu_id = gpu_id;
    }
}