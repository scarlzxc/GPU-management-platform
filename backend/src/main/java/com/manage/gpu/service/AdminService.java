package com.manage.gpu.service;

import com.manage.gpu.entity.*;
import com.manage.gpu.mapper.AdminMapper;
import com.manage.gpu.utils.JWTUtil;
import com.manage.gpu.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RedisUtils redisUtils;
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
                //生成jwt
                HashMap<String,String> paylod = new HashMap<>();
                paylod.put("type","admin");
                paylod.put("name",admin.getAdmin_name());
                String token = JWTUtil.getToken(paylod);
                result.setToken(token);
                //放入redis

                redisUtils.hset("jwt",token,true);
            }

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 退出
     * @param token
     * @return
     */
    public Result logout(String token){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            redisUtils.hdel("jwt",token);
            result.setMsg("退出成功");
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
                a = new Admin();
                a.setPassword(insertAdminRequest.getPassword());
                a.setEmail(insertAdminRequest.getEmail());
                a.setAccount(insertAdminRequest.getAccount());
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
            Admin a = adminMapper.findAdminById(updateAdminRequest.getId());
            a.setPassword(updateAdminRequest.getPassword());
            a.setAccount(updateAdminRequest.getAccount());
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
     * 查看所有管理员
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
    /**
     * 删除admin
     * @return
     */
    public Result deletegpu(Long id){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            if(adminMapper.deletetadmin(id)!=null){
                result.setDetail("删除成功");
                result.setSuccess(true);
            }


        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


}
