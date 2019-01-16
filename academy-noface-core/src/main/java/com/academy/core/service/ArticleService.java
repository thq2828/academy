package com.academy.core.service;

import com.academy.core.dto.PageBean;
import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Article;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "article-service")
public interface ArticleService {

    /**
     * 前台文章列表接口业务
     * @param category
     * @param start
     * @param size
     * @param status
     * @return
     */
    @GetMapping("/impl/home/articles")
    public PageBean getHomeArticles(@RequestParam(name = "category", required = false) Integer category,
                                    @RequestParam(name = "start", required = false) Integer start,
                                    @RequestParam(name = "size", required = false) Integer size,
                                    @RequestParam(name = "status", required = false) Integer status,
                                    @RequestParam(name="userId") Long userId);

    /**
     * 后台文章列表业务
     * @param title
     * @param author
     * @param category
     * @param status
     * @param starLike
     * @param endLike
     * @param startCollection
     * @param endCollection
     * @param start
     * @param size
     * @return
     */
    @GetMapping("/impl/articles")
    public PageBean getArticles(@RequestParam(name = "title", required = false) String title,
                                @RequestParam(name = "author", required = false) String author,
                                @RequestParam(name = "category", required = false) Integer category,
                                @RequestParam(name = "status", required = false) Integer status,
                                @RequestParam(name = "starLike", required = false) Integer starLike,
                                @RequestParam(name = "endLike", required = false) Integer endLike,
                                @RequestParam(name = "startCollection", required = false) Integer startCollection,
                                @RequestParam(name = "endCollection", required = false) Integer endCollection,
                                @RequestParam(name = "start", required = false) Integer start,
                                @RequestParam(name = "size", required = false) Integer size);

    /**
     * 查询文章详情
     * @param id
     * @return
     */
    @GetMapping("/impl/article")
    public ResultBean getArticle(@RequestParam(name = "id") Long id,
                                 @RequestParam(name = "userId",required = false)Long userId);

    /**
     * 增加一条文章数据
     * @param article
     * @return
     */
    @PostMapping("/impl/article")
    public ResultBean addArticle(@RequestBody Article article);

    /**
     * 更新一条文章数据
     * @param article
     * @return
     */
    @PutMapping("/impl/article")
    public ResultBean putArticle(@RequestBody Article article);

    /**
     * 点赞和收藏业务
     * @param userId
     * @param id
     * @param type
     * @return
     */
    @PostMapping("/impl/article/like")
    public ResultBean putArticleLike(@RequestParam(name = "userId") Long userId,
                                     @RequestParam(name = "id") Long id,
                                     @RequestParam(name = "type") Integer type);

    /**
     * 取消点赞和收藏
     * @param userId
     * @param id
     * @param type
     * @return
     */
    @DeleteMapping("/impl/article/like")
    public ResultBean delArticleLike(@RequestParam(name = "userId") Long userId,
                                        @RequestParam(name = "id") Long id,
                                        @RequestParam(name = "type") Integer type);

    /**
     * 上下架业务
     * @param article
     * @return
     */
    @PutMapping("/impl/article/status")
    public ResultBean putArticleStatus(@RequestBody Article article);


}
