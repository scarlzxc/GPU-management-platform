package com.manage.gpu.controller;

import com.manage.gpu.entity.Result;
import com.manage.gpu.entity.Student;
import com.manage.gpu.entity.UpdateStudentRequest;
import com.manage.gpu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/update")
    public Result update(@RequestBody UpdateStudentRequest updateStudentRequest){
        return studentService.update(updateStudentRequest);
    }
}
