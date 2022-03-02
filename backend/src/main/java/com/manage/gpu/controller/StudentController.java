package com.manage.gpu.controller;

import com.manage.gpu.entity.*;
import com.manage.gpu.service.ApplicationService;
import com.manage.gpu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zxc
 * @Packagename:com.manage.gpu.controller
 * @date 2022/2/10
 * @Description
 */

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/login")
    public Result login(@RequestBody Student student) {
        return studentService.login(student);
    }
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request){
        String token = request.getHeader("token");
        return studentService.logout(token);
    }
    //学生和管理员更新学生信息
    @PostMapping("/update")
    public Result update(@RequestBody UpdateStudentRequest updateStudentRequest){
        return studentService.update(updateStudentRequest);
    }
    //学生申请gpu
    @PostMapping("/applyapplication")
    public Result applyapplication(@RequestBody ApplicationRequest applicationRequest){
        return applicationService.insert(applicationRequest);
    }
    //学生申请gpu
    @GetMapping("/test")
    public String test(){
        return "test";
    }

}
