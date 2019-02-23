package com.academy.core.pojo;

import java.util.Arrays;

public class Article {
    public static final String ARTICLE_PIC_PATH = "/article/";

    public static final String PIC_PNG = "PNG";
    public static final String PIC_JPG = "JPG";


    private Long id;


    private String title;


    private Integer category;


    private String imgUrl;

    private String author;


    private String brief;


    private Integer like;


    private Integer collection;


    private Integer status;


    private Long createBy;


    private Long updateBy;


    private Long createAt;


    private Long updateAt;


    private String body;

    private Integer[] state;


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }


    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }


    public String getImgUrl() {
        return imgUrl;
    }


    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getBrief() {
        return brief;
    }


    public void setBrief(String brief) {
        this.brief = brief == null ? null : brief.trim();
    }


    public Integer getLike() {
        return like;
    }


    public void setLike(Integer like) {
        this.like = like;
    }


    public Integer getCollection() {
        return collection;
    }


    public void setCollection(Integer collection) {
        this.collection = collection;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public Long getCreateBy() {
        return createBy;
    }


    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }


    public Long getUpdateBy() {
        return updateBy;
    }


    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }


    public Long getCreateAt() {
        return createAt;
    }


    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }


    public Long getUpdateAt() {
        return updateAt;
    }


    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public Integer[] getState() {
        return state;
    }

    public void setState(Integer[] state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category=" + category +
                ", imgUrl='" + imgUrl + '\'' +
                ", author='" + author + '\'' +
                ", brief='" + brief + '\'' +
                ", like=" + like +
                ", collection=" + collection +
                ", status=" + status +
                ", createBy=" + createBy +
                ", updateBy=" + updateBy +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", body='" + body + '\'' +
                ", state=" + Arrays.toString(state) +
                '}';
    }
}