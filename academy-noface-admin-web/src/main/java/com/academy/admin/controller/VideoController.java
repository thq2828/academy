package com.academy.admin.controller;

import com.academy.core.pojo.Response;
import com.academy.core.pojo.Teacher;
import com.academy.core.pojo.User;
import com.academy.core.pojo.Video;
import com.academy.core.service.TeacherService;
import com.academy.core.service.VideoService;
import com.academy.core.util.AccessTokenUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.academy.core.util.Constant.MANAGER_ID;

/**
 * @author Joe
 */
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
    public Response add(@RequestBody Video video, HttpServletRequest request) {
        Long uid = (Long) AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID);
        log.info("新增视频 uid = {}", uid);
        Integer type = video.getType();
        if(type == null) {
            log.info("视频类型错误");
            return new Response<>(-1, "类型错误", video);
        }
        if(!(type.equals(Video.CARD_VIDEO) || type.equals(Video.BANNER_VIDEO))) {
            log.info("视频类型错误 type = {}", type);
            return new Response<>(-1, "类型错误", video);
        }
        if(StringUtils.isEmpty(video.getTitle()) || video.getTitle().length() > Video.TITLE_LENGTH) {
            log.info("视频标题错误 title = {}", video.getTitle());
            return new Response<>(-1, "标题错误", video);
        }
        if(StringUtils.isEmpty(video.getIntro()) || video.getIntro().length() > Video.INTRO_LENGTH) {
            log.info("视频简介错误 intro = {}", video.getIntro());
            return new Response<>(-1, "简介错误", video);
        }
        Integer grade = video.getGrade();
        if(grade == null || grade < User.GRADE_LOW || grade > User.GRADE_HIGH) {
            log.info("视频年级错误 grade = {}", grade);
            return new Response<>(-1, "年级错误", video);
        }
        Integer subject = video.getSubject();
        if(subject == null || subject < User.SUBJECT_LOW || subject > User.SUBJECT_HIGH) {
            log.info("视频科目错误 subject = {}", subject);
            return new Response<>(-1, "科目错误", video);
        }

        String url = video.getUrl();
        if(!StringUtils.substringAfter(url, ".").toUpperCase().equals(Video.VIDEO_FORMAT)){
            log.info("视频格式错误 url = {}", url);
            return new Response<>(-1, "格式错误", video);
        }
        String cover = video.getCover();
        if(video.getType().equals(Video.BANNER_VIDEO)) {
            if(cover == null) {
                log.info("Banner视频必须有cover");
                return new Response<>(-1, "Banner视频缺少cover", video);
            }
        }
        video.setCreateBy(uid);
        video.setUpdateBy(uid);
        Long id = videoService.insert(video);
        log.info("新增视频 id = {}", id);
        return new Response<>(0, "success", id);
    }

    /**
     * 编辑视频
     */
    @PutMapping("/a/u/video/{id}")
    public Response update(@PathVariable("id") Long id, @RequestBody Video video, HttpServletRequest request) {
        Long uid = (Long) AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID);
        log.info("编辑视频 id = {}, uid = {}", id, uid);
        Video check = videoService.findById(id);
        if(check==null){
            log.info("无效id = {}", id);
            return new Response<>(-1, "无效id", id);
        }
        Integer type = video.getType();
        if(type == null) {
            log.info("视频类型错误");
            return new Response<>(-1, "类型错误", video);
        }
        if(!(type.equals(Video.CARD_VIDEO) || type.equals(Video.BANNER_VIDEO))) {
            log.info("视频类型错误 type = {}", type);
            return new Response<>(-1, "类型错误", video);
        }
        if(StringUtils.isEmpty(video.getTitle()) || video.getTitle().length() > Video.TITLE_LENGTH) {
            log.info("视频标题错误 title = {}", video.getTitle());
            return new Response<>(-1, "标题错误", video);
        }
        if(StringUtils.isEmpty(video.getIntro()) || video.getIntro().length() > Video.INTRO_LENGTH) {
            log.info("视频简介错误 intro = {}", video.getIntro());
            return new Response<>(-1, "简介错误", video);
        }
        Integer grade = video.getGrade();
        if(grade == null || grade < User.GRADE_LOW || grade > User.GRADE_HIGH) {
            log.info("视频年级错误 grade = {}", grade);
            return new Response<>(-1, "年级错误", video);
        }
        Integer subject = video.getSubject();
        if(subject == null || subject < User.SUBJECT_LOW || subject > User.SUBJECT_HIGH) {
            log.info("视频科目错误 subject = {}", subject);
            return new Response<>(-1, "科目错误", video);
        }

        String url = video.getUrl();
        if(!StringUtils.substringAfter(url, ".").toUpperCase().equals(Video.VIDEO_FORMAT)){
            log.info("视频格式错误 url = {}", url);
            return new Response<>(-1, "格式错误", video);
        }
        String cover = video.getCover();
        if(video.getType().equals(Video.BANNER_VIDEO)) {
            if(cover == null) {
                log.info("Banner视频必须有cover");
                return new Response<>(-1, "Banner视频缺少cover", video);
            }
        }
        // 更新数据
        check.setTitle(video.getTitle());
        check.setGrade(video.getGrade());
        check.setSubject(video.getSubject());
        check.setTeacherId(video.getTeacherId());
        check.setType(video.getType());
        check.setIntro(video.getIntro());
        check.setContent(video.getContent());
        check.setUrl(video.getUrl());
        check.setCover(video.getCover());
        check.setUpdateBy(uid);
        videoService.update(video);
        log.info("后台视频更新成功 id = {}", id);

        return new Response<>(0, "success", id);
    }

    /**
     * 视频上下架
     */
    @PutMapping("/a/u/video/status/{id}")
    public Response updateStatus(@PathVariable("id") Long id, HttpServletRequest request) {
        Long uid = (Long) AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID);
        log.info("视频上下架 id = {}， uid = {}", id, uid);
        Video check = videoService.findById(id);
        if(check == null) {
            log.info("无效id");
            return new Response<>(-1, "无效id", id);
        }
        check.setStatus(check.getStatus().equals(Video.ON) ? Video.OFF : Video.ON);
        videoService.update(check);
        log.info("视频status更新成功id = {}", id);
        return new Response<>(0, "success", id);
    }


    /**
     * 老师列表
     */
    @GetMapping("/a/u/teacher/list")
    public Response findTeachers() {
        log.info("查询全部老师");
        List<Teacher> teachers = teacherService.findAll();
        return new Response<>(0, "success", teachers);
    }

    /**
     * 新增老师
     */
    @PostMapping("/a/u/teacher")
    public Response addTeacher(@RequestBody Teacher teacher) {
        log.info("新增老师");
        Long id = teacherService.insert(teacher);
        log.info("新增老师成功 id = {}", id);
        return new Response<>(0, "success", id);
    }

    /**
     * 删除老师
     */
    @DeleteMapping("/a/u/teacher/{id}")
    public Response deleteTeacher(@PathVariable("id") Long id) {
        log.info("删除老师 id = {}", id);
        Teacher check = teacherService.findById(id);
        if(check==null) {
            log.info("无效id");
            return new Response<>(-1, "无效id", id);
        }
        teacherService.delete(id);
        log.info("删除成功");
        return new Response<>(0, "success", 2L);
    }
}
