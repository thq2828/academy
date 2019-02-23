package com.academy.admin.dao;


import com.academy.core.pojo.Manager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerMapperTest {
    @Autowired
    ManagerMapper managerMapper;

    @Test
    public void selectByPrimaryKey() {
        System.out.println(managerMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void deleteByPrimaryKey() {
    }

    @Test
    public void insert() {
        Manager manager =new Manager();
        manager.setName("haiqing");
        manager.setPwd("123456");
        manager.setRoleId(1L);
        manager.setCreateBy(1L);
        manager.setCreateAt(System.currentTimeMillis());
        manager.setUpdateBy(1L);
        manager.setUpdateAt(System.currentTimeMillis());
        System.out.println(managerMapper.insert(manager));
    }

    @Test
    public void insertSelective() {
        Manager manager =new Manager();
        manager.setName("haiqing");

        manager.setRoleId(1L);
        manager.setCreateBy(1L);
        manager.setCreateAt(System.currentTimeMillis());
        manager.setUpdateBy(1L);
        manager.setUpdateAt(System.currentTimeMillis());
        System.out.println(managerMapper.insertSelective(manager));
    }

    @Test
    public void selectByName() {
        System.out.println(managerMapper.selectByName1("admin1",67L));
    }

    @Test
    public void selectByPrimaryKeySelective() {
        Map<String,Object> map =new HashMap<>();
        map.put("roleId","1");
        map.put("start",0);
        map.put("size",4);
        System.out.println(managerMapper.selectByPrimaryKeySelective(map));

    }

    @Test
    public void updateByPrimaryKeySelective() {
        Manager manager =new Manager();
        manager.setId(26L);
        manager.setPwd("456789");
        manager.setRoleId(1L);
        manager.setCreateBy(2L);
        manager.setCreateAt(System.currentTimeMillis());
        manager.setUpdateBy(1L);
        System.out.println(managerMapper.updateByPrimaryKeySelective(manager));


    }

    @Test
    public void updateByPrimaryKey() {
        Manager manager =new Manager();
        manager.setId(26L);
        manager.setName("tanghaiqing");
        manager.setPwd("123456");
        manager.setRoleId(2L);
        manager.setCreateBy(2L);
        manager.setCreateAt(System.currentTimeMillis());
        manager.setUpdateBy(2L);
        manager.setUpdateAt(System.currentTimeMillis());
        System.out.println(managerMapper.updateByPrimaryKey(manager));
    }

    @Test
    public void selectByCountSelective() {
        Map<String,Object> map =new HashMap<>();
        map.put("name","admin");
        System.out.println(managerMapper.selectByCountSelective(map));

    }
}
