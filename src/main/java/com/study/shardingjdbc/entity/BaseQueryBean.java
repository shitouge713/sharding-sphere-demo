/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:42
 */
package com.study.shardingjdbc.entity;

/**
 * 基础查询Bean
 * @author: susu
 * 
 */
public class BaseQueryBean {

    private Integer pageNo;

    private Integer pageSize;

    public Integer getPageNo() {
        if(pageNo == null || pageNo <= 0){
            return 1;
        }
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        if(pageSize == null || pageSize <= 0){
            return 50;
        }
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartIndex(){
        return (getPageNo() - 1) * getPageSize();
    }
}
