package com.academy.admin.dao;


import com.academy.core.pojo.Module;

public interface ModuleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Module record);

    int insertSelective(Module record);

    Module selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Module record);

    int updateByPrimaryKey(Module record);
}