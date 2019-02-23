package com.academy.admin.service.impl;

import com.academy.core.pojo.Module;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleServiceImplTest {
    @Autowired
    ModuleServiceImpl moduleService;

    @Test
    public void putModule() {
        Module m =new Module();
        m.setId(7L);
        m.setName("视频列表");
        m.setUrl("videolist");
        m.setParentId(8L);



    }


}