package com.academy.core.util;

public class PageUtil {
    /**
     * 获取当前页是从几页开始
     * @param page
     * @param size
     * @return
     */
    public static Integer getStart(Integer page,Integer size){
        return (page -1)*size;
    }
}
