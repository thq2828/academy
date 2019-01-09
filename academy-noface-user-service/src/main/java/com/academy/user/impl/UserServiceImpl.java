package com.academy.user.impl;

import com.academy.core.pojo.User;
import com.academy.core.service.UserService;
import com.academy.user.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    @GetMapping("/feign/user/openId")
    public User findByOpenId(@RequestParam("openId") String openId) {
        return userMapper.findByOpenId(openId);
    }

    @Override
    @PostMapping("/feign/user")
    public Long insertUser(@RequestBody User user) {
        user.setCreateAt(System.currentTimeMillis());
        user.setUpdateAt(System.currentTimeMillis());
        userMapper.insertUser(user);
        return user.getId();
    }

    @Override
    @GetMapping("/feign/user/id")
    public User findById(@RequestParam("id") Long id) {
        return userMapper.findById(id);
    }
}
