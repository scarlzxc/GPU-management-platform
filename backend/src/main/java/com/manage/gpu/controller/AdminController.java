package com.manage.gpu.controller;

import com.manage.gpu.entity.*;
import com.manage.gpu.service.AdminService;
import com.manage.gpu.service.GpuService;
import com.manage.gpu.service.StudentService;
import com.manage.gpu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxc
 * @Packagename:com.manage.gpu.controller
 * @date 2022/2/14
 * @Description
 */

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private GpuService gpuService;

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        return adminService.login(admin);
    }


    @PostMapping("/insert")
    public Result insertadmin(@RequestBody InsertAdminRequest insertAdminRequest){
        return adminService.insert(insertAdminRequest);
    }


    @PostMapping("/update")
    public Result updateadmin(@RequestBody UpdateAdminRequest updateAdminRequest){
        return adminService.update(updateAdminRequest);
    }





}
