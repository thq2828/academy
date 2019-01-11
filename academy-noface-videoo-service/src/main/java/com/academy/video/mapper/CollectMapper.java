package com.academy.video.mapper;

import com.academy.core.pojo.Collect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectMapper {

    List<Collect> listCollectByQuery(@Param("videoId") Long videoId, @Param("type") Integer type,
                                     @Param("userId") Long userId, @Param("id") Long id);

    void insert(Collect collect);

    void deleteCollect(@Param("type") Integer type, @Param("userId") Long userId,
                       @Param("videoId") Long videoId);
}
