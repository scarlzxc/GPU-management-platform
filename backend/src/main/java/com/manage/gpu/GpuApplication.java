package com.manage.gpu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GpuApplication {

    public static void main(String[] args) {
        SpringApplication.run(GpuApplication.class, args);
    }

}
