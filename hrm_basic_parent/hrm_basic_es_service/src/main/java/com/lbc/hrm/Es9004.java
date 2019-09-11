package com.lbc.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Es9004 {
    public static void main(String[] args) {
        SpringApplication.run(Es9004.class, args);
    }
}
