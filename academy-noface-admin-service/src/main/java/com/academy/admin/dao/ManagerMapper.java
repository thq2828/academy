package com.academy.admin.dao;


import com.academy.core.pojo.Manager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 管理员接口，主要是操作数据库增删改查接口
 */
public interface ManagerMapper {
    /**
     * 根据id删除数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入一整条数据
     * @param record
     * @return
     */
    int insert(Manager record);

    /**
     * 动态插入数据
     * @param record
     * @return
     */
    int insertSelective(Manager record);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    Manager selectByPrimaryKey(Long id);

    /**
     * 根据账号查询用户
     * @param name
     * @return
     */
    Manager selectByName(@Param("name") String name);

    /**
     * 动态查询数据
     * @param map 传入的条件
     * @return
     */
   List<Manager> selectByPrimaryKeySelective(Map<String,Object> map);

    /**
     * 动态查询数据的总数
     * @param map
     * @return
     */
   int selectByCountSelective(Map<String,Object> map);

    /**
     * 动态更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Manager record);

    /**
     * 更新一整天数据
     * @param record
     * @return
     */
    int updateByPrimaryKey(Manager record);
}