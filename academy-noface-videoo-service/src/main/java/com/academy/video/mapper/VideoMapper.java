package com.academy.video.mapper;

import com.academy.core.pojo.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface VideoMapper {

    List<Video> listVideoByQuery(@Param("title") Integer title, @Param("type") Integer type,
                                 @Param("grade") Integer grade, @Param("subject") Integer subject,
                                 @Param("likeFrom") String likeFrom, @Param("likeTo") String likeTo,
                                 @Param("collectFrom") Integer collectFrom, @Param("collectTo") Integer collectTo,
                                 @Param("teacherId") Long teacherId, @Param("status") Integer status);

    Video findById(Long id);

    void insert(Video video);

    void updateVideo(Video video);
}