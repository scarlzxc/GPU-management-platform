package com.manage.gpu.utils;


import com.manage.gpu.entity.UserInfoDO;
import org.springframework.stereotype.Component;


@Component
public class LocalUser {
    public static ThreadLocal<UserInfoDO> USER = new ThreadLocal<>();
}


