package com.academy.core.service;

import com.academy.core.pojo.Video;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "academy-noface-videoo-service")
public interface VideoService {

    @GetMapping("/feign/video/list")
    List<Video> listVideoByQuery(@RequestParam(value = "title", required = false) Integer title,
                                 @RequestParam(value = "type", required = false) Integer type,
                                 @RequestParam(value = "grade", required = false) Integer grade,
                                 @RequestParam(value = "subject", required = false) Integer subject,
                                 @RequestParam(value = "likeFrom", required = false) Integer likeFrom,
                                 @RequestParam(value = "likeTo", required = false) Integer likeTo,
                                 @RequestParam(value = "collectFrom", required = false) Integer collectFrom,
                                 @RequestParam(value = "collectTo", required = false) Integer collectTo,
                                 @RequestParam(value = "teacherId", required = false) Long teacherId,
                                 @RequestParam(value = "status", required = false) Integer status);

    @GetMapping("/feign/video/{id}")
    Video findById(@PathVariable("id") Long id);

    @PostMapping("/feign/video")
    Long insert(@RequestBody Video video);

    @PutMapping("/feign/video")
    void update(@RequestBody Video video);
}
