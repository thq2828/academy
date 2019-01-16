package com.academy.article.service.impl;

import com.academy.article.dao.ArticleMapper;
import com.academy.core.dto.PageBean;
import com.academy.core.dto.ResultBean;
import com.academy.core.pojo.Article;
import com.academy.core.service.ArticleService;
import com.academy.core.util.PublicUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.academy.core.constant.Constant.*;

@RestController
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public PageBean getHomeArticles(@RequestParam(name = "category", required = false) Integer category,
                                    @RequestParam(name = "start", required = false) Integer start,
                                    @RequestParam(name = "size", required = false) Integer size,
                                    @RequestParam(name = "status", required = false) Integer status,
                                    @RequestParam(name = "userId") Long userId) {
        log.info("-------------进入ArticleServiceImpl.getHomeArticles-----------");
        //装入map里面就行动态查询
        Map<String, Object> map = new HashMap<>();
        map.put("category", category);
        map.put("status", status);
        List<Article> articles = articleMapper.selectBySelective(map);
        if (PublicUtility.isNullOrEmpty(articles)) {
            return new PageBean(611);
        }
        //看一下总数是否小于size,小于直接返回数据
        int totalRecord = articles.size();
        if (totalRecord < size && start == ZERO) {
            log.info("总数小于size，直接返回数据");
            //加入显示用户点赞收藏的数据
            articles = getUserArticle(articles, userId);
            return new PageBean<List<Article>>(200, size, totalRecord, articles);
        }
        map.put("start", start);
        map.put("size", size);
        articles = articleMapper.selectBySelective(map);
        //加入用户点赞和收藏数据
        articles = getUserArticle(articles, userId);
        return new PageBean<List<Article>>(200, size, totalRecord, articles);
    }

    @Override
    public PageBean getArticles(@RequestParam(name = "title", required = false) String title,
                                @RequestParam(name = "author", required = false) String author,
                                @RequestParam(name = "category", required = false) Integer category,
                                @RequestParam(name = "status", required = false) Integer status,
                                @RequestParam(name = "starLike", required = false) Integer starLike,
                                @RequestParam(name = "endLike", required = false) Integer endLike,
                                @RequestParam(name = "startCollection", required = false) Integer startCollection,
                                @RequestParam(name = "endCollection", required = false) Integer endCollection,
                                @RequestParam(name = "start", required = false) Integer start,
                                @RequestParam(name = "size", required = false) Integer size) {
        log.info("-------------进入ArticleServiceImpl.getArticles-----------");
        //装入map里面就行动态查询
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("author", author);
        map.put("category", category);
        map.put("status", status);
        map.put("starLike", starLike);
        map.put("endLike", endLike);
        map.put("startCollection", startCollection);
        map.put("endCollection", endCollection);
        List<Article> articles = articleMapper.selectBySelective(map);
        if (PublicUtility.isNullOrEmpty(articles)) {
            return new PageBean(611);
        }
        //看一下总数是否小于size,小于直接返回数据,数据量大，会出现什么问题？
        int totalRecord = articles.size();
        if (totalRecord < size && start == ZERO) {
            log.info("总数小于size，直接返回数据");
            return new PageBean<List<Article>>(200, size, totalRecord, articles);
        }
        map.put("start", start);
        map.put("size", size);
        articles = articleMapper.selectBySelective(map);
        return new PageBean<List<Article>>(200, size, totalRecord, articles);

    }

    @Override
    public ResultBean getArticle(@RequestParam(name = "id") Long id,
                                 @RequestParam(name = "userId", required = false) Long userId) {
        log.info("-------------进入ArticleServiceImpl.getArticles-----------");
        log.info("id:" + id);
        Article article = articleMapper.selectByPrimaryKey(id);
        if (PublicUtility.isNullOrEmpty(article)) {
            return new ResultBean(611);
        }
        //加入用户点赞搜藏数据
        List<Integer> types = articleMapper.getUserAndArticleDate(userId, article.getId());
        if (!PublicUtility.isNullOrEmpty(types)) {
            Integer[] integers = new Integer[types.size()];
            for (int i = 0; i < types.size(); i++) {
                Integer type = types.get(i);
                if (type == LIKE) {
                    integers[i] = LIKE;
                }
                if (type == COLLECTION) {
                    integers[i] = COLLECTION;
                }
            }
            article.setState(integers);
        }
        return new ResultBean<Article>(200, article);
    }

    @Override
    public ResultBean addArticle(@RequestBody Article article) {
        log.info("-------------进入ArticleServiceImpl.addArticle-----------");
        log.info("--------------" + article + "---------------");
        int i = articleMapper.insert(article);
        if (i < ADD_ARTICLE) {
            return new ResultBean(612);
        }
        return new ResultBean(200);
    }

    @Override
    public ResultBean putArticle(@RequestBody Article article) {
        log.info("-------------进入ArticleServiceImpl.putArticle-----------");
        log.info("--------------" + article + "---------------");
        int i = articleMapper.updateByPrimaryKeySelective(article);
        if (i < ADD_ARTICLE) {
            return new ResultBean(613);
        }
        return new ResultBean(200);
    }

    @Override
    public ResultBean putArticleLike(@RequestParam(name = "userId") Long userId,
                                     @RequestParam(name = "id") Long id,
                                     @RequestParam(name = "type") Integer type) {
        log.info("-------------进入ArticleServiceImpl.putArticleLike-----------");
        log.info("type:{},id:{},userId{}", type, id, userId);
        int count = articleMapper.selectUserAndArticleByCount(type, userId, id);
        if (count > ZERO) {
            return new ResultBean(616);
        }
        int j = articleMapper.insertUserArticle(type, userId, id);
        if (j < ONE) {
            return new ResultBean(201);
        }
        if (type == LIKE) {
            Article article = articleMapper.selectByPrimaryKey(id);
            Integer like = article.getLike() + 1;
            article.setLike(like);
            int i =articleMapper.updateByPrimaryKeyWithBLOBs(article);
            if (i<ONE){
                log.info("点赞或者收藏总数没有更新");
                //设即到事务还没解决
                articleMapper.delUserArticle(type,userId,id);
                return new ResultBean(201);

            }
        }
        if (type == COLLECTION) {
            Article article = articleMapper.selectByPrimaryKey(id);
            Integer collection = article.getCollection()+ 1;
            article.setCollection(collection);
            int i =articleMapper.updateByPrimaryKeyWithBLOBs(article);
            if (i<ONE){
                log.info("点赞或者收藏总数没有更新");
                //设即到事务还没解决
               articleMapper.delUserArticle(type,userId,id);
                return new ResultBean(201);
            }
        }
        return new ResultBean(200);
    }

    @Override
    public ResultBean delArticleLike(@RequestParam(name = "userId") Long userId,
                                     @RequestParam(name = "id") Long id,
                                     @RequestParam(name = "type") Integer type) {
        log.info("-------------进入ArticleServiceImpl.delArticleLike-----------");
        log.info("type:{},id:{},userId{}", type, id, userId);
        int count = articleMapper.selectUserAndArticleByCount(type, userId, id);
        System.out.println(count);
        if (count < ONE) {
            log.info("没有点赞和取消状态");
            return new ResultBean(617);
        }
        int i = articleMapper.delUserArticle(type, userId, id);
        System.out.println(i);
        if (i < ONE) {
            log.info("取消点赞失败！");
            return new ResultBean(618);
        }
        if (type == LIKE) {
            Article article = articleMapper.selectByPrimaryKey(id);
            Integer like = article.getLike() -1;
            article.setCollection(like);
            int n =articleMapper.updateByPrimaryKeyWithBLOBs(article);
            if (n<ONE){
                log.info("点赞或者收藏总数没有更新");
                //设即到事务还没解决
               articleMapper.delUserArticle(type,userId,id);
                return new ResultBean(201);

            }
        }
        if (type == COLLECTION) {
            Article article = articleMapper.selectByPrimaryKey(id);
            Integer collection = article.getCollection()-1;
            article.setLike(collection);
            int n =articleMapper.updateByPrimaryKeyWithBLOBs(article);
            if (n<ONE){
                log.info("点赞或者收藏总数没有更新");
                //设即到事务还没解决
                articleMapper.delUserArticle(type,userId,id);
                return new ResultBean(201);
            }
        }
        return new ResultBean(200);
    }

    @Override
    public ResultBean putArticleStatus(@RequestBody Article article) {
        log.info("-------------进入ArticleServiceImpl.putArticleStatus-----------");
        log.info("--------------" + article + "---------------");
        if (article.getCategory() == BANNER && article.getStatus() == PUTAWAY) {
            Map<String, Object> map = new HashMap<>();
            map.put("category", article.getCategory());
            map.put("status", article.getStatus());
            //查询出banner上线状态的文章集合
            List<Article> articles = articleMapper.selectBySelective(map);
            //判断是否为null，不为null继续下一步
            if (!PublicUtility.isNullOrEmpty(articles)) {
                //集合总数小于8直接执行更新上架
                if (articles.size() < EIGHT) {
                    int i = articleMapper.updateByPrimaryKeySelective(article);
                    if (i < PUTAWAY) {
                        return new ResultBean(614);
                    }
                    return new ResultBean(200);
                }
                //判断集合总数是否等于8，等于8把最后一位改成下线状态
                if (articles.size() == EIGHT) {
                    //把第最后一位改成下线状态，并且更新数据
                    articles.get(articles.size() - ONE).setStatus(SOLD_OUT);
                    int i = articleMapper.updateByPrimaryKeyWithBLOBs(articles.get(articles.size() - ONE));
                    if (i < PUTAWAY) {
                        return new ResultBean(614);
                    }
                    //更新前端传过来的为上线状态
                    i = articleMapper.updateByPrimaryKeySelective(article);
                    if (i < PUTAWAY) {
                        return new ResultBean(614);
                    }
                    return new ResultBean(200);
                }
                return new ResultBean(615);
            }

        }
        int i = articleMapper.updateByPrimaryKeyWithBLOBs(article);
        if (i < PUTAWAY) {
            return new ResultBean(614);
        }
        return new ResultBean(200);
    }

    protected List<Article> getUserArticle(List<Article> articles, Long userId) {
        for (Article article : articles) {
            List<Integer> types = articleMapper.getUserAndArticleDate(userId, article.getId());
            if (!PublicUtility.isNullOrEmpty(types)) {
                Integer[] integers = new Integer[types.size()];
                for (int i = 0; i < types.size(); i++) {
                    Integer type = types.get(i);
                    if (type == LIKE) {
                        integers[i] = LIKE;
                    }
                    if (type == COLLECTION) {
                        integers[i] = COLLECTION;
                    }
                }
                article.setState(integers);
            }
        }
        return articles;
    }
}
