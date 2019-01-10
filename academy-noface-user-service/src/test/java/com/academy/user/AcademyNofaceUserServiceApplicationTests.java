package com.academy.user;

import com.academy.core.pojo.User;
import com.academy.user.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AcademyNofaceUserServiceApplicationTests {
    @Resource
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        User user = userMapper.findById(5L);
        System.out.println(user.getSignList().toString());
    }

}

