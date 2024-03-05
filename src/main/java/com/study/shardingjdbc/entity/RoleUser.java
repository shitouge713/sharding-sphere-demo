/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:52
 */
package com.study.shardingjdbc.entity;

/**
 * 用户基础对象
 * @author: susu
 * 
 */
public class RoleUser {

    private Integer id;

    private Integer roleId;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
