package com.academy.core.util;

public class Constant {
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


}
