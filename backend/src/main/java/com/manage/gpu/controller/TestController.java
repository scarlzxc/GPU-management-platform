package com.manage.gpu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zxc
 * @Packagename:com.manage.gpu.controller
 * @date 2021/12/30
 * @Description
 */
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping("test2")
    public String test1(){
        return "Hello spring-boot";
    }
}
