package com.manage.gpu.mapper;


import com.manage.gpu.entity.Student;
import com.manage.gpu.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TeacherMapper {
    /**
     *查看用户名是否已经存在
     * @param teacher_name
     * @return
     */
    @Select("select t.teacher_name,t.password from teacher t where t.teacher_name=#{teacher_name}")
    Teacher findTeacherByName(@Param("teacher_name") String teacher_name);


    @Select("SELECT * FROM teacher WHERE id=#{id}")
    Teacher findTeacherById(@Param("id")  Long id);



    /**
     * 登录
     * @param teacher
     * @return
     */
    @Select("select t.id from teacher t where t.accounnt=#{account} and password=#{password}")
    Long login(Teacher teacher);

    /**
     * 根据name找老师id
     * @param teacher_name
     * @return
     */
    @Select("select t.id from teacher t where t.teacher_name=#{teacher_name}")
    Long findTeacherId(@Param("teacher_name")  String teacher_name);

    /**
     * 添加
     * @param teacher
     * @return
     */
    @Insert("INSERT INTO teacher (id, teacher_name, password, email, account)\n" +
            "    VALUES(#{id},#{teacher_name},#{password},#{email},#{account});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer insertTeacher(Teacher teacher);

    /**
     * 修改
     * @param teacher
     * @return
     */
    @Update("UPDATE Teacher SET teacher_name=#{teacher_name},password=#{password},email=#{email},account=#{account} WHERE id=#{id}")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer updateTeacher(Teacher teacher);
}
