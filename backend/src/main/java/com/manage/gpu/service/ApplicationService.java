package com.manage.gpu.service;

import com.manage.gpu.entity.*;
import com.manage.gpu.mapper.ApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    /**
     * 录入申请单
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
     * 修改申请单信息
     */
    public Result update(ApplicationRequest applicationRequest){
        Result result=new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            Application a = applicationMapper.findApplicationById(applicationRequest.getId());
            //修改状态
            a.setStatus(applicationRequest.getStatus());
            //延长使用时间
            a.setEnd_time(applicationRequest.getEnd_time());
            int res = applicationMapper.updateApplication(a);
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

}
