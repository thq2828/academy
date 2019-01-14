package com.academy.core.util;

import java.util.Collection;
import java.util.Map;

public class PublicUtility {

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     */
    public static boolean isNullOrEmpty(Object ojb) {
        if (ojb == null) {
            return true;
        }
        if (ojb instanceof CharSequence) {
            return true;
        }
        if (ojb instanceof Collection) {
            return ((Collection) ojb).isEmpty();
        }
        if (ojb instanceof Map) {
            return ((Map) ojb).isEmpty();
        }
        if (ojb instanceof Object[]) {
            Object[] object = (Object[]) ojb;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }

        return false;
    }

    /**
     * 判断一个字符串是否为null
     * @param s
     * @return
     */
    public static  boolean strIsEmpty(String s){
        if ("".equals(s)||s==null||s.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 判断Long是否为null
     * @param id
     * @return
     */
    public static boolean numIsEmpty(Long id){
        if (id==null){
            return true;
        }
        return false;
    }
}
