package com.manage.gpu.controller;


import com.manage.gpu.entity.GpuRequest;
import com.manage.gpu.entity.Result;
import com.manage.gpu.service.GpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("gpu")
public class GpuController {
    @Autowired
    private GpuService gpuService;
    @PostMapping("/insert")
    public Result update(@RequestBody GpuRequest gpuRequest){
        return gpuService.insert(gpuRequest);
    }

    @PostMapping("/update")
    public Result updategpu(@RequestBody GpuRequest gpuRequest){
        return gpuService.update(gpuRequest);
    }
    @PostMapping("/getallgpu")
    public Result getallgpu(){
        return gpuService.findallGpu();
    }

}
