package com.academy.home.controller;

import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "前台文章模块接口")
@RestController
@Slf4j
@RequestMapping("a")
public class ArtilcleController {
    private static Article article;

    /**
     * 初始化假数据
     */
    static {
        article = new Article();
        Integer[] integers = new Integer[2];
        integers[0] = 1;
        integers[1] = 0;
        article.setId(1L);
        article.setTitle("阿里山的姑娘美如水啊");
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


    /**
     *
     * ---------------------------说明：前台文章模块接口---------------------------
     *
     */

    @ApiOperation(value = "测试ArtilcleController", notes = "测试ArtilcleController")
    @ApiParam(value = "test")
    @GetMapping("/test")
    public String test(String str) {
        log.info("ArtilcleController.test:测试是否进入");
        return "测试：" + str;
    }

    @ApiOperation(value = "文章和banner列表", notes = "根据category的不同，区别banner和文章")
    @GetMapping("/u/articles")
    public ResultBean getArticles(@RequestParam(name = "category") Integer category,
                                  @RequestParam(name = "page",required = false) Integer page,
                                  @RequestParam(name = "size",required = false) Integer size) {
        log.info("ArtilcleController.getArticles:测试是否进入");

        if (category == null) {
            return new ResultBean(604);
        }
        if (page == null) {
            page = 1;
        }
        if (category == 1 && size == null) {
            size = 8;
        }
        if (category == 2 && size == null) {
            size = 10;
        }
        log.info("文章类别：" + category + ",第几页：" + page + ",每页多少行" + size);




        List<Article> articles = new ArrayList<>();
        for (Integer i = 0; i < size; i++) {
            articles.add(article);
        }

        return new ResultBean<List<Article>>(200,articles);
    }

    @ApiOperation(value = "文章详情", notes = "根据url中的id获取文章详情")
    @GetMapping("/u/article/{id}")
    public ResultBean<Article> getArticle(@PathVariable(value = "id") Long id) {
        log.info("ArtilcleController.getArticle:测试是否进入"+",id:"+id);
        article.setId(id);
        return new ResultBean<Article>(200,article);
    }

    @ApiOperation(value = "点赞和收藏",notes = "根据URL获取文章的id，type=1时为点赞，type=2为收藏")
    @PostMapping ("/u/article/like/{id}")
    public ResultBean likeOrCollection(@PathVariable(name = "id")Long id,
                                       @RequestParam(name = "type") Integer type){
        log.info("ArtilcleController.likeOrCollection:测试是否进入"+",id:"+id);
        article.setId(id);
        if (type==null){
            return new ResultBean(601);
        }
        if (type==1){
            return new ResultBean<Integer>(200,article.getLike()+1);
        }
        if (type==2){
            return new ResultBean<Integer>(200,article.getCollection()+1);
        }
        return new ResultBean(602);
    }


    @ApiOperation(value = "取消点赞和收藏",notes = "根据URL获取文章的id，type=1时为取消点赞，type=2为取消收藏")
    @PutMapping("/u/article/like/{id}")
    public ResultBean putLikeOrCollection(@PathVariable(name = "id")Long id,
                                          @RequestParam(name = "type") Integer type){
        log.info("ArtilcleController.putLikeOrCollection:测试是否进入"+",id:"+id);
        article.setId(id);
        if (type==null){
            return new ResultBean(602);
        }
        if (type==1){
            return new ResultBean<Integer>(200,article.getLike()-1);
        }
        if (type==2){
            return new ResultBean<Integer>(200,article.getCollection()-1);
        }

        return new ResultBean(603);
    }
}
