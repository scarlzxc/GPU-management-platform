package com.manage.gpu.service;

import com.manage.gpu.entity.InsertStudentRequest;
import com.manage.gpu.entity.Result;
import com.manage.gpu.entity.Student;
import com.manage.gpu.entity.UpdateStudentRequest;
import com.manage.gpu.mapper.StudentMapper;
import com.manage.gpu.mapper.TeacherMapper;
import com.manage.gpu.utils.JWTUtil;
import com.manage.gpu.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zxc
 * @Packagename:com.manage.gpu.service
 * @date 2022/1/22
 * @Description
 */
@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    /**
     * 登录
     */
    public Result login(Student student){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Long ID = studentMapper.login(student);
            if(ID==null){
                result.setMsg("用户名或密码错误");
            }else {
                result.setMsg("登录成功");
                result.setSuccess(true);
                Student studentByid = studentMapper.findStudentById(ID);
                result.setDetail(studentByid);
                //生成jwt
                HashMap<String,String> paylod = new HashMap<>();
                paylod.put("type","student");
                paylod.put("name",student.getStudent_name());
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
     * 录入学生
     */
    public Result insert(InsertStudentRequest insertStudentRequest){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
           Student s = studentMapper.findStudentByName(insertStudentRequest.getStudent_name());
            if(s!=null){
                result.setMsg("用户已存在");
            }else {

                s.setStudent_name(insertStudentRequest.getStudent_name());
                s.setPassword(insertStudentRequest.getPassword());
                s.setEmail(insertStudentRequest.getEmail());
                s.setTeacher_id(teacherMapper.findTeacherId(insertStudentRequest.getTeacher_name()));
                s.setAccount(insertStudentRequest.getAccount());
                int res = studentMapper.insertStudent(s);
                if(res!=0){
                    result.setMsg("录入成功");
                    result.setSuccess(true);
                }
                result.setDetail(s);
            }

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 修改学生信息
     */
    public Result update(UpdateStudentRequest updateStudentRequest){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            //学生修改自己信息的时候，姓名没法更改，前端传过来
            Student s = studentMapper.findStudentByName(updateStudentRequest.getStudent_name());
            s.setPassword(updateStudentRequest.getPassword());
            s.setAccount(updateStudentRequest.getAccount());
            s.setEmail(updateStudentRequest.getEmail());
            int res = studentMapper.updateStudent(s);
            if(res!=0){
                result.setMsg("修改成功");
                result.setSuccess(true);
            }
            result.setDetail(s);

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
    public Result findallStudent(){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            List<Student> l = studentMapper.findAllStudent();
            result.setDetail(l);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}
