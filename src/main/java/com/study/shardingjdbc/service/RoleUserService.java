/*
 * All rights Reserved, Designed By Nemo
 * 2020/9/3 13:40
 */
package com.study.shardingjdbc.service;

import com.study.shardingjdbc.entity.RoleUser;
import com.study.shardingjdbc.entity.SysUser;

import java.util.List;

/**
 * 用户相关业务
 *
 * @author: susu
 */
public interface RoleUserService {

    /**
     * 新增
     *
     * @param sysUser
     */
    void insert(RoleUser sysUser);


    List<SysUser> selectByRoleId(Integer roleId);

    void deleteByRoleId(Integer roleId);
}
