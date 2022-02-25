package com.manage.gpu.service;

import com.manage.gpu.entity.*;
import com.manage.gpu.mapper.GpuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GpuService {
    @Autowired
    GpuMapper gpuMapper;
    /**
     * 添加gpu
     */
    public Result insert(GpuRequest gpuRequest){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Gpu g = new Gpu();
            g.setCpu(gpuRequest.getCpu());
            g.setDisk(gpuRequest.getDisk());
            g.setGpu_resource(gpuRequest.getGpu_resource());
            g.setPassword(gpuRequest.getPassword());
            g.setHost_name(gpuRequest.getHost_name());
            g.setIp(gpuRequest.getIp());
            g.setMemory(gpuRequest.getMemory());
            g.setOs(gpuRequest.getOs());
            g.setRemarks(gpuRequest.getRemarks());
            g.setResource_owner(gpuRequest.getResource_owner());
            g.setResource_user(gpuRequest.getResource_user());
            g.setSsd(gpuRequest.getSsd());
            g.setUser(gpuRequest.getUser());
            g.setUsername(gpuRequest.getUsername());
                int res = gpuMapper.insertGpu(g);
                if (res != 0) {
                    result.setMsg("录入成功");
                    result.setSuccess(true);
                }
                result.setDetail(g);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改gpu信息
     */
    public Result update(GpuRequest gpuRequest){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Gpu g = new Gpu();
            g.setCpu(gpuRequest.getCpu());
            g.setDisk(gpuRequest.getDisk());
            g.setGpu_resource(gpuRequest.getGpu_resource());
            g.setPassword(gpuRequest.getPassword());
            g.setHost_name(gpuRequest.getHost_name());
            g.setIp(gpuRequest.getIp());
            g.setMemory(gpuRequest.getMemory());
            g.setOs(gpuRequest.getOs());
            g.setRemarks(gpuRequest.getRemarks());
            g.setResource_owner(gpuRequest.getResource_owner());
            g.setResource_user(gpuRequest.getResource_user());
            g.setSsd(gpuRequest.getSsd());
            g.setUser(gpuRequest.getUser());
            g.setUsername(gpuRequest.getUsername());
            int res = gpuMapper.updateGpu(g);
            if(res!=0){
                result.setMsg("修改成功");
                result.setSuccess(true);
            }
            result.setDetail(g);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查看所有gpu
     * @return
     */
    public Result findallGpu(){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            List<Gpu> l = gpuMapper.findAllGpu();
            result.setDetail(l);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }



    /**
     * 查看空闲gpu（管理员分配时查看）
     * @return
     */
    public Result findavailablegpu(){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            List<Gpu> l = gpuMapper.findavailableGpu();
            result.setDetail(l);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
            result.setSuccess(true);
        }
        return result;
    }


    /**
     * 查看gpu使用情况
     * @return
     */
    public Result findapplygpu(){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            List<Gpu> l = gpuMapper.findavailableGpu();
            result.setDetail(l);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
            result.setSuccess(true);
        }
        return result;
    }


}
