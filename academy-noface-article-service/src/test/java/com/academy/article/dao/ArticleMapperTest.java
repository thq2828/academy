package com.academy.article.dao;

import com.academy.core.pojo.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleMapperTest {
    @Autowired
    private ArticleMapper articleMapper;

    private static Article article;
    /**
     * 初始化假数据
     */
    static {
        article = new Article();
        //Integer[] integers = new Integer[2];
        //integers[0] = 1;
        //integers[1] = 0;
        article.setTitle("阿里山的姑娘美如水啊");
        article.setAuthor("吴彦祖");
        article.setBrief("这是摘要的内容.......");
        article.setCategory(2);
        article.setBody("这是富文本内容............................");
        article.setCollection(110);
        article.setLike(120);
        article.setImgUrl(" http://carrots.ks3-cn-beijing.ksyun.com/img/atlas/b975c68e-e544-4cdc-82ee-f72fcfcbf335.jpg");
        article.setStatus(1);

        article.setUpdateAt(System.currentTimeMillis());
        article.setUpdateBy(2L);


    }


    @Test
    public void deleteByPrimaryKey() {
        int i =articleMapper.deleteByPrimaryKey(3L);
        System.out.println(i);
    }

    @Test
    public void insert() {
        int i=articleMapper.insert(article);
        System.out.println(i);

    }

    @Test
    public void insertSelective() {

    }

    @Test
    public void selectByPrimaryKey() {
        System.out.println(articleMapper.selectByPrimaryKey(1L));
    }

    @Test
    public void selectBySelective() {
        Map<String,Object> map =new HashMap<>();
        map.put("starLike",70);
        map.put("endLike",90);
        map.put("startCollection",110);
        map.put("endCollection",120);

        System.out.println(articleMapper.selectBySelective(map));


    }

    @Test
    public void updateByPrimaryKeySelective() {
        article.setId(1L);
        article.setAuthor("海清");
        System.out.println(articleMapper.updateByPrimaryKeySelective(article));
    }

    @Test
    public void updateByPrimaryKeyWithBLOBs() {
    }

    @Test
    public void getUserAndArticleDate() {
        System.out.println(articleMapper.getUserAndArticleDate(2L,2L));



    }

    @Test
    public void selectUserAndArticleByCount() {
        System.out.println(articleMapper.selectUserAndArticleByCount(1,1L,1L)+"----2222222222222222222");
    }
}