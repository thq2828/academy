package com.academy.video.impl;

import com.academy.core.pojo.Teacher;
import com.academy.core.service.TeacherService;
import com.academy.video.mapper.TeacherMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<Teacher> findAll() {
        return teacherMapper.findList();
    }

    @Override
    public Long insert(@RequestBody Teacher teacher) {
        teacherMapper.insert(teacher);
        return teacher.getId();
    }

    @Override
    public void delete(@PathVariable("id") Long id) {
        teacherMapper.delete(id);
    }
}
