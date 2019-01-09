package com.academy.admin.dao;


import com.academy.core.pojo.Manager;

public interface ManagerMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Manager record);


    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Long id);


    int updateByPrimaryKeySelective(Manager record);


    int updateByPrimaryKey(Manager record);
}