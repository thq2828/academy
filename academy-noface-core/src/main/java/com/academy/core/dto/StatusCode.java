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
    NOT_PERMISSIONS(205,"角色没有模块权限"),
    ID_NOT_NULL(206,"id不能为null"),

    /**
     * 文章模块code码,600-620
     */
    TYPE_NOT_NULL(601, "type不能为null"),
    UNRECOGNIZED_TYPE(602,"无法识别type"),
    LIKE_COLLECTION_FAILD(603,"初始点赞收藏数不能大于结束点收藏赞数"),
    CEGATORY_NOT_NULL(604,"category不能为null"),
    ARTICLE_NOT_NULL(605,"文章不能为null"),
    AUTHOR_NOT_NULL(606,"作者不能为null"),
    BRIEF_NOT_NULL(607,"摘要不能为null"),
    IMGURL_NOT_NULL(608,"封面不能为null"),
    BODY_NOT_NULL(609,"正文不能为null"),
    TITLE_NOT_NULL(610,"标题不能为null"),
    ARTICLES_NOT_NULL(611,"文章数据为null"),
    ADD_ARTICLE_FAILD(612,"增加文章失败"),
    PUT_ARTICLE_FAILD(613,"更新文章失败"),
    PUTAWAY_FAILD(614,"上架失败"),
    PUTAWAY_FAILD_MANY(615,"上架的总数超过8个请先下架到8个以下在上架"),
    LIKE_COLLECTION(616,"已在点赞或者收藏状态"),
    LIKE_COLLECTION_DO(617,"没有点赞或者收藏状态"),
    LIKE_COLLECTION_DEL(618,"取消点赞收藏失败"),
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
    PUT_MANAGER_FAILD(632,"manager更新失败"),



    /**
     * Module模块code码，641-660
     */
    MODULE_NOT_NULL(641,"module不能为null"),
    MODULE_NULL(642,"模块的名字不能为null"),
    MODULES_NULL(643,"模块数据为null"),
    MODULE_NAME_URL(644,"模块名或者url已经存在"),
    MODULE_ADD_FAILD(645,"增加模块失败"),
    MODULE_PUT_FAILD(646,"更新失败"),
    MODULE_DEL_FAILD(647,"删除失败"),



    /**
     * Role模块code码，661-680
     */
    ROLE_NOT_NULL(661,"role不能为null"),
    ROLE_NAME_NULL(662,"role名不能为null"),
    IDS_NOT_NULL(663,"ids不能为null"),
    ROLE_IS_NULL(665,"没有角色信息"),
    ROLE_IS_EXIST(666,"角色名经存在"),
    ROLE_SAVE_FAILD(667,"新增角色失败"),
    ROLE_PUT_FAILD(668,"更新失败！"),
    DEL_ROLE_FAILD(669,"删除失败，role下有管理员信息"),
    ROLE_DEL_FAILD(670,"删除失败");


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
