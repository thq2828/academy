package com.academy.core.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * user
 * @author joe
 */
@Data
public class User implements Serializable {
    /**
     * 自增id
     */
    private Long id;

    /**
     * 微信用户唯一标识
     */
    private String openId;

    /**
     * 昵称
     */
    private String nick;

    /**
     * 头像
     */
    private String pic;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 逆袭豆
     */
    private Integer bean;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 用户状态
     */
    private Integer status;

    /**
     * 所在区域
     */
    private String address;

    /**
     * 上次签到日期
     */
    private Integer lastSign;

    /**
     * 当前连续签到天数
     */
    private Integer nowSign;

    /**
     * 最大连续签到天数
     */
    private Integer mostSign;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 更新人
     */
    private Long updateBy;

    /**
     * 签到详情
     */
    private String signDays;

    private static final long serialVersionUID = 1L;
}