package com.manage.gpu.service;

import com.manage.gpu.entity.MailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class MailService {

    private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    //检测邮件信息类
    public void checkMail(MailVo mailVo) {
        if (StringUtils.isEmpty(mailVo.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    public void sendMimeMail(MailVo mailVo) {
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);//true表示支持复杂类型
            //mailVo.setFrom(getMailSendFrom());//邮件发信人从配置项读取
            messageHelper.setTo(mailVo.getTo().split(","));//邮件收信人
            messageHelper.setSubject(mailVo.getSubject());//邮件主题
            messageHelper.setText(mailVo.getText());//邮件内容
            mailSender.send(messageHelper.getMimeMessage());//正式发送邮件
            mailVo.setStatus("ok");
        } catch (Exception e) {
            throw new RuntimeException(e);//发送失败
        }
    }


}

