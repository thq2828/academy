package com.academy.home.controller;

import com.academy.core.pojo.Response;
import com.academy.core.pojo.Video;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class VideoController {

    /**
     * 视频列表
     */
    @GetMapping("/a/u/video/card")
    public Response findList(@RequestParam(value = "page") Integer page,
                             @RequestParam(value = "size") Integer size,
                             @RequestParam(value = "grade") Integer grade,
                             @RequestParam(value = "subject") Integer subject) {
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
     * Banner列表
     */
    @GetMapping("/a/u/video/banner")
    public Response findBanner() {
        Video video = new Video();
        video.setId(1L);
        video.setTitle("葫芦娃全集");
        video.setType(2);
        video.setGrade(3);
        video.setSubject(4);
        video.setIntro("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        video.setContent("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        video.setUrl("https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4");
        video.setTeacherId(1L);
        video.setTeacherNick("苍老师");
        video.setTeacherPic("http://thirdwx.qlogo.cn/mmopen/vi_32/kaCCtnsmXico9mEWKmTKeZpjibLLchNe5TTF7b9IoAAeqQoP1BnRpDuukr1DsVnFXm1zzM2tWRck8biaMUffh2DPg/132");
        video.setStatus(1);
        video.setCover("http://thirdwx.qlogo.cn/mmopen/vi_32/kaCCtnsmXico9mEWKmTKeZpjibLLchNe5TTF7b9IoAAeqQoP1BnRpDuukr1DsVnFXm1zzM2tWRck8biaMUffh2DPg/132");
        video.setCollect(200);
        video.setLike(888);
        video.setCollectStatus(1);
        video.setLikeStatus(1);

        List<Video> videoList = new ArrayList<>();
        for(int i = 0; i < 8; i++) {
            videoList.add(video);
        }
        return new Response<>(0, "sucess", videoList);
    }

    /**
     * 视频详情
     */
    @GetMapping("/a/u/video/{id}")
    public Response find(@PathVariable("id") Long id) {
        Video video = new Video();
        video.setId(id);
        video.setTitle("葫芦娃全集");
        video.setType(2);
        video.setGrade(3);
        video.setSubject(4);
        video.setIntro("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        video.setContent("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
        video.setUrl("https://download.blender.org/peach/bigbuckbunny_movies/BigBuckBunny_320x180.mp4");
        video.setTeacherId(1L);
        video.setTeacherNick("苍老师");
        video.setTeacherPic("http://thirdwx.qlogo.cn/mmopen/vi_32/kaCCtnsmXico9mEWKmTKeZpjibLLchNe5TTF7b9IoAAeqQoP1BnRpDuukr1DsVnFXm1zzM2tWRck8biaMUffh2DPg/132");
        video.setStatus(1);
        video.setCover("http://thirdwx.qlogo.cn/mmopen/vi_32/kaCCtnsmXico9mEWKmTKeZpjibLLchNe5TTF7b9IoAAeqQoP1BnRpDuukr1DsVnFXm1zzM2tWRck8biaMUffh2DPg/132");
        video.setCollect(200);
        video.setLike(888);
        video.setCollectStatus(1);
        video.setLikeStatus(1);
        return new Response<>(0, "sucess", video);
    }

    /**
     * 收藏
     */
    @PutMapping("/a/u/video/collect/{id}")
    public Response collect(@PathVariable("id") Long id) {
        Map<String, Integer> map = new HashMap<>(4);
        map.put("collectStatus", 1);
        map.put("likeStatus", 0);
        map.put("collect", 666);
        map.put("like", 888);

        return new Response<>(0, "sucess", map);
    }

    /**
     * 点赞
     */
    @PutMapping("/a/u/video/like/{id}")
    public Response like(@PathVariable("id") Long id) {
        Map<String, Integer> map = new HashMap<>(4);
        map.put("collectStatus", 0);
        map.put("likeStatus", 1);
        map.put("collect", 666);
        map.put("like", 888);

        return new Response<>(0, "sucess", map);
    }

}
