package com.academy.article;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.academy.article.dao")
@EnableEurekaClient
@EnableFeignClients
public class AcademyArticleService {
    public static void main(String[] args) {
        SpringApplication.run(AcademyArticleService.class,args);
    }
}
