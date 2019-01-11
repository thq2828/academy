package com.academy.video.impl;

import com.academy.core.pojo.Teacher;
import com.academy.core.service.TeacherService;
import com.academy.video.mapper.TeacherMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TeacherServiceImpl implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    @Override
    public Teacher findByNick(@RequestParam(value = "nick") String nick) {
        return teacherMapper.findByNick(nick);
    }

    @Override
    public Teacher findById(@PathVariable("id") Long id) {
        return teacherMapper.findById(id);
    }
}
