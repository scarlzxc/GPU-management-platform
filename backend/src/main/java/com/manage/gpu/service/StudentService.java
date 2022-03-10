package com.manage.gpu.service;

import com.manage.gpu.entity.*;
import com.manage.gpu.mapper.StudentMapper;
import com.manage.gpu.mapper.TeacherMapper;
import com.manage.gpu.utils.JWTUtil;
import com.manage.gpu.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    @Autowired
    RedisUtils redisUtils;
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
                s = new Student();
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

            Student s = studentMapper.findStudentById(updateStudentRequest.getId());
            s.setPassword(updateStudentRequest.getPassword());
            s.setAccount(updateStudentRequest.getAccount());
            s.setEmail(updateStudentRequest.getEmail());
            int res = studentMapper.updateStudent(s);
            s = studentMapper.findStudentById(updateStudentRequest.getId());
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

    public Result adminupdate(Adminupdatestudent adminupdatestudent){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Student s = studentMapper.findStudentById(adminupdatestudent.getId());
            s.setPassword(adminupdatestudent.getPassword());
            s.setAccount(adminupdatestudent.getAccount());
            s.setEmail(adminupdatestudent.getEmail());
            s.setStudent_name(adminupdatestudent.getStudent_name());
            s.setTeacher_id(teacherMapper.findTeacherId(adminupdatestudent.getTeacher_name()));
            int res = studentMapper.updateStudent(s);
            s = studentMapper.findStudentById(adminupdatestudent.getId());
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
            List<StudentListInfo> l2 =new ArrayList<>();
            for(Student s: l){
                StudentListInfo s2 = new StudentListInfo();
                s2.setId(s.getId());
                s2.setStudent_name(s.getStudent_name());
                s2.setAccount(s.getAccount());
                s2.setPassword(s.getPassword());
                s2.setEmail(s.getEmail());
                s2.setTeacher_name(teacherMapper.findTeacherById(s.getTeacher_id()).getTeacher_name());
                l2.add(s2);
            }
            result.setDetail(l2);
            result.setSuccess(true);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 删除学生
     * @return
     */
    public Result deleteStudent(Long id){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            if(studentMapper.deleteStudent(id)!=null){
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
