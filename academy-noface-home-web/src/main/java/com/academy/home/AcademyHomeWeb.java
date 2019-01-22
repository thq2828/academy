package com.academy.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients(basePackages = "com.academy.core.service")
public class AcademyHomeWeb {
    public static void main(String[] args) {
        SpringApplication.run(AcademyHomeWeb.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("100MB");
        return factory.createMultipartConfig();
    }

}