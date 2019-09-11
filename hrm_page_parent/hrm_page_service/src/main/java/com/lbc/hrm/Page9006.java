package com.lbc.hrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @program: hrm_parents
 * @Date: 2019/9/8 15:40
 * @Author: Mr.Deng
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.lbc.hrm.mapper")
public class Page9006 {
    public static void main(String[] args) {
        SpringApplication.run(Page9006.class,args);
    }
}
