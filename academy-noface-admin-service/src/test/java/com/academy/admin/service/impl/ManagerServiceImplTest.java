package com.academy.admin.service.impl;

import com.academy.core.pojo.Manager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerServiceImplTest {
    @Autowired
    ManagerServiceImpl managerService;



    @Test
    public void getManagerInfo() {
    }

    @Test
    public void getManagers() {
        Map<String,Object> map =new HashMap<>();
        map.put("name","qqq");
        map.put("start",0);
        map.put("size",4);
        //System.out.println(managerService.getManagers(map));
    }
}
