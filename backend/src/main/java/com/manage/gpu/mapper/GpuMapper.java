package com.manage.gpu.mapper;


import com.manage.gpu.entity.Gpu;
import com.manage.gpu.entity.Teacher;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GpuMapper {
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
}
