package com.manage.gpu.schedule;

import com.manage.gpu.entity.Application;
import com.manage.gpu.entity.Gpu;
import com.manage.gpu.entity.MailVo;
import com.manage.gpu.entity.Student;
import com.manage.gpu.mapper.ApplicationMapper;
import com.manage.gpu.mapper.GpuMapper;
import com.manage.gpu.mapper.StudentMapper;
import com.manage.gpu.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class RemindStudentTask {
    @Autowired
    private MailService mailService;
    @Autowired
    private GpuMapper gpuMapper;
    @Autowired
    private ApplicationMapper applicationMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Scheduled(cron = "0 0 12 * * ?")
    private void proces() throws ParseException {
        SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat bartDateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Long l = Long.parseLong(bartDateFormat.format(date));
        List<Gpu> list = gpuMapper.findbusyGpu();
        List<Application> apply = new ArrayList<>();
        List<Application> toremind = new ArrayList<>();
        for(Gpu G : list){
            apply.add(applicationMapper.findApplicationBygpuId(G.getId()));
        }
        for(Application a : apply){
            if((a.getEnd_time()>l)&&(a.getEnd_time()-l)<3){
                toremind.add(a);
            }
        }
        for(Application to : toremind){
            MailVo mv = new MailVo();
            Student s = studentMapper.findStudentByName(to.getName());
            mv.setTo(s.getEmail());
            mv.setSubject("gpu服务器即将到期");
            mv.setText("您的gpu服务器"+bartDateFormat2.parse(to.getEnd_time()+"").toString()+"将要到期");
            mailService.sendMimeMail(mv);
        }
    }
}
