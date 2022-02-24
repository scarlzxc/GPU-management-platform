package com.manage.gpu;

import com.manage.gpu.entity.MailVo;
import com.manage.gpu.entity.Result;
import com.manage.gpu.entity.Student;
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
    @Test
    public void test1() {
        Student s1 = studentMapper.findStudentById(1L);
        s1.setPassword("123");
        int res = studentMapper.updateStudent(s1);
        System.out.println(res);
    }
    public static void quicksort(int[] arr,int left,int right){
        if(left<right){
            int pivot = arr[left];
            int i = left;
            int j = right;

            while(i < j){
                while(i<j && arr[j] > pivot)
                    j--;
                arr[i] = arr[j];
                while(i < j && arr[i]<= pivot)
                    left++;
                arr[j] = arr[i];
            }
            arr[i] = pivot;
            quicksort(arr,i+1,right);
            quicksort(arr,left,i-1);
        }else return;
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


}
