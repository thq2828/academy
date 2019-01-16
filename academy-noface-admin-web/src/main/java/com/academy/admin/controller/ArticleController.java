package com.academy.admin.controller;

import com.academy.core.dto.PageBean;
import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Article;
import com.academy.core.service.ArticleService;
import com.academy.core.util.AccessTokenUtil;
import com.academy.core.util.PageUtil;
import com.academy.core.util.PublicUtility;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.academy.core.constant.Constant.*;


@RestController
@Slf4j
@RequestMapping("a")
@Api(tags = "后台文章模块接口")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @ApiOperation(value = "查询列表", notes = "传入什么参数，对应什么条件，多个参数查询的是满足的多个条件，" +
            "初始点赞收藏数不能大于结束点收藏赞数。默认：page=1,size=10")
    @GetMapping("/a/articles")
    public ResultBean getArticles(@RequestParam(name = "title", required = false) String title,
                                  @RequestParam(name = "author", required = false) String author,
                                  @RequestParam(name = "category", required = false) Integer category,
                                  @RequestParam(name = "status", required = false) Integer status,
                                  @RequestParam(name = "E", required = false) Integer starLike,
                                  @RequestParam(name = "endLike", required = false) Integer endLike,
                                  @RequestParam(name = "startCollection", required = false) Integer startCollection,
                                  @RequestParam(name = "endCollection", required = false) Integer endCollection,
                                  @RequestParam(name = "page", required = false) Integer page,
                                  @RequestParam(name = "size", required = false) Integer size) {
        log.info("后台：ArtilcleController.getArticles:测试是否进入");
        log.info("title:" + title + ",author:" + author + ",category:" + category +
                ",status:" + size + ",starLike:" + starLike + ",endLike:" + endLike +
                ",startCollection:" + startCollection + ",endCollection:" + endCollection +
                ",page:" + page + ",size:" + size);
        if (starLike != null && endLike != null) {
            if (starLike > endLike) {
                return new ResultBean(603);
            }
        }
        if (startCollection != null && endCollection != null) {
            if (startCollection > endCollection) {
                return new ResultBean(603);
            }
        }

        if (page == null) {
            page = START_PAGE;
        }
        if (size == null) {
            size = START_SIZE;
        }
        int start = PageUtil.getStart(page, size);
        //采用的是web，service分离，并且是http协议，所以只能这样传参。
        ResultBean resultBean =articleService.getArticles(title, author, category, status, starLike, endLike,
                startCollection, endCollection, start, size);
        if (!PublicUtility.isNullOrEmpty(resultBean.getData())) {
            ((PageBean) resultBean).setPageNum(page);
        }
        return resultBean;
    }

    @ApiOperation(value = "文章详情", notes = "根据url中的id获取文章详情")
    @GetMapping("/a/article/{id}")
    public ResultBean getArticle(@PathVariable(name = "id") Long id,
                                 HttpServletRequest request) {
        log.info("ArtilcleController.getArticle:测试是否进入" + ",id:" + id);
        return articleService.getArticle(id, null);
    }

    @ApiOperation(value = "新增文章", notes = "创建用户，默认：状态为下架，类型为文章，点赞数为0，收藏数为0。" +
            "约定：1为上架，2为下架，category：1为banner，2为card")
    @PostMapping("/a/article")
    public ResultBean addArticle(@RequestBody Article article,
                                 HttpServletRequest request) {
        log.info("ArtilcleController.putArticle:测试是否进入");
        //参数校验
        if (PublicUtility.isNullOrEmpty(article)) {
            return new ResultBean(605);
        }
        if (PublicUtility.strIsEmpty(article.getTitle())) {
            return new ResultBean(610);
        }
        if (PublicUtility.strIsEmpty(article.getAuthor())) {
            return new ResultBean(606);
        }
        if (PublicUtility.strIsEmpty(article.getBrief())) {
            return new ResultBean(607);
        }
        if (PublicUtility.strIsEmpty(article.getImgUrl())) {
            return new ResultBean(608);
        }
        if (PublicUtility.strIsEmpty(article.getBody())) {
            return new ResultBean(609);
        }
        if (article.getStatus() == null || article.getStatus() == ZERO) {
            article.setStatus(SOLD_OUT);
        }
        if (article.getCategory() == null) {
            article.setCategory(CARD);
        }
        if (article.getLike() == null) {
            article.setLike(ZERO);
        }
        if (article.getCollection() == null) {
            article.setCollection(ZERO);
        }
        //加入创建者id
        Long managerId = Long.valueOf(AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID).toString());
        article.setCreateBy(managerId);
        article.setUpdateBy(managerId);
        article.setUpdateAt(System.currentTimeMillis());
        article.setCreateAt(System.currentTimeMillis());
        log.info(article.toString());
        return articleService.addArticle(article);
    }

    @ApiOperation(value = "更新文章", notes = "根据URL获取文章的id进行更新文章,id!=null")
    @PutMapping("/a/article/{id}")
    public ResultBean putArticle(@PathVariable(name = "id") Long id,
                                 @RequestBody Article article,
                                 HttpServletRequest request) {
        log.info("ArtilcleController.putArticle:测试是否进入" + ",id:" + id);
        //参数校验
        if (PublicUtility.isNullOrEmpty(article)) {
            return new ResultBean(605);
        }
        if (id == null) {
            return new ResultBean(206);
        }
        //加入创建者id
        Long managerId = Long.valueOf(AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID).toString());
        article.setUpdateBy(managerId);
        article.setUpdateAt(System.currentTimeMillis());
        article.setId(id);
        log.info(article.toString());
        return articleService.putArticle(article);
    }

    @ApiOperation(value = "文章上下架", notes = "根据URL获取文章的id进行更新文章上下架,传入的值：id！=null，cetegory不能为null，status！=null")
    @PutMapping("/a/article/status/{id}")
    public ResultBean putArticleStatus(@PathVariable(name = "id") Long id,
                                       @RequestBody Article article,
                                       HttpServletRequest request) {
        log.info("ArtilcleController.putArticleStatus:测试是否进入" + ",id:" + id);
        if (id == null) {
            return new ResultBean(206);
        }
        if (article.getCategory() == null) {
            return new ResultBean(604);
        }
        //加入创建者id
        Long managerId = Long.valueOf(AccessTokenUtil.getAccessTokeValues(request, MANAGER_ID).toString());
        article.setUpdateBy(managerId);
        article.setUpdateAt(System.currentTimeMillis());
        article.setId(id);
        return articleService.putArticleStatus(article);
    }
}
