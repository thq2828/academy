package com.academy.user.mapper;

import com.academy.core.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface UserMapper {

    User findByOpenId(String openId);

    void insertUser(User user);

    User findById(Long id);

    void updateUser(User user);

    List<User> listUserByQuery(@Param("nick") String nick, @Param("id") Long id,
                               @Param("grade") Integer grade, @Param("phone") String phone,
                               @Param("email") String email, @Param("status") Integer status,
                               @Param("beanFrom") Integer beanFrom, @Param("beanTo") Integer beanTo,
                               @Param("address") String address);
}
