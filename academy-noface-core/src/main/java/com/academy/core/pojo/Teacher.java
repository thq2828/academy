package com.academy.core.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * teacher
 * @author joe
 */
@Data
public class Teacher implements Serializable {
    private Long id;

    private String nick;

    /**
     * 头像URL
     */
    private String pic;

    private static final long serialVersionUID = 1L;
}