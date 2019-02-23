package com.academy.admin.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMapperTest {
    @Autowired
    RoleMapper roleMapper;



    @Test
    public void deleteByPrimaryKey() {



    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void selectRoles() {
        System.out.println(roleMapper.selectRoles(1, 2));
    }

    @Test
    public void selecrByCount() {
        System.out.println(roleMapper.selecrByCount());
    }

    @Test
    public void selectByIds() {
        Map<String,Object> map =new HashMap<>();
        int[] ids =new int[3];
        ids[0]=1;
        ids[1]=3;
        ids[2]=8;
        map.put("ids",ids);
        //map.put("start",1);
        //map.put("size",2);
        System.out.println(roleMapper.selectByIds(map).size());

    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }

    @Test
    public void selectByName() {
        System.out.println(roleMapper.selectByName1("管理页",5L));
    }
}
