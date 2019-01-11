package com.academy.video;

import com.academy.core.pojo.Collect;
import com.academy.core.pojo.Video;
import com.academy.core.service.VideoService;
import com.academy.video.mapper.CollectMapper;
import com.academy.video.mapper.TeacherMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AcademyNofaceVideooServiceApplicationTests {

    @Resource
    private TeacherMapper teacherMapper;

    @Resource
    private CollectMapper collectMapper;

    @Resource
    private VideoService videoService;
    @Test
    public void contextLoads() {

        System.out.println(collectMapper.listCollectByQuery(3L, 2, 7L, null));

    }

}

