package com.academy.core.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * collect
 * @author joe
 */
@Data
public class Collect implements Serializable {

    public static final Integer COLLECT = 1;
    public static final Integer LIKE = 2;

    public static final Integer STATUS_UNLIKE = 0;
    public static final Integer STATUS_LIKE = 1;

    public static final Integer STATUS_UNCOLLECT = 0;
    public static final Integer STATUS_COLLECT = 1;

    /**
     * 自增id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 视频id
     */
    private Long videoId;

    /**
     * 点赞或收藏
     */
    private Integer type;

    private static final long serialVersionUID = 1L;
}