package com.academy.core.dto;

/**
 * StatusCode主要是用枚举对返回的状态码进行封装
 */
public enum StatusCode {
    /**
     * 公共statusCode
     */
    SUCCESS(200,"操作成功"),
    FAILURE(201,"操作失败"),
    DATA_IS_NULL(203,"数据不能为null"),
    SAVE_FAILD(204,"保存失败"),

    /**
     * 文章模块code码,600-620
     */
    TYPE_NOT_NULL(601, "type不能为null"),
    UNRECOGNIZED_TYPE(602,"无法识别type"),
    LIKE_COLLECTION_FAILD(603,"初始点赞收藏数不能大于结束点收藏赞数"),
    CEGATORY_NOT_NULL(604,"category不能为null"),

    /**
     * Manager模块code码，621-640
     */

    NEWPWD_EQUAL_OLDPWD(621,"新密码和旧密码相同"),
    MANAGER_NOT_NULL(622,"账号不存在"),
    PWD_ERROR(623,"密码错误"),
    TOKEN_ERROE(624,"获取token失败"),
    MANAGERS_NOT_NULL(625,"manager没有数据"),
    PWD_NOT_NULL(626,"密码不能为null"),
    NAME_NOT_NULL(627,"账号不能为null"),
    MAMAGER_ROLE_ID(628,"角色id不能为null"),
    MANAGER_EXISTS(629,"用户名已经存在"),
    PWD_NULL(630,"请输入新密码和旧密码"),
    PUT_PWD_FAILD(631,"更新密码错误"),
    PUT_MANAGER_FAILD(632,"manager更新失败");



    /**
     * Module模块code码，641-660
     */

    /**
     * Role模块code码，661-680
     */


    private int code;
    private String message;

    StatusCode(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public static String getName(int index) {
        for (StatusCode c : StatusCode.values()) {
            if (c.getCode() == index) {
                return c.message;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void main(String[] args) {
        System.out.println(StatusCode.getName(1));
    }

    }
