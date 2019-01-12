package com.academy.core.service;

import com.academy.core.pojo.Teacher;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "academy-noface-videoo-service")
public interface TeacherService {

    @GetMapping("/feign/teacher")
    Teacher findByNick(@RequestParam(value = "nick") String nick);

    @GetMapping("/feign/teacher/{id}")
    Teacher findById(@PathVariable("id") Long id);

    @GetMapping("/feign/teacher/all")
    List<Teacher> findAll();

    @PostMapping("/feign/teacher")
    Long insert(@RequestBody Teacher teacher);

    @DeleteMapping("/feign/teacher/{id}")
    void delete(@PathVariable("id") Long id);

}
