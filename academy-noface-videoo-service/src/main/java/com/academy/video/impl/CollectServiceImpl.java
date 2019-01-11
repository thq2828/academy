package com.academy.video.impl;

import com.academy.core.pojo.Collect;
import com.academy.core.service.CollectService;
import com.academy.video.mapper.CollectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CollectServiceImpl implements CollectService {

    @Resource
    private CollectMapper collectMapper;

    @Override
    public List<Collect> findByVideoId(@RequestParam(value = "videoId", required = false) Long videoId,
                                       @RequestParam(value = "type", required = false) Integer type,
                                       @RequestParam(value = "userId", required = false) Long userId,
                                       @RequestParam(value = "id", required = false) Long id) {
        return collectMapper.listCollectByQuery(videoId, type, userId, id);
    }

    @Override
    public void insert(@RequestBody Collect collect) {
        collectMapper.insert(collect);
    }

    @Override
    public void delete(@RequestParam(value = "type") Integer type, @RequestParam(value = "userId") Long userId,
                       @RequestParam(value = "videoId") Long videoId) {
        collectMapper.deleteCollect(type, userId, videoId);
    }
}
