package com.academy.home.utils;

import javax.servlet.http.HttpServletRequest;

public class LoginUtil {
    public static Long getUid(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null) {
            return 0L;
        }
        return JJWTUtil.getUID(token);
    }
}
