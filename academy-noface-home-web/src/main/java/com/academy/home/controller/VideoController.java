package com.academy.home.controller;

import com.academy.core.pojo.Collect;
import com.academy.core.pojo.Response;
import com.academy.core.pojo.Video;
import com.academy.core.service.CollectService;
import com.academy.core.service.VideoService;
import com.academy.home.utils.LoginUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class VideoController {

    @Resource
    private VideoService videoService;

    @Resource
    private CollectService collectService;

    /**
     * 视频列表
     */
    @GetMapping("/a/u/video/card")
    public Response findList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             @RequestParam(value = "grade") Integer grade,
                             @RequestParam(value = "subject") Integer subject, HttpServletRequest request) {
        Long uid = LoginUtil.getUid(request);
        log.info("card视频列表 grade = {}, subject = {}, uid = {}", grade, subject, uid);

        PageHelper.startPage(page, size);
        List<Video> videoList = videoService.listVideoByQuery(null, Video.CARD_VIDEO, grade, subject, null, null, null, null, null, 1);
        // 设置属性
        for (Video video : videoList) {
            List<Collect> likeList = collectService.findByVideoId(video.getId(), Collect.LIKE, uid, null);
            List<Collect> collectList = collectService.findByVideoId(video.getId(), Collect.COLLECT, uid, null);
            // 当前用户点赞状态
            if(likeList.size() > 0){
                video.setLikeStatus(Collect.STATUS_LIKE);
            }else {
                video.setLikeStatus(Collect.STATUS_UNLIKE);
            }
            // 当前用户收藏状态
            if(collectList.size() > 0){
                video.setCollectStatus(Collect.STATUS_COLLECT);
            }else {
                video.setCollectStatus(Collect.STATUS_UNCOLLECT);
            }
        }
        PageInfo pageInfo = new PageInfo<>(videoList);

        log.info("card视频列表 size = {}", videoList.size());

        return new Response<>(0, "success", pageInfo);
    }

    /**
     * Banner列表
     */
    @GetMapping("/a/u/video/banner")
    public Response findBanner(HttpServletRequest request) {
        Long uid = LoginUtil.getUid(request);
        log.info("查询Banner视频 uid = {}", uid);

        List<Video> videoList = videoService.listVideoByQuery(null, Video.BANNER_VIDEO, null, null, null, null, null, null, null, null);

        log.info("Banner 视频 size = {}", videoList.size());
        return new Response<>(0, "sucess", videoList);
    }

    /**
     * 视频详情
     */
    @GetMapping("/a/u/video/{id}")
    public Response find(@PathVariable("id") Long id, HttpServletRequest request) {
        Long uid = LoginUtil.getUid(request);
        log.info("查询视频 id = {}", id);
        Video video = videoService.findById(id);
        if(video==null){
            log.info("无效id");
            return new Response<>(-1, "无效id", id);
        }
        List<Collect> likeList = collectService.findByVideoId(video.getId(), Collect.LIKE, uid, null);
        List<Collect> collectList = collectService.findByVideoId(video.getId(), Collect.COLLECT, uid, null);
        // 当前用户点赞状态
        if(likeList.size() > 0){
            video.setLikeStatus(Collect.STATUS_LIKE);
        }else {
            video.setLikeStatus(Collect.STATUS_UNLIKE);
        }
        // 当前用户收藏状态
        if(collectList.size() > 0){
            video.setCollectStatus(Collect.STATUS_COLLECT);
        }else {
            video.setCollectStatus(Collect.STATUS_UNCOLLECT);
        }
        return new Response<>(0, "sucess", video);
    }

    @GetMapping("/a/u/video/student/collect")
    public Response collectVideo(HttpServletRequest request) {
        Long uid = LoginUtil.getUid(request);
        log.info("查询用户收藏视频 uid = {}", uid);

        List<Collect> collectList = collectService.findByVideoId(null, Collect.COLLECT, uid, null);
        log.info("用户收藏视频 size = {}", collectList.size());
        List<Long> ids = collectList.stream().map(Collect::getVideoId).collect(Collectors.toList());
        List<Video> videoList = new ArrayList<>();
        for (Long id : ids) {
            Video video = videoService.findById(id);
            videoList.add(video);
        }
        return new Response<>(0, "success", videoList);
    }

    /**
     * 收藏
     */
    @PutMapping("/a/u/video/collect/{id}")
    public Response collect(@PathVariable("id") Long id, HttpServletRequest request) {
        Long uid = LoginUtil.getUid(request);
        log.info("改变视频收藏状态 id = {}, uid = {}", id, uid);
        Video check = videoService.findById(id);
        if(check==null) {
            log.info("id 无效");
            return new Response<>(-1, "无效id", id);
        }
        List<Collect> collectList = collectService.findByVideoId(id, Collect.COLLECT, uid, null);
        if(collectList.size()==0){
            log.info("收藏");
            // 新增一条关系记录
            Collect collect = new Collect();
            collect.setType(Collect.COLLECT);
            collect.setUserId(uid);
            collect.setVideoId(id);
            collectService.insert(collect);
            check.setCollect(check.getCollect() + 1);
            videoService.update(check);
            return new Response<>(0, "success", check);
        }else{
            log.info("取消收藏");
            // 删除一条关系记录
            collectService.delete(Collect.COLLECT, uid, id);
            check.setCollect(check.getCollect() - 1);
            videoService.update(check);
            return new Response<>(0, "success", check);
        }
    }

    /**
     * 点赞
     */
    @PutMapping("/a/u/video/like/{id}")
    public Response like(@PathVariable("id") Long id, HttpServletRequest request) {
        Long uid = LoginUtil.getUid(request);
        log.info("改变视频点赞状态 id = {}, uid = {}", id, uid);
        Video check = videoService.findById(id);
        if(check==null) {
            log.info("id 无效");
            return new Response<>(-1, "无效id", id);
        }
        List<Collect> likeList = collectService.findByVideoId(id, Collect.LIKE, uid, null);
        if(likeList.size()==0){
            log.info("点赞");
            // 新增一条关系记录
            Collect collect = new Collect();
            collect.setType(Collect.LIKE);
            collect.setUserId(uid);
            collect.setVideoId(id);
            collectService.insert(collect);
            check.setLike(check.getLike() + 1);
            videoService.update(check);
            return new Response<>(0, "success", check);
        }else{
            log.info("取消点赞");
            // 删除一条关系记录
            collectService.delete(Collect.LIKE, uid, id);
            check.setLike(check.getLike() - 1);
            videoService.update(check);
            return new Response<>(0, "success", check);
        }
    }

}
