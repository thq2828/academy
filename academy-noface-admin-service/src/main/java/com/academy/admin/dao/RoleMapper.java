package com.academy.admin.dao;


import com.academy.core.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * 查询全部的角色信息
     */
    List<Role> selectRoles(@Param("start")Integer start,
                           @Param("size")Integer size);

    /**
     * 查询全部数据的总数
     * @return
     */
    Integer selecrByCount();

    /**
     * 根据那么查询数据
     * @param name
     * @return
     */
    Role selectByName(@Param("name") String name);
    /**
     * 批量获取角色详细信息
     */
    List<Role> selectByIds(Map<String,Object> map);

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