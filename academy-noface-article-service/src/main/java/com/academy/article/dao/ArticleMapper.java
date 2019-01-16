package com.academy.article.dao;


import com.academy.core.pojo.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;


public interface ArticleMapper {
    /**
     * 根据id删除文章
     * @param id
     * @return
     */

    int deleteByPrimaryKey(Long id);

    /**
     * 插入文章
     * @param record
     * @return
     */
    int insert(Article record);

    /**
     * 动态插入文章
     * @param record
     * @return
     */
    int insertSelective(Article record);

    /**
     * 根据id查询文章信息
     * @param id
     * @return
     */
    Article selectByPrimaryKey(Long id);

    /**
     * 动态查询
     * @param map
     * @return
     */
    List<Article> selectBySelective(Map<String,Object> map);

    /**
     * 动态更新数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Article record);

    /**
     * 插入一条数据
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(Article record);

    /**
     * 查询文章对应用户点赞收藏数据
     * @param userId 用户id
     * @param id 文章id
     * @return 返回值为关系表中的type字段
     */

    List<Integer> getUserAndArticleDate(@Param("userId")Long userId,
                                        @Param("id")Long id);

    /**
     * 查询关系表中是否有点赞或者收藏状态
     * @param type
     * @param userId
     * @param id
     * @return
     */
    int selectUserAndArticleByCount(@Param("type")Integer type,
                                    @Param("userId")Long userId,
                                    @Param("id")Long id);

    /**
     * 向关系表中插入一条数据
     * @param type
     * @param userId
     * @param id
     * @return
     */
    int insertUserArticle(@Param("type")Integer type,
                          @Param("userId")Long userId,
                          @Param("id")Long id);

    /**
     * 删除关系表中的一条数据
     * @param type
     * @param userId
     * @param id
     * @return
     */
    int delUserArticle(@Param("type")Integer type,
                       @Param("userId")Long userId,
                       @Param("id")Long id);
}