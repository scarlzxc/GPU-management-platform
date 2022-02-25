package com.manage.gpu.mapper;


import com.manage.gpu.entity.Gpu;
import com.manage.gpu.entity.Student;
import com.manage.gpu.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GpuMapper {

    @Select("select * from gpu")
    List<Gpu> findAllGpu();
    @Select("select * from gpu where user = null")
    List<Gpu> findavailableGpu();
    @Select("select * from gpu g where user != null")
    List<Gpu> findbusyGpu();

    /**
     * 添加
     * @param gpu
     * @return
     */
    @Insert("INSERT INTO gpu (id, resource_owner, resource_user, host_name,ip,user,gpu_resource,username, password,ssd,disk,memory,cpu,os,remarks)\n" +
            "    VALUES(#{id},#{resource_owner}, #{resource_user}, #{host_name},#{ip},#{user},#{gpu_resource},#{username}, #{password},#{ssd},#{disk},#{memory},#{cpu},#{os},#{remarks});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer insertGpu(Gpu gpu);

    /**
     * 修改
     * @param gpu
     * @return
     */
    @Update("UPDATE gpu SET resource_owner = #{resource_owner},resource_user = #{resource_user},host_name = #{host_name},ip = #{ip},user=#{user},gpu_resource=#{gpu_resource},username=#{username}, password=#{password},ssd=#{ssd},disk=#{disk},memory=#{memory},cpu=#{cpu},os=#{os},remarks=#{remarks} WHERE id=#{id}")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer updateGpu(Gpu gpu);

    @Select("SELECT * FROM gpu WHERE id=#{id}")
    Gpu findGPUById(@Param("id")  Long id);

    @Delete(" DELETE FROM gpu WHERE id=#{id}")
    Integer deletetgpu(Long id);

}
