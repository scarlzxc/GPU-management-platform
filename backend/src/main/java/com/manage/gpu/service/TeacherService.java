package com.manage.gpu.service;
import com.manage.gpu.entity.*;
import com.manage.gpu.mapper.TeacherMapper;
import com.manage.gpu.utils.JWTUtil;
import com.manage.gpu.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    /**
     * 登录
     */
    public Result login(Teacher teacher){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Long ID = teacherMapper.login(teacher);
            if(ID==null){
                result.setMsg("用户名或密码错误");
            }else {
                result.setMsg("登录成功");
                result.setSuccess(true);
                Teacher teacherById = teacherMapper.findTeacherById(ID);
                result.setDetail(teacherById);
                //生成jwt
                HashMap<String,String> paylod = new HashMap<>();
                paylod.put("type","teacher");
                paylod.put("name",teacher.getTeacher_name());
                String token = JWTUtil.getToken(paylod);
                result.setToken(token);
                //放入redis
                RedisUtils redisUtils = new RedisUtils();
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
            RedisUtils redisUtils = new RedisUtils();
            redisUtils.hdel("jwt",token);
            result.setMsg("退出成功");
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 录入老师
     */
    public Result insert(InsertTeacherRequest insertTeacherRequest){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Teacher t = teacherMapper.findTeacherByName(insertTeacherRequest.getTeacher_name());
            if(t!=null){
                result.setMsg("用户已存在");
            }else {


                t.setTeacher_name(insertTeacherRequest.getTeacher_name());
                t.setPassword(insertTeacherRequest.getPassword());
                t.setEmail(insertTeacherRequest.getEmail());

                t.setAccount(insertTeacherRequest.getAccounnt());
                int res = teacherMapper.insertTeacher(t);
                if(res!=0){
                    result.setMsg("录入成功");
                    result.setSuccess(true);
                }
                result.setDetail(t);
            }

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 修改老师信息
     */
    public Result update(UpdateTeacherRequest updateTeacherRequest){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            //修改自己信息的时候，姓名没法更改，前端传过来
            Teacher t = teacherMapper.findTeacherByName(updateTeacherRequest.getTeacher_name());
            t.setPassword(updateTeacherRequest.getPassword());
            t.setAccount(updateTeacherRequest.getAccounnt());
            t.setEmail(updateTeacherRequest.getEmail());
            int res = teacherMapper.updateTeacher(t);
            if(res!=0){
                result.setMsg("修改成功");
                result.setSuccess(true);
            }
            result.setDetail(t);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 查看所有老师
     * @return
     */
    public Result findallTeacher(){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            List<Teacher> l = teacherMapper.findAllTeacher();
            result.setDetail(l);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 删除老师
     * @return
     */
    public Result deleteteacher(Long id){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            if(teacherMapper.deleteteacher(id)!=null){
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
