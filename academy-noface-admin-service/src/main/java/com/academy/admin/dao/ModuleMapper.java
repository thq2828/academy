package com.academy.admin.dao;


import com.academy.core.pojo.Module;

import javax.validation.groups.ConvertGroup;
import java.util.List;
import java.util.Map;

public interface ModuleMapper {
    /**
     * 根据id删除模块
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增一个模块
     * @param record
     * @return
     */
    int insert(Module record);

    /**
     * 动态插入一个模块
     * @param record
     * @return
     */
    int insertSelective(Module record);

    /**
     * 根据id查询模块
     * @param id
     * @return
     */
    Module selectByPrimaryKey(Long id);

    /**
     * 动态查询数据（分页）
     * @param map
     * @return
     */
    List<Module> selectModules(Map<String,Object> map);

    /**
     * 根据ids查询数据
     * @param map
     * @return
     */
    List<Module> selectByIds(Map<String,Object> map);
    /**
     * 动态更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Module record);

    /**
     * 更新一条数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(Module record);
}