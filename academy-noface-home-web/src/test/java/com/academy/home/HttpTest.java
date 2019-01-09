package com.academy.home;

import com.academy.home.utils.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpTest {

    @Test
    public void getTest() throws IOException {
        System.out.println(HttpUtil.getUsr("081e35rC1jTjP40HIqrC1NPOqC1e35rM"));
    }
}
