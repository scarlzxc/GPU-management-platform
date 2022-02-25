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
import org.springframework.scheduling.annotation.Scheduled;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        mv.setId("真帅");
        mv.setTo("10175102121@stu.ecnu.edu.cn");
        mv.setText("xzx真帅啊");
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

    @Test
    public void test5() throws ParseException {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
        Long a = 19220225L;
        System.out.println(bartDateFormat.parse(a+"").toString()+"test");
    }
    @Test
    public void test6(){
        List<String> l = new ArrayList<>();
        l.add("s");
        l.add("b");
        for(String s : l){
            System.out.println(s);
        }
    }





}
