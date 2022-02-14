package com.manage.gpu;

import com.manage.gpu.entity.Result;
import com.manage.gpu.entity.Student;
import com.manage.gpu.mapper.StudentMapper;
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
        int[] num = new int[]{1,10,4};
        quicksort(num,0,2);
        System.out.println(num[1]);
    }
    @Test
    public  void test3(){

    }


}
