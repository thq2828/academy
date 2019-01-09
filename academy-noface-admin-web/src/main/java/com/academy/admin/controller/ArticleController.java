package com.academy.admin.controller;

import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@Slf4j
@RequestMapping("a")
@Api(tags = "后台文章模块接口")
public class ArticleController {
    private static Article article;

    /**
     * 初始化假数据
     */
    static {
        article = new Article();
        Integer[] integers = new Integer[2];
        integers[0] = 1;
        integers[1] = 0;
        article.setId(2L);
        article.setTitle("这是后台的假数据");
        article.setAuthor("吴彦祖");
        article.setBrief("这是摘要的内容.......");
        article.setCategory(1);
        article.setBody("这是富文本内容............................");
        article.setCollection(100);
        article.setLike(120);
        article.setImgUrl(" http://carrots.ks3-cn-beijing.ksyun.com/img/atlas/b975c68e-e544-4cdc-82ee-f72fcfcbf335.jpg");
        article.setStatus(1);
        article.setState(integers);
        article.setCreateAt(System.currentTimeMillis());
        article.setUpdateAt(System.currentTimeMillis());
        article.setUpdateBy(1L);
        article.setCreateBy(1L);
    }

    @ApiOperation(value = "查询列表", notes = "传入什么参数，对应什么条件，多个参数查询的是满足的多个条件")
    @GetMapping("/a/articles")
    public ResultBean getArticles(@RequestParam(name = "title",required = false) String title,
                                  @RequestParam(name = "author",required = false) String author,
                                  @RequestParam(name = "category",required = false) Integer category,
                                  @RequestParam(name = "status",required = false) Integer status,
                                  @RequestParam(name = "starLike",required = false) Integer starLike,
                                  @RequestParam(name = "endLike",required = false) Integer endLike,
                                  @RequestParam(name = "startCollection",required = false) Integer startCollection,
                                  @RequestParam(name = "endCollection",required = false) Integer endCollection,
                                  @RequestParam(name = "page",required = false) Integer page,
                                  @RequestParam(name = "size",required = false) Integer size) {
        log.info("后台：ArtilcleController.getArticles:测试是否进入");
        log.info("title:" + title + ",author:" + author + ",category:" + category +
                ",status:" + size + ",starLike:" + starLike + ",endLike:" + endLike +
                ",startCollection:" + startCollection + ",endCollection:" + endCollection +
                ",page:" + page + ",size:" + size);
        if (category == null) {
            category = 1;
        }
        if (starLike > endLike || startCollection > endCollection) {
            return new ResultBean(603);
        }
        if (page == null) {
            page = 1;
        }
        if (size == null) {
            size = 10;
        }
        List<Article> articles = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            articles.add(article);
        }
        return new ResultBean<List<Article>>(200,  articles);
    }

    @ApiOperation(value = "文章详情", notes = "根据url中的id获取文章详情")
    @GetMapping("/a/article/{id}")
    public ResultBean getArticle(@PathVariable(name = "id") Long id) {
        log.info("ArtilcleController.getArticle:测试是否进入" + ",id:" + id);
        article.setId(id);
        return new ResultBean<Article>(200, article);
    }

    @ApiOperation(value = "新增文章",notes = "创建用户")
    @PostMapping("/a/article")
    public ResultBean addArticle(@RequestBody Article article){
        log.info("ArtilcleController.putArticle:测试是否进入" );
        return new ResultBean(200);
    }

    @ApiOperation(value = "更新文章",notes ="根据URL获取文章的id进行更新文章" )
    @PutMapping("/a/article/{id}")
    public ResultBean putArticle(@PathVariable(name = "id")Long id,@RequestBody Article article){
        log.info("ArtilcleController.putArticle:测试是否进入" + ",id:" + id);
        article.setStatus(2);
        return new ResultBean(200);
    }

    @ApiOperation(value = "文章上下架",notes = "根据URL获取文章的id进行更新文章上下架")
    @PutMapping("/a/article/status/{id}")
    public ResultBean putArticleStatus(@PathVariable(name = "id")Long id, @RequestBody Article article){
        log.info("ArtilcleController.putArticleStatus:测试是否进入" + ",id:" + id);
        return new ResultBean(200);
    }

}
