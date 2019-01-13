package com.academy.core.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import static com.academy.core.util.Constant.AUTHORIZATION;

@Slf4j
public class AccessTokenUtil {
    public static Object getAccessTokeValues(HttpServletRequest request, String key) {
        log.info("获取accessToken");
        String accessToken = request.getHeader(AUTHORIZATION);
        //截取“.”到“.”之间的字符串
        int start = accessToken.indexOf(".")+1;
        int end =accessToken.indexOf(".",start);
        String userInfoBase = accessToken.substring(start,end);
        //解密accessToken第二部分内容
        byte[] bytes = Base64.getUrlDecoder().decode(userInfoBase.getBytes(StandardCharsets.UTF_8));
        String userInfo=new String(bytes, StandardCharsets.UTF_8);
        System.out.println(userInfo);
        //解密后的json数据装入Map
        Map<String,Object> map = JSON.parseObject(userInfo);
        return map.get(key);
    }
}
