package com.manage.gpu.mapper;

import com.manage.gpu.entity.Student;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StudentMapper {





    @Select("select * from student")
    List<Student> findAllStudent();

    /**
     *查看用户名是否已经存在
     * @param student_name
     * @return
     */
    @Select("select s.student_name,s.password from student s where s.student_name=#{student_name}")
    Student findStudentByName(@Param("student_name") String student_name);


    @Select("SELECT * FROM student WHERE id=#{id}")
    Student findStudentById(@Param("id")  Long id);

    //根据学生姓名找老师id
    @Select("SELECT s.teacher_id FROM student s WHERE s.student_name=#{student_name}")
    Long findstuTeacher(@Param("student_name")  String student_name);


    /**
     * 登录
     * @param student
     * @return
     */
    @Select("select s.id from student s where s.account=#{accounnt} and password=#{password}")
    Long login(Student student);

    /**
     * 添加
     * @param student
     * @return
     */
    @Insert("INSERT INTO student (id, student_name, password, email, teacher_id, account)\n" +
            "    VALUES(#{id},#{student_name},#{password},#{email},#{teacher_id},#{account});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer insertStudent(Student student);

    /**
     * 修改
     * @param student
     * @return
     */
    @Update("UPDATE Student SET student_name=#{student_name},password=#{password},email=#{email},teacher_id=#{teacher_id},account=#{account} WHERE id=#{id}")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer updateStudent(Student student);


}
