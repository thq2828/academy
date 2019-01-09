package com.academy.admin.controller;

import com.academy.core.pojo.Response;
import com.academy.core.pojo.Teacher;
import com.academy.core.pojo.Video;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VideoController {

    /**
     * 视频列表
     */
    @GetMapping("/a/u/video/list")
    public Response findList(@RequestParam(value = "page") Integer page,
                             @RequestParam(value = "size") Integer size,
                             @RequestParam(value = "title", required = false) Integer title,
                             @RequestParam(value = "grade", required = false) Integer grade,
                             @RequestParam(value = "subject", required = false) Integer subject,
                             @RequestParam(value = "likeFrom", required = false) String likeFrom,
                             @RequestParam(value = "likeTo", required = false) String likeTo,
                             @RequestParam(value = "collectFrom", required = false) Integer collectFrom,
                             @RequestParam(value = "collectTo", required = false) Integer collectTo,
                             @RequestParam(value = "teacherName", required = false) Integer teacherName,
                             @RequestParam(value = "status", required = false) String status) {
        Video video = new Video();

        video.setId(1L);
        video.setTitle("葫芦娃全集");
        video.setType(1);
        video.setGrade(3);
        video.setSubject(4);
        video.setIntro("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        video.setContent("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        video.setUrl("https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4");
        video.setTeacherId(1L);
        video.setTeacherNick("苍老师");
        video.setTeacherPic("http://thirdwx.qlogo.cn/mmopen/vi_32/kaCCtnsmXico9mEWKmTKeZpjibLLchNe5TTF7b9IoAAeqQoP1BnRpDuukr1DsVnFXm1zzM2tWRck8biaMUffh2DPg/132");
        video.setStatus(1);
        video.setCollect(200);
        video.setLike(888);
        video.setCollectStatus(1);
        video.setLikeStatus(1);

        List<Video> videoList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            videoList.add(video);
        }

        return new Response<>(0, "success", videoList);
    }

    /**
     * 视频详情
     */
    @GetMapping("/a/u/video/{id}")
    public Response find(@PathVariable("id") Long id) {
        Video video = new Video();
        video.setId(1L);
        video.setTitle("葫芦娃全集");
        video.setType(1);
        video.setGrade(3);
        video.setSubject(4);
        video.setIntro("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        video.setContent("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        video.setUrl("https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4");
        video.setTeacherId(1L);
        video.setTeacherNick("苍老师");
        video.setTeacherPic("http://thirdwx.qlogo.cn/mmopen/vi_32/kaCCtnsmXico9mEWKmTKeZpjibLLchNe5TTF7b9IoAAeqQoP1BnRpDuukr1DsVnFXm1zzM2tWRck8biaMUffh2DPg/132");
        video.setStatus(1);
        video.setCollect(200);
        video.setLike(888);
        video.setCollectStatus(1);
        video.setLikeStatus(1);
        return new Response<>(0, "success", video);
    }

    /**
     * 新增视频
     */
    @PostMapping("/a/u/video")
    public Response add(@RequestBody Video video) {
        return new Response<>(0, "success", 2L);
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
