package com.academy.admin.dao;


import com.academy.core.pojo.Role;

public interface RoleMapper {

    int deleteByPrimaryKey(Long id);
    int insert(Role record);
    int insertSelective(Role record);
    Role selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(Role record);
    int updateByPrimaryKeyWithBLOBs(Role record);
    int updateByPrimaryKey(Role record);
}