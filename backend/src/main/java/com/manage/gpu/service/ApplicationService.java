package com.manage.gpu.service;

import com.manage.gpu.entity.*;
import com.manage.gpu.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zxc
 * @Packagename:com.manage.gpu.service
 * @date 2022/2/10
 * @Description
 */

@Service
public class ApplicationService {
    @Autowired
    ApplicationMapper applicationMapper;
    @Autowired
    MailService mailService;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    GpuMapper gpuMapper;
    /**
     * 学生申请单
     */
    public Result insert(ApplicationRequest applicationRequest){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Application a = applicationMapper.findApplicationByName(applicationRequest.getName());
            if(a!=null){
                result.setMsg("不要重复申请");
            }else {
                a.setName(applicationRequest.getName());
                a.setReason(applicationRequest.getReason());
                a.setStatus(applicationRequest.getStatus());
                a.setStart_time(applicationRequest.getStart_time());
                a.setEnd_time(applicationRequest.getEnd_time());
                int res = applicationMapper.insertApplication(a);
                if (res != 0) {
                    result.setMsg("录入成功");
                    result.setSuccess(true);

                    result.setDetail(a);
                    MailVo mv = new MailVo();
                    mv.setSubject(a.getName() + "申请gpu");
                    Long teacher_id = studentMapper.findstuTeacher(a.getName());
                    //发给老师审核
                    mv.setTo(teacherMapper.findTeacherById(teacher_id).getEmail());
                    //url未定,后面直接写一个页面获取application列表去审批
                    mv.setText("请点击审核" + "url");
                    mailService.checkMail(mv);
                    mailService.sendMimeMail(mv);
                }

            }
        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 老师，管理员审核申请单信息
     */
    public Result update(ApplicationRequest applicationRequest){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Application a = applicationMapper.findApplicationById(applicationRequest.getId());
            //修改状态
            a.setStatus(applicationRequest.getStatus());
            int res = applicationMapper.updateApplication(a);
            if(res!=0){
                result.setMsg("修改成功");
                result.setSuccess(true);
                MailVo mv = new MailVo();
                //老师审批完 状态为1
                if(a.getStatus()==1){
                    mv.setSubject("请分配gpu");
                    //先发给第一个admin，后面再考虑发多个
                    mv.setTo(adminMapper.findAdminById(1L).getEmail());
                    mv.setText("请点击分配gpu"+"URL");
                }else{
                    //管理员分配完
                    mv.setSubject("申请成功！请查看gpu信息");
                    mv.setTo(studentMapper.findStudentByName(a.getName()).getEmail());
                    Gpu g = gpuMapper.findGPUById(a.getGpu_id());
                    mv.setText(
                            "ip:"+g.getIp()+
                                    "用户名："+g.getUsername()+
                                    "密码："+g.getPassword()+
                                    "到期时间"+a.getEnd_time()
                    );
                    //同时更新gpu信息,通过用户名，用户名空 就是空闲状态的gpu
                    g.setUser(a.getName());
                    gpuMapper.updateGpu(g);
                }
                mailService.checkMail(mv);
                mailService.sendMimeMail(mv);
            }
            result.setDetail(a);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }







    public Result findallApplication(){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            List<Application> l = applicationMapper.findAllApplication();
            result.setDetail(l);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

}
