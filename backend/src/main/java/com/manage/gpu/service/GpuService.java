package com.manage.gpu.service;

import com.manage.gpu.mapper.GpuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GpuService {
    @Autowired
    GpuMapper gpuMapper;

}
