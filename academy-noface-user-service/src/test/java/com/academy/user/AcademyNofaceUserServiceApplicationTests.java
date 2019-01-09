package com.academy.user;

import com.academy.user.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AcademyNofaceUserServiceApplicationTests {
    @Resource
    private UserMapper userMapper;

    @Test
    public void contextLoads() {
        
    }

}

