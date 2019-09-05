package com.lbc.hrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.lbc.hrm.mapper")
public class Management9001 {
    public static void main(String[] args) {
        SpringApplication.run(Management9001.class,args);
    }
}
