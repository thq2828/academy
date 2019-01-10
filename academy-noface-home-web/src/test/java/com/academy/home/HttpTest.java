package com.academy.home;

import com.academy.core.pojo.User;
import com.academy.core.service.UserService;
import com.academy.home.controller.UserController;
import com.academy.home.utils.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpTest {

    @Resource
    private UserController userController;



    @Test
    public void getTest() throws IOException {
        String a = "[1]";
        String b = a.replace("[", "").replace("]","").trim();
        System.out.println(b);
        System.out.println(Arrays.toString(b.split(",")));
    }
}
