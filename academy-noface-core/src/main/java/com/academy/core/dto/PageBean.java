package com.academy.core.dto;


/**
 * PageBean继承ResultBean，主要作用是返回分页的一些数据给前端
 * @param <T>
 */
public class PageBean<T> extends ResultBean implements Result{

    //已知数据
    private Integer pageNum;    //当前页,从请求那边传过来。
    private Integer pageSize;    //每页显示的数据条数。
    private Integer totalRecord;    //总的记录条数。查询数据库得到的数据

    public PageBean() {
    }
    public PageBean(Integer code,Integer pageSize,Integer totalRecord,T data){
        super(code,data);
        this.pageSize=pageSize;
        this.totalRecord=totalRecord;
    }

    public PageBean(Integer code) {
        super(code);
    }

    public PageBean(Integer code, Integer totalRecord, T data){
        super(code,data);
        this.totalRecord=totalRecord;
    }

    public PageBean(Integer code,Integer pageNum,Integer pageSize,Integer totalRecord, T data) {
        super(code,data);
        this.pageNum=pageNum;
        this.pageSize=pageSize;
        this.totalRecord=totalRecord;

    }
    public PageBean(Integer code, String message,Integer pageNum,Integer pageSize,Integer totalRecord, T data) {
        super(code,message,data);
        this.pageNum=pageNum;
        this.pageSize=pageSize;
        this.totalRecord=totalRecord;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", totalRecord=" + totalRecord +
                "} " + super.toString();
    }
}

