package com.academy.core.service;

import com.academy.core.pojo.Collect;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "academy-noface-videoo-service")
public interface CollectService {

    @GetMapping("/feign/collect/videoId")
    List<Collect> findByVideoId(@RequestParam(value = "videoId", required = false) Long videoId,
                                @RequestParam(value = "type", required = false) Integer type,
                                @RequestParam(value = "userId", required = false) Long userId,
                                @RequestParam(value = "id", required = false) Long id);

    @PostMapping("/feign/collect")
    void insert(@RequestBody Collect collect);

    @DeleteMapping("/feign/collect")
    void delete(@RequestParam(value = "type") Integer type, @RequestParam(value = "userId") Long userId,
                @RequestParam(value = "videoId") Long videoId);
}
