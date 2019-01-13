package com.academy.uaa.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerDaoTest {
    @Autowired
   private ManagerDao managerDao;

    @Test
    public void findByName() {
        System.out.println(managerDao.findByName("admin"));
    }
}
