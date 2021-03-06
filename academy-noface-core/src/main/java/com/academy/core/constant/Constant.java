package com.academy.core.constant;

public class Constant {
    /**
     * 0-10常量
     */
    public static final Long PARENT=0L;
    public static final Integer ZERO=0;
    public static final Integer ONE=1;
    public static final Integer EIGHT =8;
    public static final Integer SEVEN =7;


    public static final Integer START_PAGE=1;
    public static final Integer START_SIZE=10;
    public static final Integer BANAGER_SIZE=8;

    public static final Integer PUTAWAY=1;
    public static final Integer SOLD_OUT=2;


    /**
     * 请求头中的Authorization
     */
    public static final String AUTHORIZATION ="Authorization";

    /**
     * 管理员常量表
     */
    //增加账号默认状态
    public static final String STATUS="using";
    //增加成功
    public static final Integer MANAGER_SUCCESS =1;
    //删除状态
    public static final Integer MANAGER_DEL_SUCCESS=1;
    //更新密码状态
    public static final Integer MANAGER_PUT_PWD=1;
    //更新失败
    public static final Integer MANAGER_PUT_STATUS=1;


    /**
     * JWT第二部分中的内容
     */
    public static final String USER_NAME="user_name";
    //范围
    public static final String SCOPE="scope";
    //管理员的ID
    public static final String MANAGER_ID="manageId";
    //管理员的名字
    public static final String EXP="user_name";
    //用户的权限信息
    public static final String AUTHORITIES="authorities";
    public static final String JTI="jti";
    //客户端的id
    public static final String CLIENT_ID="client_id";

    /**
     * Role常量表
     */
    //增加失败
    public static final Integer ROLE_SUCCESS =1;
    public static final Integer ROLE_PUT=1;
    public static final Integer ROLE_DEl=1;

    /**
     * Modle常量表
     */
    public static final String TYPE="web";
    public static final Integer INSERT=1;
    public static final Integer DEL=1;
    /**
     * article常量
     */
    //点赞
    public static final Integer LIKE=1;
    //收藏
    public static final Integer COLLECTION=2;
    //增加判断
    public static final Integer ADD_ARTICLE=1;
    //更新判断
    public static final Integer PUT_ARTICLE=1;
    public static final Integer BANNER=1;
    public static final Integer CARD=2;
}
