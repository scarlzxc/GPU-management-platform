package com.manage.gpu.service;

import com.manage.gpu.entity.*;
import com.manage.gpu.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    /**
     * 登录
     */
    public Result login(Admin admin){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Long ID = adminMapper.login(admin);
            if(ID==null){
                result.setMsg("用户名或密码错误");
            }else {
                result.setMsg("登录成功");
                result.setSuccess(true);
                admin.setId(ID);
                Admin adminbyid = adminMapper.findAdminById(ID);
                result.setDetail(adminbyid);
            }

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 录入管理员
     */
    public Result insert(InsertAdminRequest insertAdminRequest){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Admin a = adminMapper.findAdminByName(insertAdminRequest.getAdmin_name());
            if(a!=null){
                result.setMsg("管理员已存在");
            }else {
                a.setPassword(insertAdminRequest.getPassword());
                a.setEmail(insertAdminRequest.getEmail());
                a.setAccounnt(insertAdminRequest.getAccounnt());
                int res = adminMapper.insertAdmin(a);
                if (res != 0) {
                    result.setMsg("录入成功");
                    result.setSuccess(true);
                }
                result.setDetail(a);
            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 修改管理员信息
     */
    public Result update(UpdateAdminRequest updateAdminRequest){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Admin a = adminMapper.findAdminByName(updateAdminRequest.getAdmin_name());
            a.setPassword(updateAdminRequest.getPassword());
            a.setAccounnt(updateAdminRequest.getAccounnt());
            a.setEmail(updateAdminRequest.getEmail());
            int res = adminMapper.updateAdmin(a);
            if(res!=0){
                result.setMsg("修改成功");
                result.setSuccess(true);
            }
            result.setDetail(a);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 查看所有学生
     * @return
     */
    public Result findallAdmin(){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            List<Admin> l = adminMapper.findAllAdmin();
            result.setDetail(l);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}
