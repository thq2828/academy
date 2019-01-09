package com.academy.home.utils;

import org.apache.http.Header;
import org.apache.http.HttpRequest;

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
