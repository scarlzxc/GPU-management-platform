package com.manage.gpu.controller;

import com.manage.gpu.entity.*;
import com.manage.gpu.service.ApplicationService;
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
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ApplicationService applicationService;
    @PostMapping("/login")
    public Result login(@RequestBody Teacher teacher) {
        return teacherService.login(teacher);
    }
    @PostMapping("/logout")
    public Result logout(HttpServletRequest request){
        String token = request.getHeader("token");
        return teacherService.logout(token);
    }
    //老师和管理员修改老师信息
    @PostMapping("/update")
    public Result update(@RequestBody UpdateTeacherRequest updateTeacherRequest){
        return teacherService.update(updateTeacherRequest);
    }
    //查看待审核
    @PostMapping("/listtocheck")
    public Result listtocheck(){
        return applicationService.findapplicationtocheck();
    }
   //审核申请单
    @PostMapping("/check")
    public Result checkApplication(@RequestBody ApplicationRequest applicationRequest){
        return applicationService.update(applicationRequest);
    }


}
