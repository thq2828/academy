package com.academy.core.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * collect
 * @author joe
 */
@Data
public class Collect implements Serializable {
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
    private Byte type;

    private static final long serialVersionUID = 1L;
}