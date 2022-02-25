package com.manage.gpu.mapper;

import com.manage.gpu.entity.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ApplicationMapper {

    @Select("select * from application")
    List<Application> findAllApplication();

    @Select("SELECT * FROM application WHERE id=#{id}")
    Application findApplicationById(@Param("id")  Long id);
    @Select("SELECT * FROM application WHERE gpu_id=#{gpu_id}")
    Application findApplicationBygpuId(@Param("gpu_id")  Long gpu_id);


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

    @Select("select * from application a where a.status = 0")
    List<Application> findApplication1();
    @Select("select * from application a where a.status = 1")
    List<Application> findApplication2();
}
