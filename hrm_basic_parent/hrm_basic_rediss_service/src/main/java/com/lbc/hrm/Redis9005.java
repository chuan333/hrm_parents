package com.lbc.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: hrm_parents
 * @Date: 2019/9/7 23:38
 * @Author: Mr.Chuan
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class Redis9005 {
    public static void main(String[] args) {
        SpringApplication.run(Redis9005.class,args);
    }
}
