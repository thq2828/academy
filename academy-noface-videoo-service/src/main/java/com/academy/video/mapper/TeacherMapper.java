package com.academy.video.mapper;

import com.academy.core.pojo.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {

    Teacher findByNick(String nick);

    Teacher findById(Long id);

    List<Teacher> findList();

    void insert(Teacher teacher);

    void delete(Long id);
}