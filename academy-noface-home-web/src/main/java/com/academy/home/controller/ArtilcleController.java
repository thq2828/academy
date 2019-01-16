package com.academy.home.controller;

import com.academy.core.dto.PageBean;
import com.academy.core.dto.ResultBean;
import com.academy.core.service.ArticleService;
import com.academy.core.util.PageUtil;
import com.academy.core.util.PublicUtility;
import com.academy.home.utils.LoginUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


import static com.academy.core.constant.Constant.*;

@Api(tags = "前台文章模块接口")
@RestController
@Slf4j
@RequestMapping("a")
public class ArtilcleController {
    @Autowired
    private ArticleService articleService;



    /**
     * ---------------------------说明：前台文章模块接口---------------------------
     */

    @ApiOperation(value = "文章和banner列表", notes = "根据category的不同，区别banner和文章,category=1,size=8；category=2,size=10")
    @GetMapping("/u/articles")
    public ResultBean getArticles(@RequestParam(name = "category") Integer category,
                                  @RequestParam(name = "page", required = false) Integer page,
                                  @RequestParam(name = "size", required = false) Integer size,
                                  HttpServletRequest request) {
        log.info("ArtilcleController.getArticles:测试是否进入");

        if (category == null) {
            return new ResultBean(604);
        }
        if (page == null) {
            page = START_PAGE;
        }
        if (category == BANNER && size == null) {
            size = BANAGER_SIZE;
        }
        if (category == CARD && size == null) {
            size = START_SIZE;
        }
        int status = PUTAWAY;
        //获取前台用户的id
        Long userId =LoginUtil.getUid(request);
        log.info("文章类别：" + category + ",第几页：" + page + ",每页多少行" + size);
        int start = PageUtil.getStart(page, size);
        ResultBean resultBean = articleService.getHomeArticles(category, start, size, status,userId);
        if (!PublicUtility.isNullOrEmpty(resultBean.getData())) {
            ((PageBean) resultBean).setPageNum(page);
        }
        return resultBean;
    }

    @ApiOperation(value = "文章详情", notes = "根据url中的id获取文章详情")
    @GetMapping("/u/article/{id}")
    public ResultBean getArticle(
            @PathVariable(value = "id") Long id,
            HttpServletRequest request) {
        log.info("ArtilcleController.getArticle:测试是否进入" + ",id:" + id);
        Long userId =LoginUtil.getUid(request);
        return articleService.getArticle(id,userId);
    }

    @ApiOperation(value = "点赞和取消点赞", notes = "根据URL获取文章的id，type=1时为点赞，type=2为收藏")
    @PostMapping("/u/article/like/{id}")
    public ResultBean likeOrCollection(@PathVariable(name = "id") Long id,
                                       @RequestParam(name = "type") Integer type,
                                       HttpServletRequest request) {
        log.info("ArtilcleController.likeOrCollection:测试是否进入" + ",id:" + id);
        //获取用户的id
        Long userId = LoginUtil.getUid(request);
        return articleService.putArticleLike(userId,id,type);
    }


    @ApiOperation(value = "取消点赞和收藏", notes = "根据URL获取文章的id，type=1时为取消点赞，type=2为取消收藏")
    @DeleteMapping("/u/article/like/{id}")
    public ResultBean putLikeOrCollection(@PathVariable(name = "id") Long id,
                                          @RequestParam(name = "type") Integer type,
                                          HttpServletRequest request) {
        log.info("ArtilcleController.putLikeOrCollection:测试是否进入" + ",id:" + id);
        //获取用户的id
        Long userId = LoginUtil.getUid(request);
        return articleService.delArticleLike(userId,id,type);
    }
}
