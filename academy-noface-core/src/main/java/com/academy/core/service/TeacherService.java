package com.academy.core.service;

import com.academy.core.pojo.Teacher;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "academy-noface-videoo-service")
public interface TeacherService {

    @GetMapping("/feign/teacher")
    Teacher findByNick(@RequestParam(value = "nick") String nick);

    @GetMapping("/feign/teacher/{id}")
    Teacher findById(@PathVariable("id") Long id);

}
