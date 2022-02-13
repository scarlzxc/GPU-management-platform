package com.manage.gpu.mapper;

import com.manage.gpu.entity.Admin;
import com.manage.gpu.entity.Application;
import com.manage.gpu.entity.Student;
import com.manage.gpu.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ApplicationMapper {

    @Select("SELECT * FROM application WHERE id=#{id}")
    Application findApplicationById(@Param("id")  Long id);
    /**
     * 添加
     * @param application
     * @return
     */
    @Insert("INSERT INTO application (id, name, reason, status, end_time, start_time)\n" +
            "    VALUES(#{id},#{name},#{reason},#{status},#{end_time},#{start_time});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer insertApplication(Application application);

    /**
     * 修改
     * @param application
     * @return
     */
    @Update("UPDATE application SET name=#{name},reason=#{reason},status=#{status},end_time=#{end_time},start_time=#{start_time}WHERE id=#{id}")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer updateApplication(Application application);
    /**
     *查看用户是否已经存在申请单
     * @param name
     * @return
     */
    @Select("select * from application a where a.name=#{name}")
    Application findApplicationByName(@Param("name") String name);
}
