package com.academy.core.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * code
 * @author joe
 */
@Data
public class Code implements Serializable {

    public static final Integer PHONE = 1;
    public static final Integer EMAIL = 2;

    /**
     * 验证手机号
     */
    public static final String REGEX_PHONE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";


    private Long id;

    /**
     * 邮箱或手机号
     */
    private String info;

    /**
     * 验证码
     */
    private Integer number;

    private Long createAt;

    private Long updateAt;

    private static final long serialVersionUID = 1L;

}