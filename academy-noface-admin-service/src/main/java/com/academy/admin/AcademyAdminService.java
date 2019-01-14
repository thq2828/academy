package com.academy.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;


@SpringBootApplication
@MapperScan("com.academy.admin.dao")
@EnableEurekaClient
@EnableFeignClients
//@EnableHystrix
public class AcademyAdminService {
    public static void main(String[] args) {
        SpringApplication.run(AcademyAdminService.class,args);
    }
}
