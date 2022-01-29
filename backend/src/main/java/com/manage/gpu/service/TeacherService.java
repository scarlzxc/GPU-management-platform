package com.manage.gpu.service;
import com.manage.gpu.entity.*;
import com.manage.gpu.mapper.TeacherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                teacher.setId(ID);
                Teacher teacherById = teacherMapper.findTeacherById(ID);
                result.setDetail(teacherById);
            }

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
                t.setPhone(insertTeacherRequest.getPhone());
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
            t.setPhone(updateTeacherRequest.getPhone());
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
}
