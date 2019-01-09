package com.academy.core.dto;

public enum StatusCode {
    /**
     * 公共statusCode
     */
    SUCCESS(200,"操作成功"),
    FAILURE(201,"操作失败"),
    DATA_IS_NULL(203,"数据为null"),

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

    NEWPWD_EQUAL_OLDPWD(621,"新密码和旧密码相同");

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
