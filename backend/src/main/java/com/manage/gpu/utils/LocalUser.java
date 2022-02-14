package com.manage.gpu.utils;


import com.manage.gpu.entity.UserInfoDO;
import org.springframework.stereotype.Component;

/**
 * @author 小明
 * @date 2021/10/27
 * @description
 */
@Component
public class LocalUser {
    public static ThreadLocal<UserInfoDO> USER = new ThreadLocal<>();
}


