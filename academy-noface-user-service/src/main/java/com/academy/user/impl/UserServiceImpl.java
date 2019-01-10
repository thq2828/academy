package com.academy.user.impl;

import com.academy.core.pojo.User;
import com.academy.core.service.UserService;
import com.academy.user.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    @PutMapping("/feign/user")
    public void update(User user) {
        System.out.println("++++++++++++++++++++++");
        System.out.println(user);
        user.setUpdateAt(System.currentTimeMillis());
        userMapper.updateUser(user);
    }

    @Override
    @GetMapping("/feign/user/list")
    public List<User> listUserByQuery(@RequestParam(value = "nick", required = false) String nick,
                                      @RequestParam(value = "id", required = false) Long id,
                                      @RequestParam(value = "grade", required = false) Integer grade,
                                      @RequestParam(value = "phone", required = false) String phone,
                                      @RequestParam(value = "email", required = false) String email,
                                      @RequestParam(value = "status", required = false) Integer status,
                                      @RequestParam(value = "beanFrom", required = false) Integer beanFrom,
                                      @RequestParam(value = "beanTo", required = false) Integer beanTo,
                                      @RequestParam(value = "address", required = false) String address) {
       return userMapper.listUserByQuery(nick, id, grade, phone, email, status, beanFrom, beanTo, address);
    }
}
