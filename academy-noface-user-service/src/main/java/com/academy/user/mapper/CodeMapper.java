package com.academy.user.mapper;

import com.academy.core.pojo.Code;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeMapper {

    void insert(Code code);

    Code findByNumber(String number);

    void update(Code code);
}
