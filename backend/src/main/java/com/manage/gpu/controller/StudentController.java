package com.manage.gpu.controller;

import com.manage.gpu.entity.InsertStudentRequest;
import com.manage.gpu.entity.Result;
import com.manage.gpu.entity.Student;
import com.manage.gpu.entity.UpdateStudentRequest;
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

    @PostMapping("/login")
    public Result login(@RequestBody Student student) {
        return studentService.login(student);
    }
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request){
        String token = request.getHeader("token");
        return studentService.logout(token);
    }

    @PostMapping("/update")
    public Result update(@RequestBody UpdateStudentRequest updateStudentRequest){
        return studentService.update(updateStudentRequest);
    }


}
