package com.academy.admin.dao;

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
public class ModuleMapperTest {
    @Autowired
    private ModuleMapper moduleMapper;

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
        System.out.println(moduleMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void selectModules() {
        Map<String,Object> map =new HashMap<>();
        map.put("id",1);
        map.put("name","后台管理");
        map.put("url","main.article");
        System.out.println("---------------+"+moduleMapper.selectModules(map).size()+"............................");
    }

    @Test
    public void selectByIds() {
        int[] ids =new int[3];
        ids[0]=1;
        ids[1]=3;
        ids[2]=8;
        Map<String,Object> map =new HashMap<>();
        map.put("ids",ids);
        System.out.println("------++++++++++++++........."+moduleMapper.selectByIds(map)+"sssssssssssss-----------");


    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}