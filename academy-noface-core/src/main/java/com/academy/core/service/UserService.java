package com.academy.core.service;

import com.academy.core.pojo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "academy-noface-user-service")
public interface UserService {

    @GetMapping("/feign/user/openId")
    User findByOpenId(@RequestParam("openId") String openId);

    @PostMapping("/feign/user")
    Long insertUser(@RequestBody User user);

    @GetMapping("/feign/user/id")
    User findById(@RequestParam("id") Long id);
}
