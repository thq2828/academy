package com.academy.core.util;

import com.alibaba.fastjson.JSON;

import java.util.Arrays;
import java.util.List;

public class JsonUtil {
    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }

    public static <T> T toObject(String json, Class<T> cla) {
        return JSON.parseObject(json, cla);
    }

    public static <T> List<T> toList(String json, Class<T> t) {
        return JSON.parseArray(json, t);
    }

    public static void main(String[] args) {
        Long[] ids =new Long[3];
        ids[0]=1L;
        ids[1]=2L;
        ids[2]=3L;
        System.out.println(JsonUtil.toJsonString(ids));
        System.out.println((JsonUtil.toObject("[1,2,3]", Long[].class)));
    }

}
