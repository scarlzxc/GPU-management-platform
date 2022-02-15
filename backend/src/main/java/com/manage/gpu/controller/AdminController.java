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

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        return adminService.login(admin);
    }
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request){
        String token = request.getHeader("token");
        return adminService.logout(token);
    }


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


    @PostMapping("/insertstudent")
    public Result insertstudent(@RequestBody InsertStudentRequest insertStudentRequest){
        return studentService.insert(insertStudentRequest);}
    @PostMapping("/getallstudent")
    public Result getallstudent(){
        return studentService.findallStudent();
    }
    @PostMapping("/updatestudent")
    public Result update(@RequestBody UpdateStudentRequest updateStudentRequest){
        return studentService.update(updateStudentRequest);
    }

    @PostMapping("/updateteacher")
    public Result update(@RequestBody UpdateTeacherRequest updateTeacherRequest){
        return teacherService.update(updateTeacherRequest);
    }
    @PostMapping("/insertteacher")
    public Result insertteacher(@RequestBody InsertTeacherRequest insertTeacherRequest){
        return teacherService.insert(insertTeacherRequest);
    }
    @PostMapping("/getallteacher")
    public Result getallteacher(){
        return teacherService.findallTeacher();
    }



}
