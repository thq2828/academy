package com.academy.user.mapper;

import com.academy.core.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User findByOpenId(String openId);

    void insertUser(User user);

    User findById(Long id);
}
