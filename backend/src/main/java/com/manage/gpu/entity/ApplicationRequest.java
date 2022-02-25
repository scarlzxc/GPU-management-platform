package com.manage.gpu.entity;

/**
 * @author zxc
 * @Packagename:com.manage.gpu.entity
 * @date 2022/2/10
 * @Description
 */

public class ApplicationRequest {
    private Long id;
    private String name;
    private String reason;
    private long start_time;
    private long end_time;
    private long status;
    private Long gpu_id;//分配的gpu的id

    public Long getGpu_id() {
        return gpu_id;
    }

    public void setGpu_id(Long gpu_id) {
        this.gpu_id = gpu_id;
    }

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

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(long end_time) {
        this.end_time = end_time;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }
}
