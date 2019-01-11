package com.academy.video.mapper;

import com.academy.core.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TeacherMapper {

    Teacher findByNick(String nick);

    Teacher findById(Long id);
}