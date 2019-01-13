package com.academy.admin.dao;


import com.academy.core.pojo.Role;

public interface RoleMapper {
    /**
     * 根据id删除角色
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 增加一个角色
     * @param record
     * @return
     */
    int insert(Role record);

    /**
     * 动态的插入角色
     * @param record
     * @return
     */
    int insertSelective(Role record);

    /**
     * 根据id查询角色的详情
     * @param id
     * @return
     */
    Role selectByPrimaryKey(Long id);

    /**
     * 动态的更新角色信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Role record);

    /**
     * 更新角色的信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Role record);
}