package com.manage.gpu;

import com.manage.gpu.entity.*;
import com.manage.gpu.mapper.*;
import com.manage.gpu.service.GpuService;
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
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    GpuMapper gpuMapper;
    @Test
    public void test1() {
       Student s = studentMapper.findStudentById(4L);
       s.setTeacher_id(3L);
       studentMapper.updateStudent(s);
    }

    @Test
    public void test2(){

        redisUtils.hset("123","111","234");
        System.out.println(redisUtils.hget("123","111"));
        boolean b = (boolean) redisUtils.hget("jwt","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0eXBlIjoic3R1ZGVudCIsImV4cCI6MTY0NjYyNzE1MH0.87J9qvJvML4Ak2P6FLAdzt4pbnVnIpF6Fm4Vvaq4Jdc");
        if(b){
            System.out.println("11111");
        }
    }
    @Test
    public  void test3(){
      Teacher t = new Teacher();
      t.setAccount("111");
      t.setEmail("123@qq.com");
      t.setPassword("111");
      teacherMapper.insertTeacher(t);
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
