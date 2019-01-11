package com.academy.admin.controller;

import com.academy.core.pojo.Response;
import com.academy.core.pojo.Teacher;
import com.academy.core.pojo.Video;
import com.academy.core.service.TeacherService;
import com.academy.core.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class VideoController {

    @Resource
    private VideoService videoService;

    @Resource
    private TeacherService teacherService;
    /**
     * 视频列表
     */
    @GetMapping("/a/u/video/list")
    public Response findList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             @RequestParam(value = "title", required = false) Integer title,
                             @RequestParam(value = "type", required = false) Integer type,
                             @RequestParam(value = "grade", required = false) Integer grade,
                             @RequestParam(value = "subject", required = false) Integer subject,
                             @RequestParam(value = "likeFrom", required = false) String likeFrom,
                             @RequestParam(value = "likeTo", required = false) String likeTo,
                             @RequestParam(value = "collectFrom", required = false) Integer collectFrom,
                             @RequestParam(value = "collectTo", required = false) Integer collectTo,
                             @RequestParam(value = "teacherName", required = false) String teacherName,
                             @RequestParam(value = "status", required = false) Integer status) {
        log.info("后台查询视频列表title={},type={},grade={},subject={},likeFrom={},likeTo={},collectFrom={},collectTo={},teacherName={},status={}",
                title, type, grade, subject, likeFrom, likeTo, collectFrom, collectTo, teacherName, status);

        Long teacherId;
        if(teacherName==null){
            teacherId = null;
        }else {
            Teacher teacher = teacherService.findByNick(teacherName);
            if(teacher == null) {
                teacherId = null;
            }else {
                teacherId = teacher.getId();
            }
        }

        PageHelper.startPage(page, size);
        List<Video> videoList = videoService.listVideoByQuery(title, type, grade, subject, likeFrom, likeTo, collectFrom, collectTo, teacherId, status);
        PageInfo pageInfo = new PageInfo<>(videoList);
        log.info("视频列表 size = {}", videoList.size());

        return new Response<>(0, "success", pageInfo);
    }

    /**
     * 视频详情
     */
    @GetMapping("/a/u/video/{id}")
    public Response find(@PathVariable("id") Long id) {
        log.info("视频详情 id = {}", id);
        Video video = videoService.findById(id);
        return new Response<>(0, "success", video);
    }

    /**
     * 新增视频
     */
    @PostMapping("/a/u/video")
    public Response add(@RequestBody Video video) {
        // +++++++++++++++++++++++++++++++
        video.setCreateBy(1L);
        video.setUpdateBy(1L);
        // ++++++++++++++++++++++++++++++
        Long id = videoService.insert(video);
        log.info("新增视频 id = {}", id);
        return new Response<>(0, "success", id);
    }

    /**
     * 编辑视频
     */
    @PutMapping("/a/u/video/{id}")
    public Response update(@PathVariable("id") Long id) {
        return new Response<>(0, "success", id);
    }

    /**
     * 视频上下架
     */
    @PutMapping("/a/u/video/status/{id}")
    public Response updateStatus(@PathVariable("id") Long id) {
        return new Response<>(0, "success", id);
    }


    /**
     * 老师列表
     */
    @GetMapping("/a/u/teacher/list")
    public Response findTeachers() {
        Teacher teacher = new Teacher();
        teacher.setId(1L);
        teacher.setNick("奥术大师多撒");
        teacher.setPic("http://thirdwx.qlogo.cn/mmopen/vi_32/kaCCtnsmXico9mEWKmTKeZpjibLLchNe5TTF7b9IoAAeqQoP1BnRpDuukr1DsVnFXm1zzM2tWRck8biaMUffh2DPg/132");

        List<Teacher> teacherList = new ArrayList<>();

        for(int i = 0; i < 5; i++) {
            teacherList.add(teacher);
        }

        return new Response<>(0, "success", teacherList);
    }

    /**
     * 新增老师
     */
    @PostMapping("/a/u/teacher")
    public Response addTeacher(@RequestBody Teacher teacher) {
        return new Response<>(0, "success", 2L);
    }

    /**
     * 删除老师
     */
    @DeleteMapping("/a/u/teacher/{id}")
    public Response deleteTeacher(@PathVariable("id") Long id) {
        return new Response<>(0, "success", 2L);
    }




}
