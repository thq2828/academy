package com.academy.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.academy.core.service")
public class AcademyHomeWeb {
    public static void main(String[] args) {
        SpringApplication.run(AcademyHomeWeb.class, args);
    }
}