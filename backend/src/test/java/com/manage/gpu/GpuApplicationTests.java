package com.manage.gpu;

import com.manage.gpu.entity.Application;
import com.manage.gpu.entity.MailVo;
import com.manage.gpu.entity.Result;
import com.manage.gpu.entity.Student;
import com.manage.gpu.mapper.ApplicationMapper;
import com.manage.gpu.mapper.StudentMapper;
import com.manage.gpu.service.MailService;
import com.manage.gpu.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class GpuApplicationTests {
@Autowired
    StudentMapper studentMapper;
    @Autowired
    MailService mailService;
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    ApplicationMapper applicationMapper;
    @Test
    public void test1() {
        System.out.println("123");
    }

    @Test
    public void test2(){

        redisUtils.hset("123","111","234");
        System.out.println(redisUtils.hget("123","111"));
    }
    @Test
    public  void test3(){
        MailVo mv = new MailVo();
        mv.setSubject("test");
        mv.setId("111");
        mv.setTo("953582049@qq.com");
        mv.setText("123test");
        mailService.sendMimeMail(mv);
        System.out.println("123");

    }
    @Test
    public  void test4(){
        Application a = new Application();
        a.setName("zxczz");
        a.setReason("测试2");
        a.setStatus(0L);
        applicationMapper.insertApplication(a);
        List<Application> l = applicationMapper.findApplication1();
        System.out.println(l.get(0).getReason());

    }





}
