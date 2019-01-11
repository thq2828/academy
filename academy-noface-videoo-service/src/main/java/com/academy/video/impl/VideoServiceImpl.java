package com.academy.video.impl;

import com.academy.core.pojo.Collect;
import com.academy.core.pojo.Teacher;
import com.academy.core.pojo.Video;
import com.academy.core.service.CollectService;
import com.academy.core.service.TeacherService;
import com.academy.core.service.VideoService;
import com.academy.video.mapper.VideoMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class VideoServiceImpl implements VideoService {

    @Resource
    private VideoMapper videoMapper;

    @Resource
    private TeacherService teacherService;

    @Override
    public List<Video> listVideoByQuery(@RequestParam(value = "title", required = false) Integer title,
                                        @RequestParam(value = "type", required = false) Integer type,
                                        @RequestParam(value = "grade", required = false) Integer grade,
                                        @RequestParam(value = "subject", required = false) Integer subject,
                                        @RequestParam(value = "likeFrom", required = false) String likeFrom,
                                        @RequestParam(value = "likeTo", required = false) String likeTo,
                                        @RequestParam(value = "collectFrom", required = false) Integer collectFrom,
                                        @RequestParam(value = "collectTo", required = false) Integer collectTo,
                                        @RequestParam(value = "teacherId", required = false) Long teacherId,
                                        @RequestParam(value = "status", required = false) Integer status) {
        List<Video> videoList = videoMapper.listVideoByQuery(title, type, grade, subject, likeFrom, likeTo, collectFrom, collectTo, teacherId, status);
        // 封装属性
        for (Video video : videoList) {
            Teacher teacher = teacherService.findById(video.getTeacherId());
            video.setTeacherNick(teacher.getNick());
            video.setTeacherPic(teacher.getPic());
        }
        return videoList;
    }

    @Override
    public Video findById(@PathVariable("id") Long id) {
        Video video = videoMapper.findById(id);
        Teacher teacher = teacherService.findById(video.getTeacherId());
        video.setTeacherNick(teacher.getNick());
        video.setTeacherPic(teacher.getPic());
        return video;
    }

    @Override
    public Long insert(@RequestBody Video video) {
        video.setCreateAt(System.currentTimeMillis());
        video.setUpdateAt(System.currentTimeMillis());
        videoMapper.insert(video);
        return video.getId();
    }

    @Override
    public void update(@RequestBody Video video) {
        video.setUpdateAt(System.currentTimeMillis());
        videoMapper.updateVideo(video);
    }
}
