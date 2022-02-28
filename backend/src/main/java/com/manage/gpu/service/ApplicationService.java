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
                a = new Application();
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
     * 管理员分配时 applicationrequest传要分配的gpu id，老师审核时 没有 就不填
     */
    public Result update(ApplicationRequest applicationRequest){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            System.out.println(applicationRequest.getGpu_id());
            Application a = applicationMapper.findApplicationById(applicationRequest.getId());
            if(applicationRequest.getGpu_id()!=null) a.setGpu_id(applicationRequest.getGpu_id());
            //修改状态
            a.setStatus(applicationRequest.getStatus());
            int res = applicationMapper.updateApplication(a);
            if(res!=0){
                result.setMsg("修改成功");
                result.setSuccess(true);
                MailVo mv = new MailVo();
                //老师审批完 状态为1，前端修改
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
                    Long teacher_id = studentMapper.findstuTeacher(a.getName());
                    //取出学生对应的老师名，作为resource_user
                    g.setResource_user(teacherMapper.findTeacherById(teacher_id).getTeacher_name());
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
    /**
     * 查看待审核(老师)
     * @return
     */
    public Result findapplicationtocheck(){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            List<Application> l = applicationMapper.findApplication1();
            result.setDetail(l);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
            result.setSuccess(true);
        }
        return result;
    }
    /**
     * 查看待分配（管理员）
     * @return
     */
    public Result findapplicationtogive(){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            List<Application> l = applicationMapper.findApplication2();
            result.setDetail(l);

        }catch (Exception e){
            result.setMsg(e.getMessage());
            e.printStackTrace();
            result.setSuccess(true);
        }
        return result;
    }




    /**
     * 查看所有申请
     * @return
     */

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
