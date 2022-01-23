package com.manage.gpu;

import com.manage.gpu.entity.Student;
import com.manage.gpu.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

}
