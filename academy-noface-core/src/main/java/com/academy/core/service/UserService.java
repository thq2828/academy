package com.academy.core.service;

import com.academy.core.pojo.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "academy-noface-user-service")
public interface UserService {

    @GetMapping("/feign/user/openId")
    User findByOpenId(@RequestParam("openId") String openId);

    @PostMapping("/feign/user")
    Long insertUser(@RequestBody User user);

    @GetMapping("/feign/user/id")
    User findById(@RequestParam("id") Long id);

    @PutMapping("/feign/user")
    void update(@RequestBody User user);

    @GetMapping("/feign/user/list")
    List<User> listUserByQuery(@RequestParam(value = "nick", required = false) String nick,
                               @RequestParam(value = "id", required = false) Long id,
                               @RequestParam(value = "grade", required = false) Integer grade,
                               @RequestParam(value = "phone", required = false) String phone,
                               @RequestParam(value = "email", required = false) String email,
                               @RequestParam(value = "status", required = false) Integer status,
                               @RequestParam(value = "beanFrom", required = false) Integer beanFrom,
                               @RequestParam(value = "beanTo", required = false) Integer beanTo,
                               @RequestParam(value = "address", required = false) String address);
}
