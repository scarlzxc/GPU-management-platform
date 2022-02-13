package com.manage.gpu.controller;

import com.manage.gpu.entity.*;
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
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @PostMapping("/login")
    public Result login(@RequestBody Teacher teacher) {
        return teacherService.login(teacher);
    }
    @PostMapping("/update")
    public Result update(@RequestBody UpdateTeacherRequest updateTeacherRequest){
        return teacherService.update(updateTeacherRequest);
    }

}
