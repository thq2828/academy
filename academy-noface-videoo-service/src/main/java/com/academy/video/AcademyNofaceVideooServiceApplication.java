package com.academy.video;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AcademyNofaceVideooServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademyNofaceVideooServiceApplication.class, args);
    }

}

