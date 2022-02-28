package com.manage.gpu.mapper;

import com.manage.gpu.entity.Admin;
import com.manage.gpu.entity.Student;
import com.manage.gpu.entity.Teacher;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {


    @Select("select * from admin")
    List<Admin> findAllAdmin();


    /**
     *查看用户名是否已经存在
     * @param admin_name
     * @return
     */
    @Select("select a.admin_name,a.password from admin a where a.admin_name=#{admin_name}")
    Admin findAdminByName(@Param("admin_name") String admin_name);




    @Select("SELECT * FROM admin WHERE id=#{id}")
    Admin findAdminById(@Param("id")  Long id);

    /**
     * 登录
     * @param admin
     * @return
     */
    @Select("select a.id from admin a where a.account=#{account} and password=#{password}")
    Long login(Admin admin);

    /**
     * 添加
     * @param admin
     * @return
     */
    @Insert("INSERT INTO admin (id, password, email, account,admin_name)\n" +
            "    VALUES(#{id},#{password},#{email},#{account},#{admin_name});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer insertAdmin(Admin admin);

    /**
     * 修改
     * @param admin
     * @return
     */
    @Update("UPDATE Admin SET password=#{password},email=#{email},account=#{account},admin_name=#{admin_name} WHERE id=#{id}")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    Integer updateAdmin(Admin admin);

    @Delete(" DELETE FROM admin WHERE id=#{id}")
    Integer deletetadmin(Long id);
}
