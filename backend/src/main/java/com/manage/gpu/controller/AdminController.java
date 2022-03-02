package com.manage.gpu.controller;

import com.manage.gpu.entity.*;
import com.manage.gpu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private ApplicationService applicationService;


    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        return adminService.login(admin);
    }
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request){
        String token = request.getHeader("token");
        return adminService.logout(token);
    }

    //管理员
    @PostMapping("/insertadmin")
    public Result insertadmin(@RequestBody InsertAdminRequest insertAdminRequest){
        return adminService.insert(insertAdminRequest);
    }
    @PostMapping("/updateadmin")
    public Result updateadmin(@RequestBody UpdateAdminRequest updateAdminRequest){
        return adminService.update(updateAdminRequest);
    }
    @PostMapping("/getalladmin")
    public Result getalladmin(){return adminService.findallAdmin();}

    //学生
    @PostMapping("/insertstudent")
    public Result insertstudent(@RequestBody InsertStudentRequest insertStudentRequest){
        return studentService.insert(insertStudentRequest);}
    @PostMapping("/getallstudent")
    public Result getallstudent(){
        return studentService.findallStudent();
    }
    @PostMapping("/deletestudent")
    public Result deletestudent(@RequestParam("id") Long id){
        return studentService.deleteStudent(id);
    }

    //老师
    @PostMapping("/insertteacher")
    public Result insertteacher(@RequestBody InsertTeacherRequest insertTeacherRequest){
        return teacherService.insert(insertTeacherRequest);
    }
    @PostMapping("/getallteacher")
    public Result getallteacher(){
        return teacherService.findallTeacher();
    }
    @PostMapping("/deleteteacher")
    public Result deletteacher(@RequestParam("id") Long id){
        return teacherService.deleteteacher(id);
    }



    //gpu
    @PostMapping("/insertgpu")
    public Result update(@RequestBody GpuRequest gpuRequest){
        return gpuService.insert(gpuRequest);
    }

    @PostMapping("/updategpu")
    public Result updategpu(@RequestBody GpuRequest gpuRequest){
        return gpuService.update(gpuRequest);
    }
    @PostMapping("/getallgpu")
    public Result getallgpu(){
        return gpuService.findallGpu();}
    @PostMapping("/deletegpu")
    public Result deletegpu(@RequestParam("id") Long id){
        return gpuService.deletegpu(id);
    }


    //查看所有表单
    @PostMapping("/getallapplication")
    public Result getallapplication(){
        return applicationService.findallApplication();
    }
    //查看待分配列表
    @PostMapping("/listtogive")
    public Result listtogive(){
        return applicationService.findapplicationtogive();
    }
    //查看空闲gpu
    @PostMapping("/availablegpu")
    public Result listavailable(){
        return gpuService.findavailablegpu();
    }
    //分配资源
    @PostMapping("/givegpu")
    public Result givegpu(@RequestBody ApplicationRequest applicationRequest){
        return applicationService.update(applicationRequest);
    }



}
