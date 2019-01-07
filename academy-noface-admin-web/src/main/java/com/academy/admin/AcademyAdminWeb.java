package com.academy.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class AcademyAdminWeb {
    public static void main(String[] args) {
        SpringApplication.run(AcademyAdminWeb.class,args);
    }
}
